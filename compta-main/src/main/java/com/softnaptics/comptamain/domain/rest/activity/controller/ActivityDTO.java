package com.softnaptics.comptamain.domain.rest.activity.controller;

import com.softnaptics.comptamodel.entries.activity.Activity;
import com.softnaptics.comptamodel.entries.activity.property.ActivityPeriod;
import com.softnaptics.comptamodel.entries.activity.property.ActivityType;
import com.softnaptics.comptamodel.invoice.Invoice;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ActivityDTO {
    private int id;

    private String name;

    private double tva;

    private ActivityType type;

    private ActivityPeriod period;

    private double unitPriceHT;

    private Date pricingDate;

    private int invoiceId;

    public ActivityDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTva() {
        return tva;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public ActivityPeriod getPeriod() {
        return period;
    }

    public void setPeriod(ActivityPeriod period) {
        this.period = period;
    }

    public double getUnitPriceHT() {
        return unitPriceHT;
    }

    public void setUnitPriceHT(double unitPriceHT) {
        this.unitPriceHT = unitPriceHT;
    }

    public Date getPricingDate() {
        return pricingDate;
    }

    public void setPricingDate(Date pricingDate) {
        this.pricingDate = pricingDate;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    /**
     * Migrate this into a static class
     * @param entity
     * @return
     */
    static ActivityDTO fromEntity(Activity entity) {
        final ActivityDTO dto = new ActivityDTO();

        if (entity != null) {
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setInvoiceId(entity.getInvoice().getId());
            dto.setPeriod(entity.getPeriod());
            dto.setTva(entity.getTVA());
            dto.setType(entity.getType());
            dto.setPricingDate(entity.getDate());
            dto.setUnitPriceHT(entity.getUnitPriceHT());
        }

        return dto;
    }

    public static List<ActivityDTO> fromEntities(List<Activity> entities) {
        if (entities == null) {
            return Collections.emptyList();

        } else {
            return entities.parallelStream()
                    .map(activity -> fromEntity(activity))
                    .collect(Collectors.toList());
        }
    }

    /**
     * Migrate this method into a static class
     * @param dto
     * @return
     */
    static Activity toEntity(ActivityDTO dto) {
        if (dto != null) {
            final Activity entity = new Activity(
                    dto.getName(),
                    dto.getTva(),
                    dto.getUnitPriceHT(),
                    dto.getType(),
                    dto.getPeriod(),
                    dto.getPricingDate()
            );
            entity.setId(dto.getId());

            final Invoice shortInvoice = new Invoice();
            shortInvoice.setId(dto.getInvoiceId());
            entity.setInvoice(shortInvoice);

            return entity;
        }
        return new Activity();
    }

    public static List<Activity> toEntities(List<ActivityDTO> dtos) {
        if (dtos == null) {
            return Collections.emptyList();

        } else {
            return dtos.parallelStream()
                    .map(activity -> toEntity(activity))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public String toString() {
        return "ActivityDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tva=" + tva +
                ", type=" + type +
                ", period=" + period +
                ", unitPriceHT=" + unitPriceHT +
                ", pricingDate=" + pricingDate +
                ", invoiceId=" + invoiceId +
                '}';
    }
}
