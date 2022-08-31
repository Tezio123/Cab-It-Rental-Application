package com.cabit.Cab_It.controller.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "admin-dashboard", value = "/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {
    /*
     * Servlet class to perform dashboard operations related to a admin
     * */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/Cab_It/admin/dashboard.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
