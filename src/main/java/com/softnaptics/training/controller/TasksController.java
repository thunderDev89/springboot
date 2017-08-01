package com.softnaptics.training.controller;


import com.softnaptics.training.domain.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/tasks")
public class TasksController {

    private TaskService taskService;

    @Autowired
    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("pageTitle", "All tasks you scheduled");
        model.addAttribute("list", true);
        model.addAttribute("tasks", taskService.geAllTasks());
        return "views/list";
    }

    @RequestMapping(path = "/latest", method = RequestMethod.GET)
    public String latest(Model model) {
        model.addAttribute("pageTitle", "The last one task... for now");
        model.addAttribute("detail", true);
        model.addAttribute("task", taskService.getLastCreatedTask());
        return "views/detail";
    }
}
