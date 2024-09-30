package com.northcoders.gamboge.waddl_api.controller;

import com.northcoders.gamboge.waddl_api.model.Task;
import com.northcoders.gamboge.waddl_api.service.TaskManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @Autowired
    TaskManagerService taskManagerService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskManagerService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Optional<Task> getTaskById(@PathVariable Long id) {
        return taskManagerService.getTaskById(id);
    }

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task){
        if (task == null) {
            return ResponseEntity.badRequest().build(); // returns 400 bad request
        } return ResponseEntity.status(HttpStatus.CREATED).body(taskManagerService.addTask(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTaskById(@PathVariable Long id, @RequestBody Task task) {
        Task updatedTask = taskManagerService.updateTaskById(id, task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbumById(@PathVariable Long id) {
        taskManagerService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }

}
