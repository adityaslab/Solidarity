package adityasingh.Solidarity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_id_generator"
    )
    private Long taskId;
    private String taskName;

    @JsonIgnore
    @OneToMany(mappedBy = "task")
    private List<TaskUser> taskUsers = new ArrayList<>();

//    private String description;
//    @Enumerated(EnumType.STRING)
//    private Status status;
//    @Enumerated(EnumType.STRING)
//    private Priority priority;
//    private Date startTime;
//    private Date endTime;
}
