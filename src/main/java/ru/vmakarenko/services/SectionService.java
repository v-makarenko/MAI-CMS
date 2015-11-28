package ru.vmakarenko.services;

import ru.vmakarenko.dao.EventsDao;
import ru.vmakarenko.dao.SectionDao;
import ru.vmakarenko.dto.common.SectionDto;
import ru.vmakarenko.entities.events.Section;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.UUID;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class SectionService {
    @Inject
    private SectionDao sectionDao;
    @Inject
    private MapperService mapperService;
    @Inject
    private EventsDao eventDao;


    public void create(SectionDto dto) {
        Section section = mapperService.map(dto, Section.class);
        section.setEvent(eventDao.find(dto.getEventId()));
        sectionDao.insert(section);
    }

    public void update(SectionDto dto) {
        Section section = mapperService.map(dto, Section.class);
        section.setEvent(eventDao.find(dto.getEventId()));
        sectionDao.update(section);
    }

    public Object getAll(UUID eventId) {
        if(eventId == null) {
            return mapperService.map(sectionDao.findAll(), SectionDto.class);
        } else{
            return mapperService.map(sectionDao.findAllFiltered(eventId), SectionDto.class);
        }
    }

    public SectionDto find(UUID id) {
        return mapperService.map(sectionDao.find(id), SectionDto.class);
    }
    public void delete(UUID id) {
        Section section = sectionDao.find(id);
        section.getEvent().getSectionList().removeIf(section1 -> section.getId().equals(id));
        eventDao.update(section.getEvent());
        sectionDao.trueDelete(id);
    }
}
