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
public class CustomDto extends CommonDto{
    private String name;
    private List<VariantDto> variants;
    private BigDecimal price;
    private Integer weight;
    private List<DayOfWeekDto> dayOfWeekList;

    public List<VariantDto> getVariants() {
        return variants;
    }

    public void setVariants(List<VariantDto> variants) {
        this.variants = variants;
    }

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

    public List<DayOfWeekDto> getDayOfWeekList() {
        return dayOfWeekList;
    }

    public void setDayOfWeekList(List<DayOfWeekDto> dayOfWeekList) {
        this.dayOfWeekList = dayOfWeekList;
    }
}
