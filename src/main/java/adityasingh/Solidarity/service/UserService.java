package adityasingh.Solidarity.service;

import adityasingh.Solidarity.entity.Task;
import adityasingh.Solidarity.entity.User;
import adityasingh.Solidarity.error.ResourceNotFoundException;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    public List<Task> getTasksForUser(Long userId) throws ResourceNotFoundException;
}
