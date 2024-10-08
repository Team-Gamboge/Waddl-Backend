package com.northcoders.gamboge.waddl_api.controller;

import com.northcoders.gamboge.waddl_api.model.Task;
import com.northcoders.gamboge.waddl_api.service.TaskManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable("taskCache2")
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<>(taskManagerService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Cacheable("taskCache2")
    public Optional<Task> getTaskById(@PathVariable Long id) {
        return taskManagerService.getTaskById(id);
    }

    @PostMapping
    @CacheEvict(cacheNames = "taskCache1", allEntries = true)
    public ResponseEntity<Task> addTask(@RequestBody Task task) throws IllegalAccessException {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskManagerService.addTask(task));
    }

    @PutMapping("/{id}")
    @CacheEvict(cacheNames = "taskCache1", allEntries = true)
    public ResponseEntity<Task> updateTaskById(@PathVariable Long id, @RequestBody Task task) throws NoSuchFieldException, IllegalAccessException {
        return ResponseEntity.ok(taskManagerService.updateTaskById(id, task));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(cacheNames = "taskCache1", allEntries = true)
    public ResponseEntity<String> deleteAlbumById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(taskManagerService.deleteTaskById(id));
    }

    @GetMapping("/health")
    public ResponseEntity<String> getHealth() {
        return new ResponseEntity<>(
                """
                          _____  U _____ u    _      __  __
                         |_ " _| \\| ___"|/U  /"\\  uU|' \\/ '|u
                           | |    |  _|"   \\/ _ \\/ \\| |\\/| |/
                          /| |\\   | |___   / ___ \\  | |  | |
                         u |_|U   |_____| /_/   \\_\\ |_|  |_|                                           .___.
                         _// \\\\_  <<   >>  \\\\    >><<,-,,-.                                           /     \\
                        (__) (__)(__) (__)(__)  (__)(./  \\.)                                         | O _ O |
                           ____      _      __  __     ____     U  ___ u   ____  U _____ u           /  \\_/  \\
                        U /"___|uU  /"\\  uU|' \\/ '|uU | __")u    \\/"_ \\/U /"___|u\\| ___"|/         .' /     \\ `.
                        \\| |  _ / \\/ _ \\/ \\| |\\/| |/ \\|  _ \\/    | | | |\\| |  _ / |  _|"          / _|       |_ \\
                         | |_| |  / ___ \\  | |  | |   | |_) |.-,_| |_| | | |_| |  | |___         (_/ |       | \\_)
                          \\____| /_/   \\_\\ |_|  |_|   |____/  \\_)-\\___/   \\____|  |_____|            \\       /
                          _)(|_   \\\\    >><<,-,,-.   _|| \\\\_       \\\\     _)(|_   <<   >>           __\\_>-<_/__   Todd Deery
                         (__)__) (__)  (__)(./  \\.) (__) (__)     (__)   (__)__) (__) (__)
                """, HttpStatus.OK);
    }

}
