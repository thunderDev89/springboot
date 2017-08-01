package com.softnaptics.training.repository;

import com.softnaptics.training.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findByOrderByCreationDateDesc();

    Task findFirstByOrderByCreationDateDesc();
}
