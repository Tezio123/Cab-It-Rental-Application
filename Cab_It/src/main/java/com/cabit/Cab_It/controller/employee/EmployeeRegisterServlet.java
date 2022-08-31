package com.cabit.Cab_It.controller.employee;

import com.cabit.Cab_It.model.Employee;
import com.cabit.Cab_It.service.EmployeeService;
import com.cabit.Cab_It.service.LocationService;
import com.cabit.Cab_It.service.VehicleService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "employee-register", value = "/register/employee")
public class EmployeeRegisterServlet extends HttpServlet {
    /*
     * Servlet class to perform register operations related to a employee
     * */
    private final EmployeeService employeeService = new EmployeeService();
    private final LocationService locationService = new LocationService();
    private final VehicleService vehicleService = new VehicleService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        if(session.getAttribute("do-post") != null)
        {
            session.removeAttribute("do-post");
            doPost(request, response);
        }
        else {
            session.setAttribute("locations", locationService.getLocationMap());
            session.setAttribute("vehicles", vehicleService.getVehicleMap());
            session.setAttribute("employees", employeeService.getEmployeeMap());

            response.sendRedirect("/Cab_It/employee/create.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String nic = session.getAttribute("nic").toString();
        String username = session.getAttribute("username").toString();
        String password = session.getAttribute("password").toString();
        String firstName = session.getAttribute("first-name").toString();
        String midName = session.getAttribute("mid-name").toString();
        String lastName = session.getAttribute("last-name").toString();
        String addressLine1 = session.getAttribute("address-line-1").toString();
        String addressLine2 = session.getAttribute("address-line-2").toString();
        String addressLine3 = session.getAttribute("address-line-3").toString();
        String city = session.getAttribute("city").toString();
        String phone = session.getAttribute("phone").toString();
        String bloodGroup = session.getAttribute("blood-group").toString();
        String license = session.getAttribute("license").toString();
        String locationId = session.getAttribute("location").toString();
        String vehicleId = session.getAttribute("vehicle").toString();
        String photoPath = session.getAttribute("photo").toString();
        File photo = photoPath.isEmpty() ? null : new File(photoPath);

        Employee employee = employeeService.addEmployee(
                username,
                password,
                nic,
                firstName,
                midName,
                lastName,
                addressLine1,
                addressLine2,
                addressLine3,
                city,
                phone,
                bloodGroup,
                license,
                photo,
                locationId,
                vehicleId
        );

        if(employee == null)
        {
            session.setAttribute("employee-signup-status", "invalid");
            response.sendRedirect("/Cab_It/register/employee");
        }
        else
        {
            session.setAttribute("employee-signup-status", "success");
            response.sendRedirect("/Cab_It/fetch/employee");
        }
    }
}
