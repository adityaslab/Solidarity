package adityasingh.Solidarity.repository;

import adityasingh.Solidarity.entity.Task;
import adityasingh.Solidarity.entity.TaskUser;
import adityasingh.Solidarity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskUserRepository extends JpaRepository<TaskUser,Long> {
    List<TaskUser> findByTask(Task task);

    List<TaskUser> findByUser(User user);
}
