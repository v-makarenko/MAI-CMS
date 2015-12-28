package ru.vmakarenko.services;

import ru.vmakarenko.dao.EventsDao;
import ru.vmakarenko.dao.FinancialDocumentsDao;
import ru.vmakarenko.dao.UserDao;
import ru.vmakarenko.dto.events.financial.FinancialDocumentDto;
import ru.vmakarenko.dto.findocs.CreateForUserDto;
import ru.vmakarenko.entities.FakeDeleteDomainEntity;
import ru.vmakarenko.entities.events.financial.FinancialDocument;
import ru.vmakarenko.entities.events.financial.FinancialDocumentStatus;
import ru.vmakarenko.entities.events.financial.FinancialDocumentType;
import ru.vmakarenko.entities.users.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class FinancialDocumentService {
    @Inject
    private FinancialDocumentsDao dao;
    @Inject
    private MapperService mapperService;
    @Inject
    private UserDao userDao;
    @Inject
    private EventsDao eventsDao;
    @Inject
    private FinancialDocumentsDao finDocDao;

    @Inject
    private CurrentService currentService;

    public List<FinancialDocumentDto> getAll(){
        return mapperService.map(dao.findAll(), FinancialDocumentDto.class);
    }

    public void insert(FinancialDocumentDto dto){
        dao.insert(mapperService.map(dto, FinancialDocument.class));
    }

    public void update(FinancialDocumentDto dto){
        dao.update(mapperService.map(dto, FinancialDocument.class));
    }

    public void delete(UUID typeId){
        dao.delete(typeId);

    }

    public List<FinancialDocumentDto> getForEvent(UUID id) {
        List<FinancialDocumentDto> dtoList = mapperService.map(dao.getForEvent(id, userDao.getByEmail(currentService.getEmail()).getId()), FinancialDocumentDto.class);
        return dtoList;
    }

    public void createDocsForUser(CreateForUserDto dto) {
        List<FinancialDocumentType> userAvailableDocTypes = finDocDao.getForEvent(dto.getEventId(), dto.getUserId())
                .stream().map(FinancialDocument::getType).collect(Collectors.toList());
        User user = userDao.find(dto.getUserId());

        eventsDao.find(dto.getEventId()).getFinancialDocumentTypeList().stream()
                .filter(financialDocumentType -> !userAvailableDocTypes.contains(financialDocumentType))
                .filter(FakeDeleteDomainEntity::isActive)
                .forEach(finDocType -> {
                    FinancialDocument finDoc = new FinancialDocument();
                    finDoc.setUser(user);
                    finDoc.setStatus(FinancialDocumentStatus.REQUESTED);
                    finDoc.setType(finDocType);
                    finDocDao.insert(finDoc);
                });
                
    }

    public List<FinancialDocumentDto> getForUser(UUID eventId, UUID userId) {
        return mapperService.map(finDocDao.getForEvent(eventId, userId), FinancialDocumentDto.class);
    }

    public FinancialDocumentDto find(UUID id) {
        return mapperService.map(finDocDao.find(id), FinancialDocumentDto.class);
    }
}
