package ru.vmakarenko.entities.events;

import ru.vmakarenko.entities.DomainEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by VMakarenko on 9/12/2015.
 */
@Entity
@Table(name="events_conn_user_def_events")
public class EventUserDefEventConnection extends DomainEntity{
    @JoinColumn(name = "event_id")
    @ManyToOne
    private Event event;
    @JoinColumn(name = "user_def_event_id")
    @ManyToOne
    private UserDefinedEvent userEvent;
    @Column(name="date_from")
    @Temporal(TemporalType.DATE)
    private Date dateFrom;
    @Column(name="date_to")
    @Temporal(TemporalType.DATE)
    private Date dateTo;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public UserDefinedEvent getUserEvent() {
        return userEvent;
    }

    public void setUserEvent(UserDefinedEvent userEvent) {
        this.userEvent = userEvent;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}
