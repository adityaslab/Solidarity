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
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_generator"
    )
    private Long userId;
    private String name;
//    private String email;
//    private String password;
//    @Enumerated(EnumType.STRING)
//    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<TaskUser> taskUsers = new ArrayList<>();
}
