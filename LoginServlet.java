package servlet;

import java.io.IOException;
import java.util.List;

import dao.impl.RestaurantDAOImpl;
import dao.impl.UserDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Restaurant;
import model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Login Servlet called");

		try {
			UserDAOImpl dao = new UserDAOImpl();
			HttpSession session = req.getSession();

			String email = req.getParameter("email");
			String password = req.getParameter("password");

			System.out.println("Email: " + email + ", Password: " + password);

			User us = dao.loginUser(email, password);

			if (us != null) {
				// System.out.println("Login successful, redirecting to Home.jsp");
				session.setAttribute("users", us);

				 RestaurantDAOImpl restaurantDAO = new RestaurantDAOImpl();
				    List<Restaurant> restaurants = restaurantDAO.getAllRestaurants();
				    session.setAttribute("restaurants", restaurants); // use session here

				    resp.sendRedirect("Home.jsp");
			} else {
				System.out.println("Login failed, redirecting to Login.jsp");
				session.setAttribute("Failed message", "Email & Password Invalid");
				resp.sendRedirect("Login.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}