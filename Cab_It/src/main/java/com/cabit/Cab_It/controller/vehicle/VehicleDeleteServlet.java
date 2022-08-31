package com.cabit.Cab_It.controller.vehicle;

import com.cabit.Cab_It.model.Vehicle;
import com.cabit.Cab_It.service.VehicleService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "vehicle-delete", value = "/delete/vehicle")
public class VehicleDeleteServlet extends HttpServlet {
    /*
     * Servlet class to perform delete operations related to a vehicle
     * */
    private final VehicleService vehicleService = new VehicleService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String id = request.getParameter("id");

        HttpSession session = request.getSession();

        for(Vehicle vehicle : vehicleService.getVehicles())
        {
            if(vehicle.getId().equals(id))
            {
                session.setAttribute("delete-vehicle", vehicle);
                response.sendRedirect("/Cab_It/vehicle/delete.jsp");

                return;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String confirmation = request.getParameter("delete-confirmation");

        if(confirmation.equals("yes"))
        {
            Vehicle vehicle = (Vehicle) request.getSession().getAttribute("delete-vehicle");

            vehicle = vehicleService.deleteVehicle(vehicle.getId());

            if(vehicle == null)
                session.setAttribute("vehicle-delete-status", "invalid");

            else
                session.setAttribute("vehicle-delete-status", "success");

        }
        response.sendRedirect("/Cab_It/fetch/vehicle");
    }
}
