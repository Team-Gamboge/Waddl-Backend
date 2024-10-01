package com.northcoders.gamboge.waddl_api.service;

import com.northcoders.gamboge.waddl_api.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface TaskManagerService {

    List<Task> getAllTasks();
    Optional<Task> getTaskById(Long id);
    Task addTask(Task task);
    Task updateTaskById(Long id, Task task);
    void deleteTaskById(Long id);

}
