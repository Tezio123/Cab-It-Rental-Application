package com.cabit.Cab_It.controller.employee;

import com.cabit.Cab_It.model.Employee;
import com.cabit.Cab_It.service.EmployeeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@WebServlet(name = "fetch-employee", value = "/fetch/employee")
public class EmployeeFetchServlet extends HttpServlet {
    /*
     * Servlet class to perform fetch operations related to a employee
     * */
    private final EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/Cab_It/employee/search.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Employee> employees = employeeService.getEmployees();
        Map<String, Employee> employeeMap = employeeService.getEmployeeMap();

        employees.sort(
                new Comparator<Employee>() {
                    @Override
                    public int compare(Employee o1, Employee o2) {
                        return o2.getRegisteredDateTime().compareTo(o1.getRegisteredDateTime());
                    }
                }
        );
        String employeeId = request.getParameter("employee-id");

        if(employeeId.equals("*"))
            session.setAttribute("employee-fetch-result", employees);

        else if(employeeMap.containsKey(employeeId))
                    session.setAttribute("employee-fetch-result", Arrays.asList(employeeMap.get(employeeId)));

        else
            session.setAttribute("employee-fetch-result", "not-found");

        response.sendRedirect("/Cab_It/fetch/employee");
    }
}
