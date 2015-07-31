package ru.vmakarenko.services;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ru.vmakarenko.dto.common.EmailMessageDto;
import ru.vmakarenko.dto.common.EmailTemplateDto;
import ru.vmakarenko.dto.common.MessageDto;
import ru.vmakarenko.dto.users.UserDto;
import ru.vmakarenko.entities.EmailMessage;
import ru.vmakarenko.entities.EmailTemplate;
import ru.vmakarenko.entities.Message;
import ru.vmakarenko.entities.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
                .byDefault().register();


        mapperFactory.classMap(EmailMessage.class, EmailMessageDto.class)
                .byDefault().register();


        mapperFactory.classMap(EmailTemplate.class, EmailTemplateDto.class)
                .byDefault().register();


        mapperFactory.classMap(Message.class, MessageDto.class)
                .fieldAToB("from.id", "from").fieldAToB("to.id", "to")
                .customize(
                        new CustomMapper<Message, MessageDto>() {
                            public void mapAtoB(Message a, MessageDto b, MappingContext context) {
                                // add your custom mapping code here
                                if (a.getFrom() != null) {
                                    b.setFromName(a.getFrom().getLastName() + " " + a.getFrom().getFirstName());
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
