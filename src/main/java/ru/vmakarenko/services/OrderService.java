package ru.vmakarenko.services;

import ru.vmakarenko.dao.OrderDao;
import ru.vmakarenko.dao.filters.CommonFilter;
import ru.vmakarenko.dto.orders.OrderDto;
import ru.vmakarenko.entities.orders.Order;
import ru.vmakarenko.filters.OrderFilter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by VMakarenko on 4/28/2015.
 */
@Stateless
public class OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private MapperService mapperService;

    public List<OrderDto> getAll(OrderFilter filter){
        return orderDao.findAll(new CommonFilter(filter))
                .parallelStream()
                .map(d -> mapperService.map(d, OrderDto.class))
                .collect(Collectors.toList());
    }

    public void insert(OrderDto orderDto){
        orderDao.insert(mapperService.map(orderDto, Order.class));
    }
}
