package com.softnaptics.comptamain.domain.rest.charge.controller;

import com.softnaptics.comptamodel.entries.EntryType;
import com.softnaptics.comptamodel.entries.charges.Charges;
import com.softnaptics.comptamodel.invoice.Invoice;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ChargesDTO {

    private int id;

    private String name;

    private EntryType type;

    private double tva;

    private double unitPriceHT;

    private Date pricingDate;

    private int invoiceId;

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

    public EntryType getType() {
        return type;
    }

    public void setType(EntryType type) {
        this.type = type;
    }

    static ChargesDTO fromEntity(Charges entity) {
        final ChargesDTO dto = new ChargesDTO();

        if (entity != null) {
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setType(entity.getEntryType());
            dto.setTva(entity.getTVA());
            dto.setPricingDate(entity.getDate());
            dto.setUnitPriceHT(entity.getUnitPriceHT());
            dto.setInvoiceId(entity.getInvoice().getId());
        }
        return dto;
    }

    public static List<ChargesDTO> fromEntities(List<Charges> entities) {
        if (entities == null) {
            return Collections.emptyList();

        } else {
            return entities.parallelStream()
                    .map(charges -> fromEntity(charges))
                    .collect(Collectors.toList());
        }
    }

    static Charges toEntity(ChargesDTO dto) {
        if (dto != null) {
            final Charges entity = new Charges(
                    dto.getName(),
                    dto.getTva(),
                    dto.getUnitPriceHT(),
                    dto.getPricingDate(),
                    dto.getType()
            );
            entity.setId(dto.getId());

            final Invoice shortInvoice = new Invoice();
            shortInvoice.setId(dto.getInvoiceId());
            entity.setInvoice(shortInvoice);

            return entity;
        }
        return new Charges();
    }

    public static List<Charges> toEntities(List<ChargesDTO> dtos) {
        if (dtos == null) {
            return Collections.emptyList();

        } else {
            return dtos.parallelStream()
                    .map(charges -> toEntity(charges))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public String toString() {
        return "ChargesDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tva=" + tva +
                ", unitPriceHT=" + unitPriceHT +
                ", pricingDate=" + pricingDate +
                ", invoiceId=" + invoiceId +
                '}';
    }
}
