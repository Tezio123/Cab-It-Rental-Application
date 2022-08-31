package com.cabit.Cab_It.controller.customer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "customer-panel", value = "/customer/dashboard")
public class CustomerDashboardServlet extends HttpServlet {
    /*
     * Servlet class to perform dashboard operations related to a customer
     * */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/Cab_It/customer/dashboard.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
