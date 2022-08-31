package com.cabit.Cab_It.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "dashboard", value = "/dashboard")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(session.getAttribute("logged-admin") != null)
        {
            response.sendRedirect("/Cab_It/admin/dashboard");
        }
        else if(session.getAttribute("logged-employee") != null)
        {
            response.sendRedirect("/Cab_It/employee/dashboard");
        }
        else if(session.getAttribute("logged-customer") != null)
        {
            response.sendRedirect("/Cab_It/customer/dashboard");
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
