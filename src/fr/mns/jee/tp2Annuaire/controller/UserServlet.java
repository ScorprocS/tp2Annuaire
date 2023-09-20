package fr.mns.jee.tp2Annuaire.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.mns.jee.tp2Annuaire.dao.Dao;
import fr.mns.jee.tp2Annuaire.dao.UserInMemoryDao;
import fr.mns.jee.tp2Annuaire.model.User;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final Dao<User> userDao = new UserInMemoryDao();

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("users", userDao.findAll());

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("users.jsp");
        requestDispatcher.forward(request, response);
    }
}