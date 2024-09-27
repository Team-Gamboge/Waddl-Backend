package com.northcoders.gamboge.waddl_api.service;

import com.northcoders.gamboge.waddl_api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskManagerServiceImpl implements TaskManagerService {

    @Autowired
    TaskRepository taskRepository;
}
