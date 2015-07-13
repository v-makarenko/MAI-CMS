package ru.vmakarenko.entities.menu;

import ru.vmakarenko.entities.DomainEntity;
import ru.vmakarenko.entities.common.DayOfWeek;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Table(name = "menu_variants")
@Entity
public class VariantEntry extends DomainEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name="weight")
    private Integer weight;
    @ManyToOne
    @JoinColumn(name = "menu_custom_id")
    private CustomEntry customEntry;
    @ManyToMany
    @JoinTable(name="menu_variants_conn_week_day", joinColumns = @JoinColumn(name = "menu_variant_id"), inverseJoinColumns = @JoinColumn(name = "week_day_id"))
    private List<DayOfWeek> dayOfWeekList;

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

    public CustomEntry getCustomEntry() {
        return customEntry;
    }

    public void setCustomEntry(CustomEntry customsEntry) {
        this.customEntry = customsEntry;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public List<DayOfWeek> getDayOfWeekList() {
        return dayOfWeekList;
    }

    public void setDayOfWeekList(List<DayOfWeek> dayOfWeek) {
        this.dayOfWeekList = dayOfWeek;
    }
}
