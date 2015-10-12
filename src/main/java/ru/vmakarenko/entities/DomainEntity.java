package ru.vmakarenko.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by vmakarenko on 22.04.2015.
 */
@MappedSuperclass
public class DomainEntity {
    @Id
    @Column(name="id")
    @Type(type="pg-uuid")
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @PrePersist
    public void prePersist(){
        id = UUID.randomUUID();
    }
}
