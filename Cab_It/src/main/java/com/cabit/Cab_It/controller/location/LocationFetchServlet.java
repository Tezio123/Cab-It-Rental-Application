package com.cabit.Cab_It.controller.location;

import com.cabit.Cab_It.model.Location;
import com.cabit.Cab_It.service.LocationService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@WebServlet(name = "fetch-location", value = "/fetch/location")
public class LocationFetchServlet extends HttpServlet {
    /*
     * Servlet class to perform fetch operations related to a location
     * */
    private final LocationService locationService = new LocationService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/Cab_It/location/search.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Location> locations = locationService.getLocations();
        Map<String, Location> locationMap = locationService.getLocationMap();

        locations.sort(
                new Comparator<Location>() {
                    @Override
                    public int compare(Location o1, Location o2) {
                        return o2.getId().compareTo(o1.getId());
                    }
                }
        );

        String locationId = request.getParameter("location-id");

        if(locationId.equals("*"))
            session.setAttribute("location-fetch-result", locations);

        else if(locationMap.containsKey(locationId))
                session.setAttribute("location-fetch-result", Arrays.asList(locationMap.get(locationId)));
        else
            session.setAttribute("location-fetch-result", "not-found");

        response.sendRedirect("/Cab_It/fetch/location");
    }
}
