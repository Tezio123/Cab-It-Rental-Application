package com.cabit.Cab_It.controller.order;

import com.cabit.Cab_It.model.Admin;
import com.cabit.Cab_It.model.Customer;
import com.cabit.Cab_It.model.Employee;
import com.cabit.Cab_It.model.Order;
import com.cabit.Cab_It.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "fetch-orders", value = "/fetch/order")
public class OrderFetchServlet extends HttpServlet {
    /*
     * Servlet class to perform fetch operations related to a order
     * */
    private final OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(session.getAttribute("logged-customer") != null)
        {
            Customer loggedCustomer =  (Customer) session.getAttribute("logged-customer");

            boolean isDesc = true;

            if(request.getParameter("asc") != null)
                isDesc = false;

            List<Order> orders = orderService.getOrders(loggedCustomer, isDesc);

            session.setAttribute("orders", orders);
            response.sendRedirect("/Cab_It/order/fetch.jsp");
        }
        else if(session.getAttribute("logged-employee") != null)
        {
            Employee loggedEmployee =  (Employee) session.getAttribute("logged-employee");

            boolean isDesc = true;

            if(request.getParameter("asc") != null)
                isDesc = false;

            List<Order> orders = orderService.getOrders(loggedEmployee.getVehicle(), isDesc);

            session.setAttribute("orders", orders);
            response.sendRedirect("/Cab_It/order/react.jsp");
        }
        else if(session.getAttribute("logged-admin") != null)
        {
            Admin loggedAdmin =  (Admin) session.getAttribute("logged-admin");

            boolean isDesc = true;

            if(request.getParameter("asc") != null)
                isDesc = false;

            List<Order> orders = orderService.getOrders(loggedAdmin, isDesc);

            session.setAttribute("orders", orders);
            response.sendRedirect("/Cab_It/order/fetch.jsp");
        }
        else
        {
            response.sendRedirect("/Cab_It/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
