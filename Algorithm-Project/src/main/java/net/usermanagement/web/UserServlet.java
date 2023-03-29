package net.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import net.usermanagement.dao.UserDAO;
import net.usermanagement.model.User;

@WebServlet({"/UserServlet"})
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public UserServlet() {
    }

    public void init() {
        this.userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	String action = request.getServletPath();
            switch (action) {
                case "/update":
                    this.updateUser(request, response);
                    return;
                case "/new":
                    this.showNewForm(request, response);
                    return;
                case "/edit":
                    this.showEditForm(request, response);
                    return;
                case "/delete":
                    this.deleteUser(request, response);
                    return;
                case "/insert":
                    this.insertUser(request, response);
                    return;
            }

            this.listUser(request, response);
        } catch (SQLException var5) {
            throw new ServletException(var5);
        } catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        List<User> listUser = this.userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = this.userDAO.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User newUser = new User(name, email, country);
        this.userDAO.insertUser(newUser);
        response.sendRedirect("list");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User book = new User(id, name, email, country);
        this.userDAO.updateUser(book);
        response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        int id = Integer.parseInt(request.getParameter("id"));
        this.userDAO.deleteUser(id);
        response.sendRedirect("list");
    }
}