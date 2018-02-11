package com.softnaptics.comptaservice.activity;

import com.softnaptics.comptamodel.entries.activity.Activity;
import com.softnaptics.comptarepository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ActivityService implements IActivityService {

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

    @Override
    public List<Activity> listAll() {
        return repository.findAllByOrderByIdDesc();
    }

    @Override
    public List<Activity> getForInvoice(int id) {
        return repository.findAllByInvoiceIdOrderByIdDesc(id);
    }

    @Override
    public Activity create(Activity activity) {
        return repository.save(activity);
    }

    @Override
    public Activity read(int id) {
        return repository.findOne(id);
    }

    @Override
    public Activity update(Activity activity) {
        return repository.save(activity);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }
}
