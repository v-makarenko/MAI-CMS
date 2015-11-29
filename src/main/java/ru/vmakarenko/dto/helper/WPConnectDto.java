package ru.vmakarenko.dto.helper;

import ru.vmakarenko.dto.common.IdDto;

import java.util.List;
import java.util.UUID;

/**
 * Created by VMakarenko on 29.11.2015.
 */
public class WPConnectDto {
    private List<IdDto> wpReduceList;
    private UUID originId;

    public UUID getOriginId() {
        return originId;
    }

    public void setOriginId(UUID originId) {
        this.originId = originId;
    }

    public List<IdDto> getWpReduceList() {
        return wpReduceList;
    }

    public void setWpReduceList(List<IdDto> wpReduceList) {
        this.wpReduceList = wpReduceList;
    }
}
