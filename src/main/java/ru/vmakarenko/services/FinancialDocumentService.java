package ru.vmakarenko.services;

import ru.vmakarenko.dao.FinancialDocumentTypesDao;
import ru.vmakarenko.dao.FinancialDocumentsDao;
import ru.vmakarenko.dto.events.financial.FinancialDocumentDto;
import ru.vmakarenko.dto.events.financial.FinancialDocumentTypeDto;
import ru.vmakarenko.entities.events.financial.FinancialDocument;
import ru.vmakarenko.entities.events.financial.FinancialDocumentType;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class FinancialDocumentService {
    @Inject
    private FinancialDocumentsDao dao;
    @Inject
    private MapperService mapperService;

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
}
