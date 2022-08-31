package com.cabit.Cab_It.controller.vehicle;

import com.cabit.Cab_It.model.Employee;
import com.cabit.Cab_It.model.Vehicle;
import com.cabit.Cab_It.service.VehicleService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "vehicle-optional-update", value = "/optional-update/vehicle")
public class VehicleOptionalUpdateServlet extends HttpServlet {
    /*
     * Servlet class to perform fetch operations related to a vehicle
     * */
    private final VehicleService vehicleService = new VehicleService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(session.getAttribute("logged-employee") != null)
        {
            response.sendRedirect("/Cab_It/vehicle/optional-update.jsp");
        }
        else
        {
            response.sendRedirect("/Cab_It/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String id = session.getAttribute("vehicle-id").toString();
        String engineType = request.getParameter("engine-type");
        String photo = request.getParameter("photo");
        File photoFile = photo.isEmpty() ? null : new File(photo);

        Vehicle vehicle = vehicleService.setVehicleOptional(
                id,
                engineType,
                photoFile
        );

        if(vehicle == null)
        {
            session.setAttribute("vehicle-optional-update-status", "invalid");
            response.sendRedirect("/Cab_It/update-optional/vehicle");
        }
        else
        {
            Employee loggedEmployee = (Employee) session.getAttribute("logged-employee");
            loggedEmployee.setVehicle(vehicle);

            session.setAttribute("logged-employee", loggedEmployee);
            session.setAttribute("vehicle-optional-update-status", "success");

            response.sendRedirect("/Cab_It/profile/vehicle");
        }
    }
}
