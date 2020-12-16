package com.epam.controller;

import com.epam.command.ActionCommand;
import com.epam.command.ActionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    private void processRequest(HttpServletRequest req, HttpServletResponse resp){
        SessionRequestContext sessionRequestContext = new SessionRequestContext();
        sessionRequestContext.load(req);

        ActionCommand command = ActionFactory.defineCommand(sessionRequestContext);

    }
}
