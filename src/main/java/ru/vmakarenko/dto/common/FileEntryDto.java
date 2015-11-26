package ru.vmakarenko.dto.common;

import ru.vmakarenko.entities.DomainEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Created by VMakarenko on 13.11.2015.
 */
public class FileEntryDto extends DomainEntity {
    private String filename;
    private String extension;
    private UUID contentId;
    private byte[] content;
    public UUID getPath() {
        return contentId;
    }

    public void setPath(UUID contentId) {
        this.contentId = contentId;
    }

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

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
