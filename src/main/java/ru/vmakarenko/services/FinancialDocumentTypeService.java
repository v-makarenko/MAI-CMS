package ru.vmakarenko.services;

import ru.vmakarenko.dao.EventsDao;
import ru.vmakarenko.dao.FinancialDocumentTypesDao;
import ru.vmakarenko.dao.UserDao;
import ru.vmakarenko.dto.events.EventDto;
import ru.vmakarenko.dto.events.PresenceDto;
import ru.vmakarenko.dto.events.financial.FinancialDocumentTypeDto;
import ru.vmakarenko.entities.events.Event;
import ru.vmakarenko.entities.events.financial.FinancialDocument;
import ru.vmakarenko.entities.events.financial.FinancialDocumentType;
import ru.vmakarenko.entities.users.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class FinancialDocumentTypeService {
    @Inject
    private FinancialDocumentTypesDao dao;
    @Inject
    private MapperService mapperService;

    public List<FinancialDocumentTypeDto> getAll(){
        return mapperService.map(dao.findAll(), FinancialDocumentTypeDto.class);
    }

    public FinancialDocumentTypeDto find(UUID id){
        return mapperService.map(dao.find(id), FinancialDocumentTypeDto.class);
    }

    public void insert(FinancialDocumentTypeDto dto){
        dao.insert(mapperService.map(dto, FinancialDocumentType.class));
    }

    public void update(FinancialDocumentTypeDto dto){
        dao.update(mapperService.map(dto, FinancialDocumentType.class));
    }

    public void delete(UUID typeId){
        dao.delete(typeId);
    }
}
