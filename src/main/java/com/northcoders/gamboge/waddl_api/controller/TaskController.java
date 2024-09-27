package com.northcoders.gamboge.waddl_api.controller;

import com.northcoders.gamboge.waddl_api.service.TaskManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/waddl")
public class TaskController {

    @Autowired
    TaskManagerService taskManagerService;



}
