package ru.vmakarenko.dto.menuEdit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.vmakarenko.dto.common.CommonDto;
import ru.vmakarenko.dto.common.DayOfWeekDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MenuEditDto extends CommonDto{
    private BigDecimal price;
    private String name;
    private List<CustomDto> customs;
    private String description;
    private Integer weight;
    private List<DayOfWeekDto> dayOfWeekList;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CustomDto> getCustoms() {
        return customs;
    }

    public void setCustoms(List<CustomDto> customs) {
        this.customs = customs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public List<DayOfWeekDto> getDayOfWeekList() {
        return dayOfWeekList;
    }

    public void setDayOfWeekList(List<DayOfWeekDto> dayOfWeekList) {
        this.dayOfWeekList = dayOfWeekList;
    }
}
