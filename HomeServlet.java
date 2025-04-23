package servlet;

import java.io.IOException;
import java.util.List;

import dao.impl.RestaurantDAOImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Restaurant;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Home Servlet called.");

        RestaurantDAOImpl impl = new RestaurantDAOImpl();
        List<Restaurant> allRestaurants = impl.getAllRestaurants();

       

        req.setAttribute("restaurants", allRestaurants);

        RequestDispatcher rd = req.getRequestDispatcher("Home.jsp");
        rd.forward(req, resp);
    }
}
