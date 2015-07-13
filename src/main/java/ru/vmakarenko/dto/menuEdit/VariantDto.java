package ru.vmakarenko.dto.menuEdit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.vmakarenko.dto.common.CommonDto;

import java.math.BigDecimal;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VariantDto extends CommonDto{
    private String name;
    private BigDecimal price;
    private Integer weight;
    private Integer dayOfWeek;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
