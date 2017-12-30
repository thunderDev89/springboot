package com.softnaptics.comptamodel.entries;

import com.softnaptics.comptamodel.invoice.Invoice;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractEntry implements Entry {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private double tva;

    @Column(name = "unit_price_ht")
    private double unitPriceHT;

    @Column(columnDefinition = "DATE DEFAULT CURRENT_DATE")
    //@Temporal(TemporalType.DATE)
    private Date pricingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    public AbstractEntry() {
    }

    public AbstractEntry(String name, double tva, double amountHT, Date pricingDate) {
        this.name = name;
        this.tva = tva;
        this.unitPriceHT = amountHT;
        this.pricingDate = pricingDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getTVA() {
        return tva;
    }

    @Override
    public double getUnitPriceHT() {
        return unitPriceHT;
    }

    @Override
    public Date getDate() {
        return pricingDate;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntry that = (AbstractEntry) o;

        if (id != that.id) return false;
        if (Double.compare(that.tva, tva) != 0) return false;
        if (Double.compare(that.unitPriceHT, unitPriceHT) != 0) return false;
        if (!name.equals(that.name)) return false;
        if (!pricingDate.equals(that.pricingDate)) return false;
        if (this.getEntryType() != that.getEntryType()) return false;
        return invoice.equals(that.invoice);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id + getEntryType().hashCode();
        result = 31 * result + name.hashCode();
        temp = Double.doubleToLongBits(tva);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(unitPriceHT);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + pricingDate.hashCode();
        result = 31 * result + invoice.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AbstractEntry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tva=" + tva +
                ", unitPriceHT=" + unitPriceHT +
                ", pricingDate=" + pricingDate +
                ", invoice=" + invoice +
                '}';
    }
}
