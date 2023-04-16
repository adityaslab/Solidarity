package adityasingh.Solidarity.service;

import adityasingh.Solidarity.error.ResourceNotFoundException;

public interface TaskUserService {

    public void addTaskToUser(Long userId, Long taskId) throws ResourceNotFoundException;
}
