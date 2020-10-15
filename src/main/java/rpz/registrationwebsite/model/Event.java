package rpz.registrationwebsite.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name = "event")
@IdClass(EventPK.class)
public class Event implements Serializable {
    @Id
    @Column
    @NotBlank(message = "学号不能为空")
    private String stu1_id;

    @Id
    @Column
    @NotBlank(message = "比赛项目不能为空")
    private String event_name;

    @NotBlank(message = "姓名不能为空")
    private String stu1_name;

    private String stu2_id;
    private String stu2_name;
    private String stu3_id;
    private String stu3_name;
}
