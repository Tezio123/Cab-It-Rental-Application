package com.cabit.Cab_It.controller;

import com.cabit.Cab_It.model.Employee;
import com.cabit.Cab_It.service.EmployeeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "logout", value = "/logout")
public class LogoutServlet extends HttpServlet {

    private final EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String logoutEntity = session.getAttribute("logout-entity").toString();

        session.removeAttribute("login-status");

        if(logoutEntity.equals("admin"))
        {
            session.removeAttribute("logged-admin");
        }
        else if(logoutEntity.equals("employee"))
        {
            Employee loggedEmployee = (Employee) session.getAttribute("logged-employee");

            employeeService.setAsLoggedOutEmployee(loggedEmployee);
            session.removeAttribute("logged-employee");
        }
        else if(logoutEntity.equals("customer"))
        {
            session.removeAttribute("logged-customer");
        }

        response.sendRedirect("/Cab_It/login");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
