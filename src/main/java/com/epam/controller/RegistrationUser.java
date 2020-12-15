package com.epam.controller;

import com.epam.repository.UserDao;
import com.epam.domain.user.Role;
import com.epam.pool.ConnectionPool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationUser extends HttpServlet {
    private UserDao userDao;
    @Override
    public void init() throws ServletException {
        userDao = new UserDao(ConnectionPool.INSTANCE.getConnection());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String firstName = req.getParameter("firstname");

        userDao.save(username, password, email, firstName, Role.CLIENT, 100);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(req, resp);

    }

    @Override
    public void destroy() {
        ConnectionPool.INSTANCE.destroyPool();
    }
}
