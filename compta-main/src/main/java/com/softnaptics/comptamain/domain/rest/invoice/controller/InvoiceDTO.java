package com.softnaptics.comptamain.domain.rest.invoice.controller;

import com.softnaptics.comptamain.domain.rest.activity.controller.ActivityDTO;
import com.softnaptics.comptamain.domain.rest.charge.controller.ChargesDTO;
import com.softnaptics.comptamodel.invoice.Invoice;

import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InvoiceDTO {

    private Integer id;
    private String name;
    private Month month;
    private List<ActivityDTO> activities;
    private List<ChargesDTO> charges;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public List<ActivityDTO> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityDTO> activities) {
        this.activities = activities;
    }

    public List<ChargesDTO> getCharges() {
        return charges;
    }

    public void setCharges(List<ChargesDTO> charges) {
        this.charges = charges;
    }

    static InvoiceDTO fromEntity(Invoice entity) {
        final InvoiceDTO dto = new InvoiceDTO();

        if (entity != null) {
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setMonth(entity.getMonth());
            dto.setActivities(
                    ActivityDTO.fromEntities(
                            entity.getActivities()
                    )
            );
            dto.setCharges(
                    ChargesDTO.fromEntities(
                            entity.getCharges()
                    )
            );
        }
        return dto;
    }

    static List<InvoiceDTO> fromEntities(List<Invoice> entities) {
        if (entities == null) {
            return Collections.emptyList();

        } else {
            return entities.parallelStream()
                    .map(invoice -> fromEntity(invoice))
                    .collect(Collectors.toList());
        }
    }

    static Invoice toEntity(InvoiceDTO dto) {
        final Invoice entity = new Invoice();

        if (dto != null) {
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setMonth(dto.getMonth());
            entity.setActivities(
                    ActivityDTO.toEntities(
                            dto.getActivities()
                    )
            );
            entity.setCharges(
                    ChargesDTO.toEntities(
                            dto.getCharges()
                    )
            );
        }

        return entity;
    }

    @Override
    public String toString() {
        return "InvoiceDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", month=" + month +
                ", activities=" + activities +
                '}';
    }
}
