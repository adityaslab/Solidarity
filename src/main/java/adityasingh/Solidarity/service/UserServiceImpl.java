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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static adityasingh.Solidarity.service.TaskServiceImpl.getLists;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskUserRepository taskUserRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskService taskService;

    public List<List<Task>> getTasksForUser(Long userId) throws ResourceNotFoundException {
        List<List<Task>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        res.add(new ArrayList<>());
        res.add(new ArrayList<>());
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User id" + userId));
        List<TaskUser> taskUsers = taskUserRepository.findByUser(user);
        List<Task> temp = taskUsers.stream().map(TaskUser::getTask).toList();
        return getLists(temp, res);
    }


    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
