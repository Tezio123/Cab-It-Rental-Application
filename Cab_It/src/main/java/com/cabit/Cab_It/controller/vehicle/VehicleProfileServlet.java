package com.cabit.Cab_It.controller.vehicle;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "vehicle-profile", value = "/profile/vehicle")
public class VehicleProfileServlet extends HttpServlet {
    /*
     * Servlet class to perform profile operations related to a vehicle
     * */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/Cab_It/vehicle/profile.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
