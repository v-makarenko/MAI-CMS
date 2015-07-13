package ru.vmakarenko.dto.orders;

import ru.vmakarenko.dto.common.CommonDto;

import java.math.BigDecimal;

/**
 * Created by VMakarenko on 4/25/2015.
 */
public class OrderDto extends CommonDto {
    private String clientName;
    private String restaurantName;
    private Long time;
    private BigDecimal price;
    private Boolean paid;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }
}
