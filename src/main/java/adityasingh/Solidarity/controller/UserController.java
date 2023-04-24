package adityasingh.Solidarity.controller;

import adityasingh.Solidarity.entity.Task;
import adityasingh.Solidarity.entity.TaskUser;
import adityasingh.Solidarity.entity.User;
import adityasingh.Solidarity.error.ResourceNotFoundException;
import adityasingh.Solidarity.model.TaskUserModel;
import adityasingh.Solidarity.service.JWTService;
import adityasingh.Solidarity.service.TaskService;
import adityasingh.Solidarity.service.TaskUserService;
import adityasingh.Solidarity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskUserService taskUserService;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private TaskService taskService;

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

    @PostMapping("/joinTask/{id}")
    public ResponseEntity<String> joinTask(@RequestHeader(name="Authorization") String token, @PathVariable("id") Long taskId) throws ResourceNotFoundException, IllegalAccessException {
        token = token.substring(7);
        String username = jwtService.extractUsername(token);
        User user = Optional.of(userService.findByEmail(username)).get().orElseThrow(IllegalAccessException::new);
        List<User> users = taskService.getUsersforTask(taskId);
        if(users.contains(user)){
            return ResponseEntity.ok("Already joined task");
        }
        else{
            taskUserService.addTaskToUser(user.getUserId(),taskId);
            return ResponseEntity.ok("Joined task");
        }
    }

    @GetMapping("/{id}/listTasks")
    public List<List<Task>> getTasksForUser(@PathVariable("id") Long userId) throws ResourceNotFoundException {
        return userService.getTasksForUser(userId);
    }
}
