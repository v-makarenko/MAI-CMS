package ru.vmakarenko.dao;

import ru.vmakarenko.dao.generic.GenericDao;
import ru.vmakarenko.entities.events.thesis.Coauthor;
import ru.vmakarenko.entities.events.thesis.Thesis;

import javax.ejb.Stateless;
import java.util.List;
import java.util.UUID;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class CoauthorDao extends GenericDao<Coauthor> {
}
