package ru.vmakarenko.services;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import ru.vmakarenko.common.UserType;
import ru.vmakarenko.dao.UserDao;
import ru.vmakarenko.dto.common.EmailMessageDto;
import ru.vmakarenko.dto.common.EmailTemplateDto;
import ru.vmakarenko.dto.common.MessageDto;
import ru.vmakarenko.dto.common.SectionDto;
import ru.vmakarenko.dto.events.CoauthorDto;
import ru.vmakarenko.dto.events.EventDto;
import ru.vmakarenko.dto.events.ThesisDto;
import ru.vmakarenko.dto.users.UserDto;
import ru.vmakarenko.entities.events.Event;
import ru.vmakarenko.entities.events.Section;
import ru.vmakarenko.entities.events.thesis.*;
import ru.vmakarenko.entities.messages.EmailMessage;
import ru.vmakarenko.entities.messages.EmailTemplate;
import ru.vmakarenko.entities.messages.InnerMessage;
import ru.vmakarenko.entities.users.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@ApplicationScoped
@Transactional(Transactional.TxType.SUPPORTS)
public class MapperService {

    private MapperFactory mapperFactory;

    @Inject
    private UserDao userDao;

    @PostConstruct
    public void init() {
        mapperFactory = new DefaultMapperFactory.Builder().build();


        // users stuff
        mapperFactory.classMap(User.class, UserDto.class)
                .customize(new CustomMapper<User, UserDto>() {
                    @Override
                    public void mapAtoB(User user, UserDto userDto, MappingContext context) {
                        if (user.getUserType() == null) {
                            user.setUserType(UserType.MEMBER);
                        }

                        userDto.setUserType(user.getUserType().name());
                        userDto.setSnpLong(user.getSurname() + " " + (user.getName() != null ? user.getName() : "") + " " + (user.getPatronymic() != null ? user.getPatronymic() : ""));
                        userDto.setSnpShort(user.getSurname() + " " + (user.getName() != null ? (user.getName().substring(0, 1)
                                + ".") : "") + (user.getPatronymic() != null ? (user.getPatronymic().substring(0, 1) + ".") : ""));
                    }

                    @Override
                    public void mapBtoA(UserDto userDto, User user, MappingContext context) {
                        if (userDto.getUserType() == null) {
                            user.setUserType(UserType.MEMBER);
                        } else {
                            user.setUserType(UserType.valueOf(userDto.getUserType().toUpperCase()));
                        }

                        //TODO
                        user.setPasswordHash(userDto.getPassword());
                    }
                })
                .byDefault().register();


        mapperFactory.classMap(EmailMessage.class, EmailMessageDto.class)
                .customize(new CustomMapper<EmailMessage, EmailMessageDto>() {
                               @Override
                               public void mapBtoA(EmailMessageDto emailMessageDto, EmailMessage emailMessage, MappingContext context) {
                                   super.mapBtoA(emailMessageDto, emailMessage, context);
                                   emailMessage.setToList(emailMessageDto
                                           .getToList()
                                           .stream().map(userDao::getByEmail)
                                           .collect(Collectors.toList()));
                               }

                               @Override
                               public void mapAtoB(EmailMessage emailMessage, EmailMessageDto emailMessageDto, MappingContext context) {
                                   super.mapAtoB(emailMessage, emailMessageDto, context);
                                   emailMessageDto.setToList(emailMessage
                                           .getToList().stream()
                                           .map(User::getEmail)
                                           .collect(Collectors.toList()));
                               }
                           }
                )
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

        mapperFactory.classMap(Section.class, SectionDto.class)
                .fieldAToB("event.id", "eventId")
                .byDefault().register();

        mapperFactory.classMap(Coauthor.class, CoauthorDto.class)
                .customize(
                        new CustomMapper<Coauthor, CoauthorDto>() {
                            @Override
                            public void mapAtoB(Coauthor coauthor, CoauthorDto coauthorDto, MappingContext context) {
                                if (coauthor instanceof CoauthorUser) {
                                    CoauthorUser caUser = (CoauthorUser) coauthor;
                                    coauthorDto.setUserId(caUser.getUser().getId());
                                    coauthorDto.setSurname(caUser.getUser().getSurname());
                                    coauthorDto.setName(caUser.getUser().getName());
                                    coauthorDto.setSnpLong(caUser.getUser().getSurname() + " " + (caUser.getUser().getName() != null ?
                                            caUser.getUser().getName() : "") + " " + (caUser.getUser().getPatronymic() != null ?
                                            caUser.getUser().getPatronymic() : ""));
                                    coauthorDto.setSnpShort(caUser.getUser().getSurname() + " " + (caUser.getUser().getName() != null
                                            ? (caUser.getUser().getName().substring(0, 1)
                                            + ".") : "") + (caUser.getUser().getPatronymic() != null ?
                                            ((caUser).getUser().getPatronymic().substring(0, 1) + ".") : ""));
                                }
                                if (coauthor instanceof CoauthorSNP) {
                                    CoauthorSNP caSnp = (CoauthorSNP) coauthor;
                                    coauthorDto.setSurname(caSnp.getSurname());
                                    coauthorDto.setName(caSnp.getName());
                                    coauthorDto.setPatronymic(caSnp.getPatronymic());
                                    coauthorDto.setSnpLong(caSnp.getSurname() + " " + (caSnp.getName() != null ?
                                            caSnp.getName() : "") + " " + (caSnp.getPatronymic() != null ?
                                            caSnp.getPatronymic() : ""));
                                    coauthorDto.setSnpShort(caSnp.getSurname() + " " + (caSnp.getName() != null
                                            ? (caSnp.getName().substring(0, 1)
                                            + ".") : "") + (caSnp.getPatronymic() != null ? (caSnp.getPatronymic().substring(0, 1) + ".") : ""));

                                }
                                if (coauthor instanceof CoauthorToBeRegistered) {
                                    CoauthorToBeRegistered ctbr = (CoauthorToBeRegistered)coauthor;
                                    coauthorDto.setEmail(ctbr.getEmail());
                                }
                            }
                        }

                )
                .byDefault()
                .register();

        mapperFactory.classMap(Thesis.class, ThesisDto.class)
                .fieldAToB("section.id", "sectionId")
                .fieldAToB("section.name", "sectionName")
                .byDefault()
                .register();


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
