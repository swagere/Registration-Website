package rpz.registrationwebsite.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rpz.registrationwebsite.config.exception.AjaxResponse;
import rpz.registrationwebsite.model.Event;
import rpz.registrationwebsite.service.EventService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/event")
@ResponseBody
public class EventController {
    @Autowired
    EventService eventService;

    @PostMapping("/add")
    public @ResponseBody
    AjaxResponse saveEvent(@Valid @RequestBody Event event) {
        eventService.saveEvent(event);
        return AjaxResponse.success("报名成功");
    }
}
