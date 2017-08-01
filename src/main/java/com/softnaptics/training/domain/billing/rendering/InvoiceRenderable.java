package com.softnaptics.training.domain.billing.rendering;

import com.softnaptics.training.domain.billing.Activity;

import java.util.Set;

/**
 * @author Audrik !
 */
public interface InvoiceRenderable {

    void accept(Renderer renderer);

    Set<Activity> getActivities();
}
