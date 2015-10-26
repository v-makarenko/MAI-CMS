package ru.vmakarenko.entities.events;

import ru.vmakarenko.entities.DomainEntity;
import ru.vmakarenko.entities.users.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by VMakarenko on 7/15/2015.
 */
@Entity
@Table(name="events")
public class Event extends DomainEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "event_type")
    private String eventType;

    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "application_start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date applicationStartDate;

    @Column(name = "application_end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date applicationEndDate;

    @Column(name = "payment_start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentStartDate;

    @Column(name = "payment_end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentEndDate;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "info_rec_end_date")
    private Date infoRecEndDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "print_end_date")
    private Date printEndDate;

    @ManyToMany
    @JoinTable(name="users_events", joinColumns = @JoinColumn(name = "events_id"), inverseJoinColumns = @JoinColumn(name="users_id"))
    private List<User> userList;

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

    public Date getInfoRecEndDate() {
        return infoRecEndDate;
    }

    public void setInfoRecEndDate(Date infoRecEndDate) {
        this.infoRecEndDate = infoRecEndDate;
    }

    public Date getPrintEndDate() {
        return printEndDate;
    }

    public void setPrintEndDate(Date printEndDate) {
        this.printEndDate = printEndDate;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public boolean isParticipating(UUID userId){
        return userList.stream().anyMatch(user -> user.getId().equals(userId));
    }
}
