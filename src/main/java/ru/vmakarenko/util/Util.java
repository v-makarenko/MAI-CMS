package ru.vmakarenko.util;

import ru.vmakarenko.dto.users.AccessAuthDto;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by VMakarenko on 7/15/2015.
 */
public class Util {
    public static String getCookieValueFromRequest(String cookieName, HttpServletRequest request){
        String result = "";
        for(Cookie cookie : request.getCookies()){
            if(cookie.getName().equals(AccessAuthDto.PARAM_AUTH_TOKEN)){
                result = cookie.getValue();
            }
        }
        return result;
    }
}
