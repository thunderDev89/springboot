package com.softnaptics.comptaservice.activity;

import com.softnaptics.comptamodel.entries.activity.Activity;

import java.util.List;

public interface IActivityService {
    List<Activity> listAll();

    List<Activity> getForInvoice(int id);

    Activity create(Activity activity);

    Activity read(int id);

    Activity update(Activity activity);

    void delete(int id);
}
