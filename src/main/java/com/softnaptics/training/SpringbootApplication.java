package com.softnaptics.training;

import com.softnaptics.training.domain.Task;
import com.softnaptics.training.domain.service.TaskService;
import com.softnaptics.training.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.Date;

@SpringBootApplication
public class SpringbootApplication {
	private static final Logger logger = LoggerFactory.getLogger(SpringbootApplication.class.getSimpleName());

	// TaskRepository
	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private TaskService service;

	@Bean
	public Task task() {
		return new Task("Call comptable", "Voir actualisation comptable", Task.PRIORITY_HIGH, new Date());
	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SpringbootApplication.class, args);
	}

	//PostConstruct
	@PostConstruct
	void seeTasks() {
//		logger.info("seeTasks method called...");
//		for (Task task : taskRepository.findAll()) {
//			logger.info(task.toString());
//		}
	}
}
