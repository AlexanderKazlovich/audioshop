package com.epam.controller;

import com.epam.repository.UserDao;
import com.epam.domain.user.User;
import com.epam.pool.ConnectionPool;

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
        userDao = new UserDao(ConnectionPool.INSTANCE.getConnection());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userDao.findUserByUsernameAndPassword(username, password);
        req.setAttribute("user", user);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/main.jsp");
        requestDispatcher.forward(req, resp);

    }


    @Override
    public void destroy() {
        ConnectionPool.INSTANCE.destroyPool();
    }
}
