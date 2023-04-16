package adityasingh.Solidarity.controller;

import adityasingh.Solidarity.entity.Task;
import adityasingh.Solidarity.entity.User;
import adityasingh.Solidarity.error.ResourceNotFoundException;
import adityasingh.Solidarity.model.TaskUserModel;
import adityasingh.Solidarity.service.TaskUserService;
import adityasingh.Solidarity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskUserService taskUserService;

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PostMapping("/addTaskToUser")
    public String addTaskToUser(@RequestBody TaskUserModel taskUserModel) throws ResourceNotFoundException {
        System.out.println("here");
        taskUserService.addTaskToUser(taskUserModel.getUserId(), taskUserModel.getTaskId());
        return "Task added to user";
    }
//    @PostMapping("/addTaskToUser/{userId}/{taskId}")
//    public String addTaskToUser(@PathVariable("userId") Long userId, @PathVariable("taskId") Long taskId) throws ResourceNotFoundException {
//        System.out.println("here");
//        taskUserService.addTaskToUser(userId, taskId);
//        return "Task added to user";
//    }

    @GetMapping("/{id}/tasks")
    public List<Task> getTasksForUser(@PathVariable("id") Long userId) throws ResourceNotFoundException {
        return userService.getTasksForUser(userId);
    }
}
