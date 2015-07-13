package ru.vmakarenko.entities.menu;

import ru.vmakarenko.entities.DomainEntity;
import ru.vmakarenko.entities.common.DayOfWeek;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Table(name = "menu_customs")
@Entity
public class CustomEntry extends DomainEntity {
    @Column(name="name")
    private String name;
    @Column(name="price")
    private BigDecimal price;
    @Column(name="weight")
    private Integer weight;
    @OneToMany(mappedBy="customEntry", cascade = CascadeType.ALL)
    private List<VariantEntry> variants;
    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;
    @Enumerated(EnumType.ORDINAL)
    @ManyToMany
    @JoinTable(name="menu_customs_conn_week_day", joinColumns = @JoinColumn(name = "menu_custom_id"), inverseJoinColumns = @JoinColumn(name = "week_day_id"))
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

    public List<VariantEntry> getVariants() {
        return variants;
    }

    public void setVariants(List<VariantEntry> variants) {
        this.variants = variants;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
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
