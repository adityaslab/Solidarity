package adityasingh.Solidarity.service;

import adityasingh.Solidarity.error.ResourceNotFoundException;

import java.util.List;

public interface TaskUserService {

    void addTaskToUser(Long userId, Long taskId) throws ResourceNotFoundException;

    void removeallWithtaskId(Long taskId);
}
