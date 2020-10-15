package rpz.registrationwebsite.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class EventPK implements Serializable {
    private String stu1_id;
    private String event_name;
}
