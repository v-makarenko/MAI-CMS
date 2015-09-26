package ru.vmakarenko.entities;

import ru.vmakarenko.entities.events.Event;
import ru.vmakarenko.entities.users.User;

import javax.persistence.*;

/**
 * Created by VMakarenko on 9/12/2015.
 */
@Entity
@Table(name="events")
public class Report extends DomainEntity{
    @ManyToOne
    @JoinColumn(name = "reporter_id")
    private User reporter;
    @ManyToOne
    @JoinColumn(name = "Creator_id")
    private User creator;
    @Column(name="reporter_name")
    private String reportName;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
//    @ManyToOne
//    @JoinColumn(name = "file_id")
//    private FileEntry file;
//    @Column(name = "report_status")
//    private ReportStatus reportStatus;

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

//    public FileEntry getFile() {
//        return file;
//    }
//
//    public void setFile(FileEntry file) {
//        this.file = file;
//    }
//
//    public ReportStatus getReportStatus() {
//        return reportStatus;
//    }
//
//    public void setReportStatus(ReportStatus reportStatus) {
//        this.reportStatus = reportStatus;
//    }
}
