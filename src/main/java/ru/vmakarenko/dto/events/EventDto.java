package ru.vmakarenko.dto.events;

import ru.vmakarenko.dto.common.SectionDto;
import ru.vmakarenko.dto.common.WorkingPlaceDto;
import ru.vmakarenko.dto.events.financial.FinancialDocumentTypeDto;
import ru.vmakarenko.dto.users.UserNoPassDto;
import ru.vmakarenko.entities.DomainEntity;
import ru.vmakarenko.entities.common.SimpleStringValue;

import java.util.Date;
import java.util.List;

/**
 * Created by VMakarenko on 7/15/2015.
 */
public class EventDto extends DomainEntity {
    private String name;
    private String shortName;
    private String eventType;
    private Date createDate;
    private Date startDate;
    private Date endDate;
    private Date applicationStartDate;
    private Date applicationEndDate;
    private Date paymentStartDate;
    private Date paymentEndDate;
    private Date infoRecEndDate;
    private Date printEndDate;

    private Boolean present;


    private List<UserNoPassDto> userList;
    private List<SectionDto> sectionList;
    private List<SimpleStringValue> techPeopleList;
    private List<WorkingPlaceDto> orgOrganisationList;
    private List<FinancialDocumentTypeDto> financialDocumentTypeList;


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

    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }

    public List<UserNoPassDto> getUserList() {
        return userList;
    }

    public void setUserList(List<UserNoPassDto> userList) {
        this.userList = userList;
    }

    public List<SectionDto> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<SectionDto> sectionList) {
        this.sectionList = sectionList;
    }

    public List<SimpleStringValue> getTechPeopleList() {
        return techPeopleList;
    }

    public void setTechPeopleList(List<SimpleStringValue> techPeopleList) {
        this.techPeopleList = techPeopleList;
    }

    public List<WorkingPlaceDto> getOrgOrganisationList() {
        return orgOrganisationList;
    }

    public void setOrgOrganisationList(List<WorkingPlaceDto> orgOrganisationList) {
        this.orgOrganisationList = orgOrganisationList;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<FinancialDocumentTypeDto> getFinancialDocumentTypeList() {
        return financialDocumentTypeList;
    }

    public void setFinancialDocumentTypeList(List<FinancialDocumentTypeDto> financialDocumentTypeList) {
        this.financialDocumentTypeList = financialDocumentTypeList;
    }
}
