package ru.vmakarenko.dto.common;

import ru.vmakarenko.entities.DomainEntity;
import ru.vmakarenko.entities.users.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VMakarenko on 7/15/2015.
 */
public class EmailMessageDto extends DomainEntity{
    private List<String> toList = new ArrayList<>();
    private Boolean sent = false;
    private String text;
    private String topic;


    public Boolean getSent() {
        return sent;
    }

    public void setSent(Boolean sent) {
        this.sent = sent;
    }

    public String getText() {
        return text;
    }

    public void setText(String msgText) {
        this.text = msgText;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<String> getToList() {
        return toList;
    }

    public void setToList(List<String> toList) {
        this.toList = toList;
    }
}
