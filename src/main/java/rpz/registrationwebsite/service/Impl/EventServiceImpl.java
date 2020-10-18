package rpz.registrationwebsite.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rpz.registrationwebsite.config.exception.CustomException;
import rpz.registrationwebsite.config.exception.CustomExceptionType;
import rpz.registrationwebsite.dao.EventRepository;
import rpz.registrationwebsite.model.Event;
import rpz.registrationwebsite.service.EventService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class EventServiceImpl implements EventService {
    @Autowired
    EventRepository eventRepository;

    @Override
    public void saveEvent(Event event) {
        String stu1_id = event.getStu1_id();  //肯定不为空
        String stu2_id = event.getStu2_id();
        String stu3_id = event.getStu3_id();
        String event_name = event.getEvent_name();

        //选出参加这个比赛的所有同学的学号
        ArrayList<String> stu_ids = eventRepository.findStu1IdsByEventName(event_name);
        ArrayList<String> stu2_ids = eventRepository.findStu2IdsByEventName(event_name);
        ArrayList<String> stu3_ids = eventRepository.findStu3IdsByEventName(event_name);
        stu_ids.addAll(stu2_ids);
        stu_ids.addAll(stu3_ids);

        //保证传来的学号不会重复 且 一个同学 一种比赛只能参加一次
        if (stu2_id.equals("")) {  //如果没有第二个学生
            if (stu3_id.equals("")) {
                //如果只有第一个学生
                if (stu_ids.contains(stu1_id)) {
                    throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"队中有学生已参加此类比赛，不能重复报名");
                }
            }
            else {
                //如果有第一个和第三个学生
                if (stu1_id.equals(stu3_id)) {
                    throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"填写时提交相同学号，请确认信息");
                }
                else {
                    if (stu_ids.contains(stu1_id) || stu_ids.contains(stu3_id)) {
                        throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"队中有学生已参加此类比赛，不能重复报名");
                    }
                }
            }
        }
        else {  //如果有第二个学生
            if (stu3_id.equals("")) {
                //如果有第一个和第二个学生
                if (stu1_id.equals(stu2_id)) {
                    throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"填写时提交相同学号，请确认信息");
                }
                else {
                    if (stu_ids.contains(stu1_id) || stu_ids.contains(stu2_id)) {
                        throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"队中有学生已参加此类比赛，不能重复报名");
                    }
                }
            }
            else {
                //如果三个学生都有
                if (stu1_id.equals(stu2_id) || stu2_id.equals(stu3_id) || stu1_id.equals(stu3_id)) {
                    throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"填写时提交相同学号，请确认信息");
                }
                else {
                    if (stu_ids.contains(stu1_id) || stu_ids.contains(stu2_id) || stu_ids.contains(stu3_id)) {
                        throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"队中有学生已参加此类比赛，不能重复报名");
                    }
                }
            }
        }

        //存入数据库
        eventRepository.save(event);
    }

    @Override
    public String isExist(String event_name, String stu1_id) {
        Event event = eventRepository.findByEventNameAndStu1Id(event_name, stu1_id);
        if (event == null) {
            return null;
        }
        return event.getStu1_name();

    }

}
