package com.softnaptics.training.domain.service;

import com.softnaptics.training.domain.DomainHelper;
import com.softnaptics.training.domain.Task;
import com.softnaptics.training.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TaskService {

    private TaskRepository repository;

    @Autowired
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public void create(Task task) {
        //TODO Create task
    }

    public Task getLastCreatedTask() {
        return repository.findFirstByOrderByCreationDateDesc();
    }

    public List<Task> geAllTasks() {
        return repository.findByOrderByCreationDateDesc();
    }

    @PostConstruct
    private void loadData() {
        repository.save(DomainHelper.createTasks());
    }

}
