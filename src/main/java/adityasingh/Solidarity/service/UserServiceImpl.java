package adityasingh.Solidarity.service;

import adityasingh.Solidarity.entity.Task;
import adityasingh.Solidarity.entity.TaskUser;
import adityasingh.Solidarity.entity.User;
import adityasingh.Solidarity.error.ResourceNotFoundException;
import adityasingh.Solidarity.repository.TaskRepository;
import adityasingh.Solidarity.repository.TaskUserRepository;
import adityasingh.Solidarity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskUserRepository taskUserRepository;
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getTasksForUser(Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User id" + userId));
        List<TaskUser> taskUsers = taskUserRepository.findByUser(user);
        return taskUsers.stream().map(TaskUser::getTask).toList();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
