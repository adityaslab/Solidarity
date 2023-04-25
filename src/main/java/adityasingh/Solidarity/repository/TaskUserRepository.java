package adityasingh.Solidarity.repository;

import adityasingh.Solidarity.entity.Task;
import adityasingh.Solidarity.entity.TaskUser;
import adityasingh.Solidarity.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskUserRepository extends JpaRepository<TaskUser,Long> {
    List<TaskUser> findByTask(Task task);
    List<TaskUser> findByUser(User user);

    @Modifying
    @Transactional
    @Query(
            value = "delete from task_user where task_id = ?1",
            nativeQuery = true
    )
    void deleteAllByTaskId(Long taskId);
}
