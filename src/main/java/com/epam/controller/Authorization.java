package com.epam.controller;

import com.epam.dao.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class Authorization extends HttpServlet {
    private UserDao userDao;
    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        userDao.getUserByUsernamePassword(username, password);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/main.jsp");
        requestDispatcher.forward(req, resp);

    }
}
