package ru.vmakarenko.services;

import ru.vmakarenko.common.RestResponse;
import ru.vmakarenko.dao.EventsDao;
import ru.vmakarenko.dao.UserDao;
import ru.vmakarenko.dao.filters.UserFilter;
import ru.vmakarenko.dto.users.AccessAuthDto;
import ru.vmakarenko.dto.users.ChangePasswordDto;
import ru.vmakarenko.dto.users.UserDto;
import ru.vmakarenko.dto.users.UserSignUpDto;
import ru.vmakarenko.entities.events.Event;
import ru.vmakarenko.entities.users.User;
import ru.vmakarenko.enums.EmailCheckResult;
import ru.vmakarenko.exceptions.MaiWebappException;
import ru.vmakarenko.util.Util;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class UserService {
    @Inject
    private UserDao userDao;

    @Inject
    private MapperService mapperService;
    @Inject
    private PasswordService passwordService;
    @Inject
    private EmailService emailService;
    @Inject
    private EventsDao eventsDao;

    public UserDto getByEmailAndPassword(String email, String password) {
        return mapperService.map(userDao.getByEmailAndPassword(email, password), UserDto.class);
    }

    public UserDto getByEmail(String email) {
        return mapperService.map(userDao.getByEmail(email), UserDto.class);
    }

    public void create(UserDto userDto) {
        User user = mapperService.map(userDto, User.class);
        userDao.insert(user);
        generatePassword(user.getEmail());
    }

    public void generatePassword(String email) {
        String password = passwordService.generatePassword();
        User user = userDao.getByEmail(email);
        if(user == null){
            throw new MaiWebappException("Пользователь с email :email не найден!".replace(":email", email));
        }
        user.setPasswordHash(password);
        userDao.update(user);
        emailService.sendPassword(user, password);
    }

    public UserDto getCurrentUser(HttpServletRequest request) {
        return mapperService.map(
                userDao.getByEmail(Util.getCookieValueFromRequest(AccessAuthDto.PARAM_AUTH_EMAIL, request))
                , UserDto.class);
    }

    public void update(UserDto userDto) {
        userDao.update(mapperService.map(userDto, User.class));
    }

    public Object getAll() {
        return mapperService.map(userDao.findAll(), UserDto.class);
    }

    public EmailCheckResult checkEmail(String email) {
        String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if (Pattern.compile(EMAIL_PATTERN).matcher(email).matches()) {
            return EmailCheckResult.INVALID_EMAIL;
        } else if (userDao.getByEmail(email) == null) {
            return EmailCheckResult.DUPLICATE_EMAIL;
        } else {
            return EmailCheckResult.OK;
        }
    }

    public void changePassword(ChangePasswordDto dto) {
        User user = userDao.find(dto.getUserId());
        user.setPasswordHash(dto.getPassword());
        userDao.update(user);
    }

    public UserDto find(UUID id) {
        return mapperService.map(userDao.find(id), UserDto.class);
    }

    public List<UserDto> getAllConfRegistered(UUID eventId) {
        List<User> userList = eventsDao.find(eventId).getUserList();
        userList.size();
        return mapperService.map(userList, UserDto.class);
    }

    public List<UserDto> getFiltered(UserFilter filter) {
        return mapperService.map(userDao.getFiltered(filter), UserDto.class);
    }
}
