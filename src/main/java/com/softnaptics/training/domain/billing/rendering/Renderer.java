package com.softnaptics.training.domain.billing.rendering;

/**
 * @author Audrik !
 */
public interface Renderer {

    void visit(InvoiceRenderable invoice);

    void render();
}
