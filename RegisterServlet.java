package servlet;

import java.io.IOException;

import dao.impl.UserDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet("/register")

public class RegisterServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("Register Servlet called");
		
		try {
			String name = req.getParameter("fname");
			String username = req.getParameter("username");
			String email = req.getParameter("email");
			String phone = req.getParameter("phno");
			String password = req.getParameter("password");
			String check = req.getParameter("check");
//			System.out.println(name+" "+email+" "+phno+" "+password+" "+check);
			
			User us = new User();
			us.setName(name);
			us.setUsername(username);
			us.setEmail(email);
			us.setPhone(phone);
			us.setPassword(password);
			
			
			HttpSession session = req.getSession();
			
			
			if(check !=null)
			{
				UserDAOImpl dao = new UserDAOImpl();
				boolean f = dao.addUser(us);			
				if(f)
				{
					//System.out.println("User Register Success");
					session.setAttribute("succMsg", "Registration Succesfully...");
					resp.sendRedirect("Login.jsp");
				}else {
					//System.out.println("Error");
					session.setAttribute("failedMsg", "Something went wrong...");
					resp.sendRedirect("Register.jsp");
				}
			} else {
				session.setAttribute("failedMsg", "Please agree to terms and conditions.");
				resp.sendRedirect("Register.jsp");
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

