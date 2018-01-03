package com.softnaptics.comptamain.controller;

import com.google.common.primitives.Doubles;
import com.softnaptics.comptaservice.ImpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/impots")
public class IRController {

    private ImpotService impotsService;

    @Autowired
    public IRController(ImpotService impotsService) {
        this.impotsService = impotsService;
    }

    @RequestMapping(path = "/ir", method = RequestMethod.GET)
    public String impotRevenu(Model model) {
        return "views/impots/calcul";
    }

    @RequestMapping(path = "/ir", method = RequestMethod.POST)
    public String calculImpotRevenu(Model model, @RequestParam("amount") String amountStr) {

        if (amountStr != null && !amountStr.isEmpty()) {
            double amount = Doubles.tryParse(amountStr);
            double result = impotsService.computeIR(amount);
            model.addAttribute("revenus", amount);
            model.addAttribute("result", result);
        }
        return "views/impots/calcul";
    }
}
