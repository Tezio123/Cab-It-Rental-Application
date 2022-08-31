package com.cabit.Cab_It.controller.location;

import com.cabit.Cab_It.model.Location;
import com.cabit.Cab_It.service.LocationService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "location-register", value = "/register/location")
public class LocationRegisterServlet extends HttpServlet {
    /*
     * Servlet class to perform register operations related to a location
     * */
    private final LocationService locationService = new LocationService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(session.getAttribute("do-post") != null)
        {
            session.removeAttribute("do-post");
            doPost(request, response);
        }
        else
            response.sendRedirect("/Cab_It/location/create.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String district = session.getAttribute("district").toString();
        String zone = session.getAttribute("zone").toString();

        Location location = locationService.addLocation(
                district,
                zone
        );

        if(location == null)
        {
            session.setAttribute("location-register-status", "invalid");
            response.sendRedirect("/Cab_It/register/location");
        }
        else
        {
            session.setAttribute("location-register-status", "success");
            response.sendRedirect("/Cab_It/fetch/location");
        }
    }
}
