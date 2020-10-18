package rpz.registrationwebsite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rpz.registrationwebsite.model.Event;

import java.util.ArrayList;

public interface EventRepository extends JpaRepository<Event, String> {
    @Query("select u.stu1_id from Event u where u.event_name = ?1 and u.stu1_id <> '' ")
    ArrayList<String> findStu1IdsByEventName(String event_name);

    @Query("select u.stu2_id from Event u where u.event_name = ?1 and u.stu2_id <> '' ")
    ArrayList<String> findStu2IdsByEventName(String event_name);

    @Query("select u.stu3_id from Event u where u.event_name = ?1 and u.stu3_id <> '' ")
    ArrayList<String> findStu3IdsByEventName(String event_name);

    @Query("select u from Event u where u.event_name = ?1 and u.stu1_id = ?2")
    Event findByEventNameAndStu1Id(String event_name, String stu1_id);
}
