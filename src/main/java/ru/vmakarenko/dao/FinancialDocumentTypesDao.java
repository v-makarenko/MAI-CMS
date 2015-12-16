package ru.vmakarenko.dao;

import ru.vmakarenko.dao.generic.GenericDao;
import ru.vmakarenko.entities.events.financial.FinancialDocument;
import ru.vmakarenko.entities.events.financial.FinancialDocumentType;

import javax.ejb.Stateless;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class FinancialDocumentTypesDao extends GenericDao<FinancialDocumentType> {
}
