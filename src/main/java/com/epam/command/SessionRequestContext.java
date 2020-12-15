package com.epam.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class SessionRequestContext {
    private HashMap<String, Object> requestAttr;
    private Map<String, String[]> requestPar;
    private HashMap<String, Object> sessionAttr;

    public SessionRequestContext() { }

    public HashMap<String, Object> getRequestAttr() {
        return requestAttr;
    }

    public Map<String, String[]> getRequestPar() {
        return requestPar;
    }

    public HashMap<String, Object> getSessionAttr() {
        return sessionAttr;
    }

    public void load(HttpServletRequest req){
        requestPar = req.getParameterMap();
        requestAttr = new HashMap<>();
        Enumeration<String> attr = req.getAttributeNames();
        while(attr.hasMoreElements()){
            String name = attr.nextElement();
            requestAttr.put(name, req.getAttribute(name));
        }
        sessionAttr = new HashMap<>();
        HttpSession session = req.getSession(true);
        attr = session.getAttributeNames();
        while (attr.hasMoreElements()){
            String name = attr.nextElement();
            sessionAttr.put(name, session.getAttribute(name));
        }

    }
}
