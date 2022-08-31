package com.cabit.Cab_It.controller.location;

import com.cabit.Cab_It.model.Location;
import com.cabit.Cab_It.service.LocationService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "update-location", value = "/update/location")
public class LocationUpdateServlet extends HttpServlet {
    /*
     * Servlet class to perform update operations related to a location
     * */
    private final LocationService locationService = new LocationService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        HttpSession session = request.getSession();

        if(id != null) {

            for (Location location : locationService.getLocations()) {
                if (location.getId().equals(id)) {
                    session.setAttribute("update-location", location);
                    break;
                }
            }
            response.sendRedirect("/Cab_It/location/update.jsp");
        }
        else if(session.getAttribute("do-post") != null)
        {
            request.removeAttribute("do-post");
            doPost(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String id = session.getAttribute("location-id").toString();
        String district = session.getAttribute("district").toString();
        String zone = session.getAttribute("zone").toString();

        Location location = locationService.setLocation(
                id,
                district,
                zone
        );

        if(location == null)
        {
            session.setAttribute("location-update-status", "invalid");
            response.sendRedirect("/Cab_It/update/location");
        }
        else
        {
            session.setAttribute("location-update-status", "success");
            response.sendRedirect("/Cab_It/fetch/location");
        }
    }
}
