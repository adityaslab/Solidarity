package adityasingh.Solidarity.controller;

import adityasingh.Solidarity.entity.Task;
import adityasingh.Solidarity.entity.User;
import adityasingh.Solidarity.error.ResourceNotFoundException;
import adityasingh.Solidarity.model.TaskUserModel;
import adityasingh.Solidarity.service.TaskService;
import adityasingh.Solidarity.service.TaskUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskUserService taskUserService;

    @GetMapping("/{id}/listUsers")
    public List<User> getUsersForTask(@PathVariable("id") Long taskId) throws ResourceNotFoundException {
        return taskService.getUsersforTask(taskId);
    }


    @PostMapping("/addUserToTask")
    public String addUserToTask(@RequestBody TaskUserModel taskUserModel) throws ResourceNotFoundException {
        System.out.println("here");
        taskUserService.addTaskToUser(taskUserModel.getUserId(), taskUserModel.getTaskId());
        return "User added to task";
    }

//    @PostMapping("/users/{id}/{userId}")
//    public String addUserToTask(@PathVariable("id") Long taskId, @PathVariable("userId") Long userId) throws ResourceNotFoundException {
//        taskUserService.addTaskToUser(userId, taskId);
//        return "User added to task";
//    }

    @GetMapping("/alltasks")
    public List<Task> getAllTasks(){
        return taskService.getAllTask();
    }


    @PostMapping("/addtask")
    public ResponseEntity<Task> addTask(@RequestBody Task task){
        return ResponseEntity.ok(taskService.saveTask(task));
    }

    @PostMapping("/removeTask/{id}")
    public String removeTask(@PathVariable("id") Long taskId) throws ResourceNotFoundException {
        taskService.removeTask(taskId);
        return "Task removed";
    }

}
