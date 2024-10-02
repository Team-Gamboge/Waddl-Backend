package com.northcoders.gamboge.waddl_api.service;

import com.northcoders.gamboge.waddl_api.exception.TaskNotFoundException;
import com.northcoders.gamboge.waddl_api.model.Task;
import com.northcoders.gamboge.waddl_api.repository.TaskRepository;
import com.northcoders.gamboge.waddl_api.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskManagerServiceImpl implements TaskManagerService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);
        return tasks;
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        if (taskRepository.existsById(id))
            return taskRepository.findById(id);

        throw new TaskNotFoundException(String.format("Task with ID %d not found.", id));
    }

    @Override
    public Task addTask(Task task) throws IllegalAccessException {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null.");
        }
        
        if (Utility.containsBlankStringFields(task)) 
            throw new IllegalArgumentException("Task cannot have any empty fields.");
            
        return taskRepository.save(task);
    }

    @Override
    public Task updateTaskById(Long id, Task task) throws NoSuchFieldException, IllegalAccessException {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();

            Utility.updateFieldsWhereNotNull(task, existingTask);

            return taskRepository.save(existingTask);
        } else {
            throw new TaskNotFoundException("Task not found with entered ID: " + id);
        }
    }

    @Override
    public String deleteTaskById(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            
            return String.format("Task %d deleted successfully.", id);            
        }
        
        throw new TaskNotFoundException(String.format("Task with ID %d not found.", id));        
    }
}
