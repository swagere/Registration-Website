package rpz.registrationwebsite.service;

import rpz.registrationwebsite.model.Event;

public interface EventService {
    void saveEvent(Event event);

    boolean isExist(String event_name, String stu1_id);
}
