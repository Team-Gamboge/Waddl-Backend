package com.northcoders.gamboge.waddl_api.service;

import com.northcoders.gamboge.waddl_api.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TaskManagerService {

    List<Task> getAllTasks();
    Optional<Task> getTaskById();
    Task addTask(Task task);
    Task updateTaskById(Long id, Task task);
    void deleteTaskById();

}
