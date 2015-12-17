package ru.vmakarenko.entities.events.financial;

import ru.vmakarenko.entities.DomainEntity;
import ru.vmakarenko.entities.FakeDeleteDomainEntity;
import ru.vmakarenko.entities.common.files.FileEntry;
import ru.vmakarenko.entities.users.User;

import javax.persistence.*;
import java.util.List;

/**
 * Created by VMakarenko on 7/15/2015.
 */
@Entity
@Table(name="financial_documents")
public class FinancialDocument extends DomainEntity {
    @ManyToOne
    @JoinColumn(name = "type_id")
    private FinancialDocumentType type;

    @Column(name="status")
    private FinancialDocumentStatus status;

    @ManyToMany
    @JoinTable(name = "financial_documents_file_entries", joinColumns = @JoinColumn(name = "financial_document_id"), inverseJoinColumns = @JoinColumn(name="file_entry_id"))
    private List<FileEntry> historyList;

    @Column(name="admin_comment")
    private String adminComment;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    public FinancialDocument() {
    }

    public FinancialDocumentType getType() {
        return type;
    }

    public void setType(FinancialDocumentType type) {
        this.type = type;
    }

    public FinancialDocumentStatus getStatus() {
        return status;
    }

    public void setStatus(FinancialDocumentStatus status) {
        this.status = status;
    }

    public List<FileEntry> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<FileEntry> historyList) {
        this.historyList = historyList;
    }

    public String getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(String adminComment) {
        this.adminComment = adminComment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
