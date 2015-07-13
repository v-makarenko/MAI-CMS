package ru.vmakarenko.services;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ru.vmakarenko.dto.common.DayOfWeekDto;
import ru.vmakarenko.dto.menuEdit.CustomDto;
import ru.vmakarenko.dto.menuEdit.MenuEditDto;
import ru.vmakarenko.dto.menuEdit.VariantDto;
import ru.vmakarenko.dto.orders.OrderDto;
import ru.vmakarenko.dto.users.UserDto;
import ru.vmakarenko.dto.users.UserSignUpDto;
import ru.vmakarenko.entities.common.DayOfWeek;
import ru.vmakarenko.entities.orders.Order;
import ru.vmakarenko.entities.menu.CustomEntry;
import ru.vmakarenko.entities.menu.MenuItem;
import ru.vmakarenko.entities.menu.VariantEntry;
import ru.vmakarenko.entities.users.AbstractUser;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@ApplicationScoped
public class MapperService {

    private MapperFactory mapperFactory;

    @PostConstruct
    public void init(){
        mapperFactory = new DefaultMapperFactory.Builder().build();


        // users stuff
        mapperFactory.classMap(UserSignUpDto.class, AbstractUser.class)
                .byDefault().register();

        mapperFactory.classMap(AbstractUser.class, UserDto.class)
                .byDefault().register();


        // orders stuff

        mapperFactory.classMap(Order.class , OrderDto.class)
                .field("client.surname", "clientName")
                .field("restaurant.name", "restaurantName")
                .byDefault().register();

        // menu stuff
        mapperFactory.classMap(MenuItem.class , MenuEditDto.class)
                .byDefault().register();
        mapperFactory.classMap(CustomEntry.class , CustomDto.class)
                .byDefault().register();
        mapperFactory.classMap(VariantEntry.class , VariantDto.class)
                .byDefault().register();
        mapperFactory.classMap(DayOfWeek.class , DayOfWeekDto.class)
                .byDefault().register();


    }

    public MapperFactory getMapperFactory() {
        return mapperFactory;
    }

    public<E,D> D map(E from, Class<D> toClass){
        return mapperFactory.getMapperFacade().map(from, toClass);
    }
    public<E,D> List<D> map(List<E> from, Class<D> toClass){
        return from.parallelStream().map(fromItem -> mapperFactory.getMapperFacade().map(fromItem, toClass)).collect(Collectors.toList());
    }
}
