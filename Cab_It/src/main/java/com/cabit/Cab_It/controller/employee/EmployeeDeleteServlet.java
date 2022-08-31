package com.cabit.Cab_It.controller.employee;

import com.cabit.Cab_It.model.Employee;
import com.cabit.Cab_It.service.EmployeeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "delete-employee", value = "/delete/employee")
public class EmployeeDeleteServlet extends HttpServlet {
    /*
     * Servlet class to perform delete operations related to a employee
     * */
    private final EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        HttpSession session = request.getSession();

        for(Employee employee : employeeService.getEmployees())
        {
            if(employee.getId().equals(id))
            {
                session.setAttribute("delete-employee", employee);
                response.sendRedirect("/Cab_It/employee/delete.jsp");

                return;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String confirmation = request.getParameter("delete-confirmation");

        if(confirmation.equals("yes")) {
            Employee employee = (Employee) request.getSession().getAttribute("delete-employee");

            employee = employeeService.deleteEmployee(employee.getId());

            if (employee == null)
                session.setAttribute("employee-delete-status", "invalid");

            else
                session.setAttribute("employee-delete-status", "success");
        }
        response.sendRedirect("/Cab_It/fetch/employee");
    }
}
