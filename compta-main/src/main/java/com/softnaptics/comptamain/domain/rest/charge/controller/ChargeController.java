package com.softnaptics.comptamain.domain.rest.charge.controller;

import com.softnaptics.comptamain.domain.rest.charge.exception.ChargesNotFoundException;
import com.softnaptics.comptamodel.entries.charges.Charges;
import com.softnaptics.comptaservice.charge.IChargesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/charges")
public class ChargeController {
    private final IChargesService chargesService;

    @Autowired
    public ChargeController(IChargesService chargesService) {
        this.chargesService = chargesService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<ChargesDTO> list() {
        final List<Charges> charges = chargesService.listAll();

        return ChargesDTO.fromEntities(charges);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ChargesDTO create(@RequestBody ChargesDTO activity) {
        final Charges createdEntity = chargesService.create(ChargesDTO.toEntity(activity));
        return ChargesDTO.fromEntity(createdEntity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ChargesDTO read(@PathVariable(value = "id") int id) throws ChargesNotFoundException {
        final Charges charges = chargesService.read(id);
        if (charges == null) {
            throw new ChargesNotFoundException("Charges with id: " + id + " not found.");
        }
        return ChargesDTO.fromEntity(charges);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ChargesDTO update(@RequestBody ChargesDTO activity) {
        final Charges updatedEntity = chargesService.update(ChargesDTO.toEntity(activity));
        return ChargesDTO.fromEntity(updatedEntity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") int id) {
        chargesService.delete(id);
    }

    @ExceptionHandler(ChargesNotFoundException.class)
    public void handleInvoiceNotFoundException(ChargesNotFoundException exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }
}
