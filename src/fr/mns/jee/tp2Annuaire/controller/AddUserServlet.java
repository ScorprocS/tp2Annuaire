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

@WebServlet("/adduser")
public class AddUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final Dao<User> userDao = new UserInMemoryDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("adduser.jsp");
		requestDispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		
		userDao.create(new User(email, username));
		
		response.sendRedirect(request.getContextPath() + "/users");
	}
}