package com.cabit.Cab_It.controller.employee;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "employee-profile", value = "/profile/employee")
public class EmployeeProfileServlet extends HttpServlet {
    /*
     * Servlet class to perform profile operations related to a employee
     * */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/Cab_It/employee/profile.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
