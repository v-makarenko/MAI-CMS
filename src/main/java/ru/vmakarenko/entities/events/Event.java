package ru.vmakarenko.entities.events;

import ru.vmakarenko.entities.DomainEntity;
import ru.vmakarenko.entities.FakeDeleteDomainEntity;
import ru.vmakarenko.entities.common.SimpleStringValue;
import ru.vmakarenko.entities.events.financial.FinancialDocumentType;
import ru.vmakarenko.entities.users.WorkingPlace;
import ru.vmakarenko.entities.users.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by VMakarenko on 7/15/2015.
 */
@Entity
@Table(name="events")
public class Event extends FakeDeleteDomainEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "short_name")
    private String shortName;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="users_events", joinColumns = @JoinColumn(name = "events_id"), inverseJoinColumns = @JoinColumn(name="users_id"))
    private List<User> userList = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="events_tech_people", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name="people_id"))
    private List<SimpleStringValue> techPeopleList = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="events_org_orgs", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name="org_id"))
    private List<WorkingPlace> orgOrganisationList = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Section> sectionList = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<FinancialDocumentType> financialDocumentTypeList = new ArrayList<>();

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

    public List<Section> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<Section> sectionList) {
        this.sectionList = sectionList;
    }

    public List<SimpleStringValue> getTechPeopleList() {
        return techPeopleList;
    }

    public void setTechPeopleList(List<SimpleStringValue> techPeopleList) {
        this.techPeopleList = techPeopleList;
    }

    public List<WorkingPlace> getOrgOrganisationList() {
        return orgOrganisationList;
    }

    public void setOrgOrganisationList(List<WorkingPlace> orgOrganisationList) {
        this.orgOrganisationList = orgOrganisationList;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<FinancialDocumentType> getFinancialDocumentTypeList() {
        return financialDocumentTypeList;
    }

    public void setFinancialDocumentTypeList(List<FinancialDocumentType> financialDocumentTypeList) {
        this.financialDocumentTypeList = financialDocumentTypeList;
    }
}
