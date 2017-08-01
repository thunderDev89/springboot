package com.softnaptics.training.controller;

import com.softnaptics.training.domain.Task;
import com.softnaptics.training.domain.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HomeController {

    private TaskService taskService;

    @Autowired
    public HomeController(TaskService taskService) {
        this.taskService = taskService;
    }

//    @RequestMapping("/")
    public String home() {
        return "Welcome home :)";
    }

    @RequestMapping("/scheduler")
    public String schedulerTasks() {
        return "Welcome to my future task's scheduler";
    }
}
