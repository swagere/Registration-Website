package rpz.registrationwebsite.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rpz.registrationwebsite.config.exception.AjaxResponse;
import rpz.registrationwebsite.model.Event;
import rpz.registrationwebsite.service.EventService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/event")
@ResponseBody
public class EventController {
    @Autowired
    EventService eventService;

    /**
     * 添加报名信息
     * @param event
     * @return
     */
    @PostMapping("/add")
    public @ResponseBody
    AjaxResponse saveEvent(@Valid @RequestBody Event event) {
        eventService.saveEvent(event);
        return AjaxResponse.success("报名成功");
    }

    /**
     * 查询报名信息是否存在
     * @param str
     * @return
     */
    @PostMapping("/isExist")
    public @ResponseBody AjaxResponse isExist(@RequestBody String str) {
        String event_name = JSON.parseObject(str).get("event_name").toString();
        String stu1_id = JSON.parseObject(str).get("stu1_id").toString();
        String stu1_name = eventService.isExist(event_name, stu1_id);
        boolean flag = true;
        if (stu1_name == null) {
            flag = false;
        }
        Map<String, Object> res = new HashMap<>();
        res.put("isExist", flag);
        res.put("stu1_name", stu1_name);
        return AjaxResponse.success(res);
    }
}
