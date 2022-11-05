package com.wodongso.wodongso.security;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {

    private static final String SESSION_NAME = "sid";
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    //    세션 생성
    public void createSession(Object value, HttpServletResponse res) {
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);

        Cookie cookie = new Cookie(SESSION_NAME, sessionId);
        res.addCookie(cookie);
    }

    //    세션 전체 조회
    public Object getAllSession(HttpServletRequest req) {
        Cookie cookie = getCookie(req, SESSION_NAME);
        if (cookie == null) {
            return null;
        }
        return sessionStore.get(cookie.getValue());
    }

    // 세션 만료
    public void expire(HttpServletRequest req) {
        Cookie cookie = getCookie(req, SESSION_NAME);
        if (cookie != null) {
            sessionStore.remove(cookie.getValue());
        }

    }


    // 특정 쿠키 조회
    private Cookie getCookie(HttpServletRequest req, String cookieName) {
        return Arrays.stream(req.getCookies())
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }
}
