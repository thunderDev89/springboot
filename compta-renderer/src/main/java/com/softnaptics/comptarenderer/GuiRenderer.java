package com.softnaptics.comptarenderer;

import com.softnaptics.comptarenderer.invoice.AmountsByCat;

import java.util.List;

public interface GuiRenderer {
    List<AmountsByCat> getAmountsList();
}
