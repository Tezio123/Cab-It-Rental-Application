package com.cabit.Cab_It.controller.customer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "customer-profile", value = "/profile/customer")
public class CustomerProfileServlet extends HttpServlet {
    /*
     * Servlet class to perform profile operations related to a customer
     * */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/Cab_It/customer/profile.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
