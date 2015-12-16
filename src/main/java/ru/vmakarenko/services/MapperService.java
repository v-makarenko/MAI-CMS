package ru.vmakarenko.services;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ru.vmakarenko.common.UserType;
import ru.vmakarenko.dao.EventsDao;
import ru.vmakarenko.dao.FileEntryDao;
import ru.vmakarenko.dao.UserDao;
import ru.vmakarenko.dto.common.*;
import ru.vmakarenko.dto.events.CoauthorDto;
import ru.vmakarenko.dto.events.EventDto;
import ru.vmakarenko.dto.events.LogEntryDto;
import ru.vmakarenko.dto.events.ThesisDto;
import ru.vmakarenko.dto.events.financial.FinancialDocumentTypeDto;
import ru.vmakarenko.dto.users.UserDto;
import ru.vmakarenko.entities.FakeDeleteDomainEntity;
import ru.vmakarenko.entities.events.Event;
import ru.vmakarenko.entities.events.Section;
import ru.vmakarenko.entities.events.financial.FinancialDocumentType;
import ru.vmakarenko.entities.events.thesis.*;
import ru.vmakarenko.entities.messages.EmailMessage;
import ru.vmakarenko.entities.messages.EmailTemplate;
import ru.vmakarenko.entities.messages.InnerMessage;
import ru.vmakarenko.entities.users.LogEntry;
import ru.vmakarenko.entities.users.User;
import ru.vmakarenko.entities.users.WorkingPlace;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
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

    @Inject
    private EventsDao eventDao;
    @Inject
    private FileEntryDao fileEntryDao;

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
                .fieldAToB("workingPlace.id", "wpId")
                .fieldAToB("workingPlace.shortName", "wpShortName")
                .fieldAToB("workingPlace.name", "wpLongName")
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
                .fieldAToB("from.id", "fromId").fieldAToB("to.id", "toId")
                .fieldAToB("file.id", "fileId")
                .customize(
                        new CustomMapper<InnerMessage, MessageDto>() {
                            public void mapAtoB(InnerMessage a, MessageDto b, MappingContext context) {
                                // add your custom mapping code here
                                if (a.getFrom() != null) {
                                    b.setFromName(a.getFrom().getSurname() + " " + a.getFrom().getName());
                                }
                                if (a.getFile() != null) {
                                    b.setFileId(a.getFile().getId());
                                    b.setFileName(a.getFile().getFilename()
                                            + "."
                                            + a.getFile().getExtension());
                                    if (a.getFile().getContentType() != null) {
                                        b.setFileIsImage(a.getFile().getContentType().equals("image/jpeg"));
                                    } else{
                                        b.setFileIsImage(false);
                                    }
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
                                    CoauthorToBeRegistered ctbr = (CoauthorToBeRegistered) coauthor;
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

        mapperFactory.classMap(WorkingPlace.class, WorkingPlaceDto.class)
                .customize(new CustomMapper<WorkingPlace, WorkingPlaceDto>() {
                    @Override
                    public void mapAtoB(WorkingPlace workingPlace, WorkingPlaceDto workingPlaceDto, MappingContext context) {
                        workingPlaceDto.setEmployeeCount(workingPlace.getEmployeeList() == null ? 0 : workingPlace.getEmployeeList().size());
                    }
                })
                .byDefault()
                .register();


        mapperFactory.classMap(LogEntry.class, LogEntryDto.class)
                .customize(new CustomMapper<LogEntry, LogEntryDto>() {
                               @Override
                               public void mapAtoB(LogEntry logEntry, LogEntryDto logEntryDto, MappingContext context) {
                                   logEntryDto.setUserLongName(logEntry.getUser().getLongSNP());
                                   logEntryDto.setUserShortName(logEntry.getUser().getShortSNP());
                               }
                           }

                )
                .fieldAToB("user.id", "userId")
                .byDefault()
                .register();

        mapperFactory.classMap(FinancialDocumentType.class, FinancialDocumentTypeDto.class)
                .customize(
                        new CustomMapper<FinancialDocumentType, FinancialDocumentTypeDto>() {
                            @Override
                            public void mapBtoA(FinancialDocumentTypeDto financialDocumentTypeDto, FinancialDocumentType financialDocumentType, MappingContext context) {
                                if (financialDocumentTypeDto.getEventId() != null) {
                                    financialDocumentType.setEvent(eventDao.find(financialDocumentTypeDto.getEventId()));
                                }
                                if (financialDocumentTypeDto.getExampleFileId() != null) {
                                    financialDocumentType.setExample(fileEntryDao.find(financialDocumentTypeDto.getExampleFileId()));
                                }
                            }

                            @Override
                            public void mapAtoB(FinancialDocumentType financialDocumentType, FinancialDocumentTypeDto financialDocumentTypeDto, MappingContext context) {
                                if (financialDocumentType.getExample() != null) {
                                    financialDocumentTypeDto.setExampleFileId(financialDocumentType.getExample().getId());
                                    financialDocumentTypeDto.setExampleFileName(financialDocumentType.getExample().getFilename()
                                            + "." + financialDocumentType.getExample().getExtension());
                                }
                            }
                        }
                )
                .fieldAToB("event.id", "eventId")
                .fieldAToB("example.filename", "exampleFileName")
                .fieldAToB("example.id", "exampleFileId")
                .byDefault()
                .register();


        mapperFactory.classMap(Event.class, EventDto.class)
                .customize(new CustomMapper<Event, EventDto>() {
                    @Override
                    public void mapBtoA(EventDto eventDto, Event event, MappingContext context) {
                        super.mapBtoA(eventDto, event, context);
                    }

                    @Override
                    public void mapAtoB(Event event, EventDto eventDto, MappingContext context) {
                        super.mapAtoB(event, eventDto, context);
                        eventDto.setFinancialDocumentTypeList(eventDto.getFinancialDocumentTypeList()
                                .stream()
                                .filter(FakeDeleteDomainEntity::isActive)
                                .collect(Collectors.toList()));
                    }
                })
                .byDefault().register();

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
