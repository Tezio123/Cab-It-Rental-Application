package com.cabit.Cab_It.controller;

import com.cabit.Cab_It.model.Admin;
import com.cabit.Cab_It.model.Customer;
import com.cabit.Cab_It.model.Employee;
import com.cabit.Cab_It.service.AdminService;
import com.cabit.Cab_It.service.CustomerService;
import com.cabit.Cab_It.service.EmployeeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {

    private final AdminService adminService = new AdminService();
    private final EmployeeService employeeService = new EmployeeService();
    private final CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Admin admin = adminService.getAdminByUsernameAndPassword(
                username,
                password
        );

        Employee employee = employeeService.getEmployeeByUsernameAndPassword(
                username,
                password
        );

        Customer customer = customerService.getCustomerByUsernameAndPassword(
                username,
                password
        );

        HttpSession session = request.getSession();

        if(admin != null)
        {
            response.sendRedirect("admin/dashboard");
            session.setAttribute("logged-admin", admin);
        }
        else if(employee != null)
        {
            employeeService.setAsLoggedInEmployee(employee);

            response.sendRedirect("employee/dashboard");
            session.setAttribute("logged-employee", employee);
        }
        else if(customer != null)
        {
            response.sendRedirect("customer/dashboard");
            session.setAttribute("logged-customer", customer);
        }
        else
        {
            session.setAttribute("login-status", "invalid");
            response.sendRedirect("login");
            return;
        }
        session.setAttribute("login-status", "success");
    }
}
