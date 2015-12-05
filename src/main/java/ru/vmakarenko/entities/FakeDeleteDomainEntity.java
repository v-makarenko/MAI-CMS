package ru.vmakarenko.entities;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.util.UUID;

/**
 * Created by vmakarenko on 22.04.2015.
 */
@MappedSuperclass
public class FakeDeleteDomainEntity extends DomainEntity{
    @Column(name = "active")
    private boolean active = true;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
