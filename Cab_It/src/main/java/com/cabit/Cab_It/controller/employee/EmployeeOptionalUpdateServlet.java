package com.cabit.Cab_It.controller.employee;

import com.cabit.Cab_It.model.Employee;
import com.cabit.Cab_It.service.EmployeeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "employee-optional-update", value = "/update-optional/employee")
public class EmployeeOptionalUpdateServlet extends HttpServlet {
    /*
     * Servlet class to perform optional update operations related to a employee
     * */
    private final EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(session.getAttribute("do-post") != null)
        {
            doPost(request, response);
            session.removeAttribute("do-post");
        }
        else if(session.getAttribute("logged-employee") != null)
        {
            response.sendRedirect("/Cab_It/employee/optional-update.jsp");
        }
        else
        {
            response.sendRedirect("/Cab_It/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String id = session.getAttribute("employee-id").toString();
        String username = session.getAttribute("username").toString();
        String password = session.getAttribute("password").toString();
        String addressLine1 = session.getAttribute("address-line-1").toString();
        String addressLine2 = session.getAttribute("address-line-2").toString();
        String addressLine3 = session.getAttribute("address-line-3").toString();
        String city = session.getAttribute("city").toString();
        String phone = session.getAttribute("phone").toString();

        Employee employee = employeeService.setEmployeeOptional(
                id,
                username,
                password,
                addressLine1,
                addressLine2,
                addressLine3,
                city,
                phone
        );

        if(employee == null)
        {
            session.setAttribute("employee-optional-update-status", "invalid");
            response.sendRedirect("/Cab_It/update-optional/employee");
        }
        else
        {
            session.setAttribute("employee-optional-update-status", "success");
            session.setAttribute("logged-employee", employee);

            response.sendRedirect("/Cab_It/profile/employee");
        }
    }
}
