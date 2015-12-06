package ru.vmakarenko.services;

import org.apache.commons.lang.StringUtils;
import ru.vmakarenko.common.LogAction;
import ru.vmakarenko.common.TokenService;
import ru.vmakarenko.dao.LogEntryDao;
import ru.vmakarenko.dao.UserDao;
import ru.vmakarenko.dto.events.LogEntryDto;
import ru.vmakarenko.dto.users.AccessAuthDto;
import ru.vmakarenko.dto.users.UserAuthDto;
import ru.vmakarenko.dto.users.UserDto;
import ru.vmakarenko.entities.users.LogEntry;
import ru.vmakarenko.entities.users.User;
import ru.vmakarenko.util.Util;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by VMakarenko on 4/26/2015.
 */
@Stateless
@Transactional(Transactional.TxType.SUPPORTS)
public class LogService {
    @Inject
    private LogEntryDao dao;
    @Inject
    private CurrentService currentService;
    @Inject
    private UserDao userDao;
    @Inject
    private MapperService mapperService;

    public void log(LogAction logAction, Map<String, Object> data, boolean success) {
        User user = userDao.getByEmail(currentService.getEmail());
        log(logAction, user.getId(), data, success);
    }

    public void log(LogAction logAction, UUID userId, Map<String, Object> data, boolean success) {
        User user = userDao.find(userId);
        if (!data.containsKey("user")) {
            data.put("user", user);
        }
        LogEntry logEntry = new LogEntry();
        logEntry.setUser(user);
        logEntry.setType(logAction.getType());
        logEntry.setDescription(fillDescription(logAction.getDescription(), data));
        logEntry.setSuccess(success);
        logEntry.setTime(Calendar.getInstance().getTime());
        dao.insert(logEntry);
    }

    public List<LogEntryDto> getLog(UUID userId, Date dateFrom, Date dateTo) {
        return mapperService.map(dao.getAll(userId, dateFrom, dateTo), LogEntryDto.class);
    }

    private String fillDescription(String template, Map<String, Object> data) {
        Pattern pattern = Pattern.compile("\\{([\\w.]+)\\}");
        while (pattern.matcher(template).find()) {
            Matcher matcher = pattern.matcher(template);
            matcher.find();
            String[] key = matcher.group(1).split("\\.");
            Object obj = data.get(key[0]);
            for (int i = 1; i < key.length; i++) {
                Method m = null;
                try {
                    m = obj.getClass().getMethod("get" + StringUtils.capitalize(key[i]));
                    obj = m.invoke(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return "";
                } catch (NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                    return "";
                }

            }
            template = pattern.matcher(template).replaceFirst(obj.toString());
        }
        return template;
    }

}
