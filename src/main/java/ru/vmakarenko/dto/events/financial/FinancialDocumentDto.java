package ru.vmakarenko.dto.events.financial;

import ru.vmakarenko.dto.common.FileEntryDto;
import ru.vmakarenko.entities.FakeDeleteDomainEntity;
import ru.vmakarenko.entities.common.files.FileEntry;
import ru.vmakarenko.entities.events.financial.FinancialDocumentStatus;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * Created by VMakarenko on 7/15/2015.
 */
public class FinancialDocumentDto extends FakeDeleteDomainEntity {
    private String typeName;
    private UUID typeId;
    private String statusId;
    private String statusDescription;
    private List<FileEntryDto> historyList;
    private String adminComment;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public UUID getTypeId() {
        return typeId;
    }

    public void setTypeId(UUID typeId) {
        this.typeId = typeId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public List<FileEntryDto> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<FileEntryDto> historyList) {
        this.historyList = historyList;
    }

    public String getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(String adminComment) {
        this.adminComment = adminComment;
    }
}
