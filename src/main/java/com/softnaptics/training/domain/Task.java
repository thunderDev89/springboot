package com.softnaptics.training.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Audrik ! on 21/05/2017.
 */
@Entity
public class Task {
    public static int PRIORITY_HIGH = 3;
    public static int PRIORITY_MEDIUM = 2;
    public static int PRIORITY_LOW = 1;

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private int priority;
    private Date creationDate;
    private Date startDate;
    private Date endDate;

    public Task() {
    }

    public Task(String name, String description, int priority, Date endDate) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.creationDate = new Date();
        this.startDate = new Date();
        this.endDate = endDate;
    }

    public Task(String name, String description, int priority, Date startDate, Date endDate) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.creationDate = new Date();
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", creationDate=" + creationDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
