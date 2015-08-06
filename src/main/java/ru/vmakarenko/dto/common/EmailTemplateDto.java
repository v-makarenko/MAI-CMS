package ru.vmakarenko.dto.common;

import ru.vmakarenko.entities.DomainEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by VMakarenko on 7/15/2015.
 */
public class EmailTemplateDto extends DomainEntity{
    private String name;
    private Boolean text;

    public Boolean getText() {
        return text;
    }

    public void setText(Boolean text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}