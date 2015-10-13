package ru.vmakarenko.entities.events;

import ru.vmakarenko.entities.DomainEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by VMakarenko on 7/15/2015.
 */
@Entity
@Table(name="events")
public class Event extends DomainEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "event_type")
    private String eventType;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "application_start_date")
    private Date applicationStartDate;

    @Column(name = "application_end_date")
    private Date applicationEndDate;

    @Column(name = "payment_start_date")
    private Date paymentStartDate;

    @Column(name = "payment_end_date")
    private Date paymentEndDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getApplicationStartDate() {
        return applicationStartDate;
    }

    public void setApplicationStartDate(Date applicationStartDate) {
        this.applicationStartDate = applicationStartDate;
    }

    public Date getApplicationEndDate() {
        return applicationEndDate;
    }

    public void setApplicationEndDate(Date applicationEndDate) {
        this.applicationEndDate = applicationEndDate;
    }

    public Date getPaymentStartDate() {
        return paymentStartDate;
    }

    public void setPaymentStartDate(Date paymentStartDate) {
        this.paymentStartDate = paymentStartDate;
    }

    public Date getPaymentEndDate() {
        return paymentEndDate;
    }

    public void setPaymentEndDate(Date paymentEndDate) {
        this.paymentEndDate = paymentEndDate;
    }
}