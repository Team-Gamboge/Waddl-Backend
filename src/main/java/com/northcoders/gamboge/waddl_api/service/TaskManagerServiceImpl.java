package com.northcoders.gamboge.waddl_api.service;

import com.northcoders.gamboge.waddl_api.model.Task;
import com.northcoders.gamboge.waddl_api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskManagerServiceImpl implements TaskManagerService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
        return List.of();
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        return Optional.empty();
    }

    @Override
    public Task addTask(Task task) {
        return null;
    }

    @Override
    public Task updateTaskById(Long id, Task task) {
        return null;
    }

    @Override
    public void deleteTaskById(Long id) {

    }
}
