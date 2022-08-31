package com.cabit.Cab_It.controller.vehicle;

import com.cabit.Cab_It.model.Vehicle;
import com.cabit.Cab_It.service.VehicleService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "vehicle-update", value = "/update/vehicle")
public class VehicleUpdateServlet extends HttpServlet {
    /*
     * Servlet class to perform update operations related to a vehicle
     * */
    private final VehicleService vehicleService = new VehicleService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        HttpSession session = request.getSession();

        if(id != null)
        {
            List<Vehicle> vehicles = vehicleService.getVehicles();

            for(Vehicle vehicle : vehicles)
            {
                if(vehicle.getId().equals(id))
                {
                    session.setAttribute("update-vehicle", vehicle);
                    break;
                }
            }
            response.sendRedirect("/Cab_It/vehicle/update.jsp");
        }
        else if(session.getAttribute("do-post") != null)
        {
            session.removeAttribute("do-post");
            doPost(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        String id = session.getAttribute("vehicle-id").toString();
        String model = session.getAttribute("model").toString();
        String brand = session.getAttribute("brand").toString();
        String fuelType = session.getAttribute("fuel-type").toString();
        String engineType = session.getAttribute("engine-type").toString();
        String plateNumber = session.getAttribute("plate-number").toString();
        String topSpeed = session.getAttribute("top-speed").toString();
        String photoPath = session.getAttribute("photo").toString();
        File photo = photoPath.isEmpty() ? null : new File(photoPath);

        Vehicle vehicle = vehicleService.setVehicle(
                id,
                model,
                brand,
                fuelType,
                engineType,
                plateNumber,
                Integer.parseInt(topSpeed),
                photo
        );

        if(vehicle == null)
        {
            session.setAttribute("vehicle-update-status", "invalid");
            response.sendRedirect("/Cab_It/update/vehicle");
        }
        else
        {
            session.setAttribute("vehicle-update-status", "success");
            response.sendRedirect("/Cab_It/fetch/vehicle");
        }
    }
}
