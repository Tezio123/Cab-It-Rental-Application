package com.cabit.Cab_It.controller.vehicle;

import com.cabit.Cab_It.model.Vehicle;
import com.cabit.Cab_It.service.VehicleService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "vehicle-fetch", value = "/fetch/vehicle")
public class VehicleFetchServlet extends HttpServlet {
    /*
     * Servlet class to perform fetch operations related to a vehicle
     * */
    private final VehicleService vehicleService = new VehicleService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/Cab_It/vehicle/search.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Vehicle> vehicles = vehicleService.getVehicles();
        Map<String, Vehicle> vehicleMap = vehicleService.getVehicleMap();

        vehicles.sort(
                new Comparator<Vehicle>() {
                    @Override
                    public int compare(Vehicle o1, Vehicle o2) {
                        return o2.getRegisteredDateTime().compareTo(o1.getRegisteredDateTime());
                    }
                }
        );

        String vehicleId = request.getParameter("vehicle-id");

        if(vehicleId.equals("*"))
            session.setAttribute("vehicle-fetch-result", vehicles);

        else if(vehicleMap.containsKey(vehicleId))
                session.setAttribute("vehicle-fetch-result", Arrays.asList(vehicleMap.get(vehicleId)));
        else
            session.setAttribute("vehicle-fetch-result", "not-found");

        response.sendRedirect("/Cab_It/fetch/vehicle");
    }
}
