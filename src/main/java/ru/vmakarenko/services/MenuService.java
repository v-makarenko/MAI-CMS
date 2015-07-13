package ru.vmakarenko.services;

import ru.vmakarenko.dao.DayOfWeekDao;
import ru.vmakarenko.dao.MenuDao;
import ru.vmakarenko.dao.OrderDao;
import ru.vmakarenko.dao.filters.CommonFilter;
import ru.vmakarenko.dto.common.DayOfWeekDto;
import ru.vmakarenko.dto.menuEdit.MenuEditDto;
import ru.vmakarenko.dto.orders.OrderDto;
import ru.vmakarenko.entities.common.DayOfWeek;
import ru.vmakarenko.entities.menu.MenuItem;
import ru.vmakarenko.filters.MenuFilter;
import ru.vmakarenko.filters.OrderFilter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by VMakarenko on 4/28/2015.
 */
@Stateless
public class MenuService {

    @Inject
    private MenuDao menuDao;
    @Inject
    private MapperService mapperService;
    @Inject
    private DayOfWeekDao dayOfWeekDao;

    public List<MenuEditDto> getAll(MenuFilter filter){
        return menuDao.findAll(new CommonFilter(filter))
                .stream()
                .map(d -> mapperService.map(d, MenuEditDto.class))
                .collect(Collectors.toList());
    }

    public void insert(MenuEditDto menuEditDto) {
        menuDao.insert(fillMenuItem(mapperService.map(menuEditDto, MenuItem.class)));
    }


    public void update(MenuEditDto menuEditDto) {
        menuDao.update(fillMenuItem(mapperService.map(menuEditDto, MenuItem.class)));
    }

    public void delete(UUID id){
        menuDao.trueDelete(id);
    }

    private MenuItem fillMenuItem(MenuItem menuItem){
        if(menuItem.getCustoms() != null){
            menuItem.getCustoms().stream().forEach(c -> {
                c.setMenuItem(menuItem);
                if(c.getVariants() != null){
                    c.getVariants().stream().forEach(v -> v.setCustomEntry(c));
                }
            });
        }
        return menuItem;
    }

    public List<DayOfWeekDto> getAllDaysOfWeek() {
        return mapperService.map(dayOfWeekDao.findAll(), DayOfWeekDto.class);
    }
}
