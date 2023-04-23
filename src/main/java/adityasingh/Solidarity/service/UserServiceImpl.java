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

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskUserRepository taskUserRepository;
    @Autowired
    private TaskRepository taskRepository;

    public List<List<Task>> getTasksForUser(Long userId) throws ResourceNotFoundException {
        List<List<Task>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        res.add(new ArrayList<>());
        res.add(new ArrayList<>());
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User id" + userId));
        List<TaskUser> taskUsers = taskUserRepository.findByUser(user);
        List<Task> temp = taskUsers.stream().map(TaskUser::getTask).toList();
        for(Task task: temp){
            if(task.getStatus().equals("pending")){
                res.get(0).add(task);
            }
            else if(task.getStatus().equals("inprogress")){
                res.get(1).add(task);
            }
            else{
                res.get(2).add(task);
            }
        }
        return res;
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
