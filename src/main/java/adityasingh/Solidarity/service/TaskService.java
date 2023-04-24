package adityasingh.Solidarity.service;

import adityasingh.Solidarity.entity.Task;
import adityasingh.Solidarity.entity.User;
import adityasingh.Solidarity.error.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;


public interface TaskService {


    public List<User> getUsersforTask(Long taskId) throws ResourceNotFoundException;
    public void addUserToTask(Long taskId, Long userId) throws ResourceNotFoundException;
    public Optional<Task> findTaskById(Long id);
    public Optional<Task> getTaskByName(String name);
    public List<Task> getAllTask();
    public List<List<Task>> getAllTaskStructured();
    public Task saveTask(Task task);

    public void removeTask(Long taskId);

}
