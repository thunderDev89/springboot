package com.softnaptics.comptamain.domain.rest.activity.controller;

import com.softnaptics.comptamain.domain.rest.activity.exception.ActivityNotFoundException;
import com.softnaptics.comptamodel.entries.activity.Activity;
import com.softnaptics.comptaservice.activity.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {
    private IActivityService activityService;

    @Autowired
    public ActivityController(IActivityService activityService) {
        this.activityService = activityService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<ActivityDTO> list() {
        final List<Activity> activities = activityService.listAll();

        return ActivityDTO.fromEntities(activities);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ActivityDTO create(@RequestBody ActivityDTO activity) {
        final Activity createdEntity = activityService.update(ActivityDTO.toEntity(activity));
        return ActivityDTO.fromEntity(createdEntity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ActivityDTO read(@PathVariable(value = "id") int id) throws ActivityNotFoundException {
        final Activity activity = activityService.read(id);
        if (activity == null) {
            throw new ActivityNotFoundException("Activity with id: " + id + " not found.");
        }
        return ActivityDTO.fromEntity(activity);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ActivityDTO update(@RequestBody ActivityDTO activity) {
        final Activity updatedEntity = activityService.update(ActivityDTO.toEntity(activity));
        return ActivityDTO.fromEntity(updatedEntity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") int id) {
        activityService.delete(id);
    }

    @ExceptionHandler(ActivityNotFoundException.class)
    public void handleInvoiceNotFoundException(ActivityNotFoundException exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }
}
