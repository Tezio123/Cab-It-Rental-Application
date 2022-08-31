package com.cabit.Cab_It.controller.location;

import com.cabit.Cab_It.model.Location;
import com.cabit.Cab_It.service.LocationService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "location-delete", value = "/delete/location")
public class LocationDeleteServlet extends HttpServlet {
    /*
     * Servlet class to perform delete operations related to a location
     * */
    private final LocationService locationService = new LocationService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        HttpSession session = request.getSession();

        for(Location location : locationService.getLocations())
        {
            if(location.getId().equals(id))
            {
                session.setAttribute("delete-location", location);
                response.sendRedirect("/Cab_It/location/delete.jsp");

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
            Location location = (Location) request.getSession().getAttribute("delete-location");

            location = locationService.deleteLocation(location.getId());

            if(location == null)
                session.setAttribute("location-delete-status", "invalid");

            else
                session.setAttribute("location-delete-status", "success");

        }
        response.sendRedirect("/Cab_It/fetch/location");
    }
}
