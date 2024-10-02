package com.northcoders.gamboge.waddl_api.service;

import com.northcoders.gamboge.waddl_api.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface TaskManagerService {

    List<Task> getAllTasks();
    Optional<Task> getTaskById(Long id);
    Task addTask(Task task) throws IllegalAccessException;
    Task updateTaskById(Long id, Task task) throws NoSuchFieldException, IllegalAccessException;
    String deleteTaskById(Long id);

}
