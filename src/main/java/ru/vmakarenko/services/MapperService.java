package ru.vmakarenko.services;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ru.vmakarenko.common.UserType;
import ru.vmakarenko.dto.common.EmailMessageDto;
import ru.vmakarenko.dto.common.EmailTemplateDto;
import ru.vmakarenko.dto.common.MessageDto;
import ru.vmakarenko.dto.users.UserDto;
import ru.vmakarenko.entities.messages.EmailMessage;
import ru.vmakarenko.entities.messages.EmailTemplate;
import ru.vmakarenko.entities.messages.InnerMessage;
import ru.vmakarenko.entities.users.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@ApplicationScoped
public class MapperService {

    private MapperFactory mapperFactory;

    @PostConstruct
    public void init() {
        mapperFactory = new DefaultMapperFactory.Builder().build();


        // users stuff
        mapperFactory.classMap(User.class, UserDto.class)
                .customize(new CustomMapper<User, UserDto>() {
                    @Override
                    public void mapAtoB(User user, UserDto userDto, MappingContext context) {
                        userDto.setUserType(user.getUserType().name());
                    }

                    @Override
                    public void mapBtoA(UserDto userDto, User user, MappingContext context) {
                        user.setUserType(UserType.valueOf(userDto.getUserType().toUpperCase()));
                    }
                })
                .byDefault().register();


        mapperFactory.classMap(EmailMessage.class, EmailMessageDto.class)
                .byDefault().register();


        mapperFactory.classMap(EmailTemplate.class, EmailTemplateDto.class)
                .byDefault().register();


        mapperFactory.classMap(InnerMessage.class, MessageDto.class)
                .fieldAToB("from.id", "from").fieldAToB("to.id", "to")
                .customize(
                        new CustomMapper<InnerMessage, MessageDto>() {
                            public void mapAtoB(InnerMessage a, MessageDto b, MappingContext context) {
                                // add your custom mapping code here
                                if (a.getFrom() != null) {
                                    b.setFromName(a.getFrom().getSurname() + " " + a.getFrom().getName());
                                }
                            }
                        }
                ).byDefault().register();

    }

    public MapperFactory getMapperFactory() {
        return mapperFactory;
    }

    public <E, D> D map(E from, Class<D> toClass) {
        if (from == null) return null;
        return mapperFactory.getMapperFacade().map(from, toClass);
    }

    public <E, D> List<D> map(List<E> from, Class<D> toClass) {
        if (from == null) return new ArrayList<>();
        return from.parallelStream().map(fromItem -> mapperFactory.getMapperFacade().map(fromItem, toClass)).collect(Collectors.toList());
    }

}
