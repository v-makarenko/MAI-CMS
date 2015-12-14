package ru.vmakarenko.dto.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.vmakarenko.entities.DomainEntity;

import java.util.Date;
import java.util.UUID;

/**
 * Created by VMakarenko on 7/15/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDto extends DomainEntity{
    private UUID fromId;
    private String fromName;
    private UUID toId;
    private String text;
    private Long time;
    private UUID fileId;
    private String fileName;
    private boolean fileIsImage;
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public UUID getFromId() {
        return fromId;
    }

    public void setFromId(UUID fromId) {
        this.fromId = fromId;
    }

    public UUID getToId() {
        return toId;
    }

    public void setToId(UUID toId) {
        this.toId = toId;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public UUID getFileId() {
        return fileId;
    }

    public void setFileId(UUID fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isFileIsImage() {
        return fileIsImage;
    }

    public void setFileIsImage(boolean fileIsImage) {
        this.fileIsImage = fileIsImage;
    }
}
