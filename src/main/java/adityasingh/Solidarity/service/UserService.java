package adityasingh.Solidarity.service;

import adityasingh.Solidarity.entity.Task;
import adityasingh.Solidarity.entity.User;
import adityasingh.Solidarity.error.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    List<List<Task>> getTasksForUser(Long userId) throws ResourceNotFoundException;

    void deleteUser(Long id);

    Optional<User> findByEmail(String email);

}
