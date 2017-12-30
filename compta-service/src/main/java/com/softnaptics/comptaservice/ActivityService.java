package com.softnaptics.comptaservice;

import com.softnaptics.comptamodel.entries.activity.Activity;
import com.softnaptics.comptarepository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ActivityService {

    private final ActivityRepository repository;

    @Autowired
    public ActivityService(ActivityRepository repository) {
        this.repository = repository;
    }

    public Activity save(Activity activity) {
        return repository.save(activity);
    }

    public Collection<Activity> saveAll(Collection<Activity> activities) {
        return repository.save(activities);
    }

    public Collection<Activity> getAll() {
        return repository.findAll();
    }
}
