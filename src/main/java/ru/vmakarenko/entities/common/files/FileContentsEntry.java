package ru.vmakarenko.entities.common.files;

import org.hibernate.annotations.Type;
import ru.vmakarenko.entities.DomainEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Created by VMakarenko on 13.11.2015.
 */
@Entity
@Table(name="file_content_entries")
public class FileContentsEntry extends DomainEntity {
    @Column(name = "content")
    private byte[] content;

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
