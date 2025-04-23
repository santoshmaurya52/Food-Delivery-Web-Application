package servlet;

import java.io.IOException;
import java.util.List;

import dao.impl.MenuDAOImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Menu;



@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("Menu Servlet Called");
		
		req.getParameter("restaurantId");
		int rid = Integer.parseInt(req.getParameter("restaurantId"));
		
		

		MenuDAOImpl daoImpl = new MenuDAOImpl();
		
		 List<Menu> menuList = daoImpl.getAllMenusByRestaurant(rid);
		 
		 req.setAttribute("menus", menuList);
		 
		 
		 RequestDispatcher rd = req.getRequestDispatcher("Menu.jsp");
		 
		 rd.forward(req, resp);
		
		
		
		
		
	}

}
