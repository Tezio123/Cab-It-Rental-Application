package com.cabit.Cab_It.controller.vehicle;

import com.cabit.Cab_It.model.Vehicle;
import com.cabit.Cab_It.service.VehicleService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "fetch-vehicles-of-location", value = "/fetch/location/vehicles")
public class VehiclesAroundLocationFetchServlet extends HttpServlet {
    /*
     * Servlet class to perform fetch operations of vehicles around a location
     * */
    private final VehicleService vehicleService = new VehicleService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String startLocationId = request.getParameter("start-location");
        String endLocationId = request.getParameter("end-location");
        List<Vehicle> vehicles = vehicleService.getVehiclesAroundLocation(startLocationId);

        session.setAttribute("start-location-id", startLocationId);
        session.setAttribute("end-location-id", endLocationId);
        session.setAttribute("location-vehicles", vehicles);

        response.sendRedirect("/Cab_It/order/create-step-2.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
