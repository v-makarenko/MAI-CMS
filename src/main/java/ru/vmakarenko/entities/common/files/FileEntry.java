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
@Table(name="file_entries")
public class FileEntry extends DomainEntity {
    @Column(name ="filename")
    private String filename;

    @Column(name="extension")
    private String extension;

    @Type(type="pg-uuid")
    @Column(name="content_id")
    private UUID contentId;

    @Column(name="content_type")
    private String contentType;

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public UUID getContentId() {
        return contentId;
    }

    public void setContentId(UUID contentId) {
        this.contentId = contentId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
