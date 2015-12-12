package ru.vmakarenko.common;

/**
 * Created by VMakarenko on 7/15/2015.
 */
public enum LogAction {
    EVENT_PARTICIPATE("event.participate",LogAction.userFormat + " принял участие в событии {event.name}({event.id})"),

    EVENT_UNPARTICIPATE("event.unparticipate",LogAction.userFormat + " отказался от участие в событии {event.name}({event.id})"),

    USER_REGISTERED("user.register",LogAction.userFormat + " зарегистрировался в системе"),
    USER_LOGIN("user.login",LogAction.userFormat + " вошел в систему"),
    USER_LOGOUT("user.logout",LogAction.userFormat + " вышел из системы"),

    USER_REFRESHED_INFO("user.save",LogAction.userFormat + " обновил свои личные данные"),

    USER_CHANGED_PASSWORD("user.password.set",LogAction.userFormat + " сменил пароль"),

    USER_RESETTED_PASSWORD("user.password.reset",LogAction.userFormat + " сбросил пароль");

    private static final String userFormat = "{user.surname} {user.name}({user.id})";
    private String type,description;

    LogAction(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }
}
