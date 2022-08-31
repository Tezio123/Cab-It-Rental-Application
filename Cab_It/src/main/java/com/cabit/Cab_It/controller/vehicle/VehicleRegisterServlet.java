package com.cabit.Cab_It.controller.vehicle;

import com.cabit.Cab_It.model.Vehicle;
import com.cabit.Cab_It.service.VehicleService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "vehicle-register", value = "/register/vehicle")
public class VehicleRegisterServlet extends HttpServlet {
    /*
     * Servlet class to perform register operations related to a vehicle
     * */
    private final VehicleService vehicleService = new VehicleService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        if(session.getAttribute("do-post") != null)
        {
            session.removeAttribute("do-post");
            doPost(request, response);
        }
        else
            response.sendRedirect("/Cab_It/vehicle/create.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String model = session.getAttribute("model").toString();
        String brand = session.getAttribute("brand").toString();
        String fuelType = session.getAttribute("fuel-type").toString();
        String engineType = session.getAttribute("engine-type").toString();
        String plateNumber = session.getAttribute("plate-number").toString();
        String topSpeed = session.getAttribute("top-speed").toString();
        String photoPath = session.getAttribute("photo").toString();
        File photo = photoPath.isEmpty() ? null : new File(photoPath);

        Vehicle vehicle = vehicleService.addVehicle(
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
            session.setAttribute("vehicle-register-status", "invalid");
            response.sendRedirect("/Cab_It/register/vehicle");
        }
        else
        {
            session.setAttribute("vehicle-register-status", "success");
            response.sendRedirect("/Cab_It/fetch/vehicle");
        }
    }
}
