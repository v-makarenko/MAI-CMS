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

    public <E extends DomainEntity> E id(UUID id) {
        this.id = id;
        return (E)this;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DomainEntity that = (DomainEntity) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);

    }

    @Override
    public final int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @PrePersist
    public void prePersist(){
        id = UUID.randomUUID();
    }
}
