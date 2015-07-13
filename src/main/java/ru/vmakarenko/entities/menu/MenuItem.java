package ru.vmakarenko.entities.menu;

import ru.vmakarenko.entities.DomainEntity;
import ru.vmakarenko.entities.common.DayOfWeek;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Table(name = "menu_items")
@Entity
public class MenuItem extends DomainEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private BigDecimal price;
    @OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL)
    private List<CustomEntry> customs;
    @Column(name="description")
    private String description;
    @Column(name="weight")
    private Integer weight;
    @ManyToMany
    @JoinTable(name="menu_edit_conn_week_day", joinColumns = @JoinColumn(name = "menu_item_id"), inverseJoinColumns = @JoinColumn(name = "week_day_id"))
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

    public List<CustomEntry> getCustoms() {
        return customs;
    }

    public void setCustoms(List<CustomEntry> customs) {
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

    public List<DayOfWeek> getDayOfWeekList() {
        return dayOfWeekList;
    }

    public void setDayOfWeekList(List<DayOfWeek> dayOfWeek) {
        this.dayOfWeekList = dayOfWeek;
    }
}
