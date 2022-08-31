package com.cabit.Cab_It.controller.employee;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "employee-panel", value = "/employee/dashboard")
public class EmployeeDashboardServlet extends HttpServlet {
    /*
     * Servlet class to perform dashboard operations related to a employee
     * */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/Cab_It/employee/dashboard.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
