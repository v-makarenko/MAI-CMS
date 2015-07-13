package ru.vmakarenko.entities.orders;

import ru.vmakarenko.entities.DomainEntity;
import ru.vmakarenko.entities.menu.MenuItem;
import ru.vmakarenko.entities.users.PhysUser;
import ru.vmakarenko.entities.users.RestUser;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Entity
@Table(name="orders")
public class Order extends DomainEntity {
    @ManyToOne
    @JoinColumn(name="client_id")
    private PhysUser client;
    @ManyToOne
    @JoinColumn(name="restaraunt_id")
    private RestUser restaurant;
    @Column(name = "time")
    private long time;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "paid")
    private boolean paid;
    @Column(name = "price")
    private BigDecimal price;
    @ManyToMany
    @JoinTable(name = "order_menu_items", joinColumns = {@JoinColumn(name = "order_id")}, inverseJoinColumns = {@JoinColumn(name = "menu_item_id")})
    private List<MenuItem> menuItemList;


    public PhysUser getClient() {
        return client;
    }

    public void setClient(PhysUser client) {
        this.client = client;
    }

    public RestUser getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestUser restaurant) {
        this.restaurant = restaurant;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    public void setMenuItemList(List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }
}
