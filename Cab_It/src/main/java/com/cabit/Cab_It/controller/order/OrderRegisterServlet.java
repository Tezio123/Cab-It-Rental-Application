package com.cabit.Cab_It.controller.order;

import com.cabit.Cab_It.model.Order;
import com.cabit.Cab_It.service.LocationService;
import com.cabit.Cab_It.service.OrderService;
import com.cabit.Cab_It.service.VehicleService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "order-register", value = "/register/order")
public class OrderRegisterServlet extends HttpServlet {
    /*
     * Servlet class to perform register operations related to a order
     * */
    private final OrderService orderService = new OrderService();
    private final LocationService locationService = new LocationService();
    private final VehicleService vehicleService = new VehicleService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        session.setAttribute("locations", locationService.getLocationMap());
        session.setAttribute("vehicles", vehicleService.getVehicleMap());

        response.sendRedirect("/Cab_It/order/create-step-1.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String customerId = session.getAttribute("customer-id").toString();
        String startLocationId = session.getAttribute("start-location-id").toString();
        String endLocationId = session.getAttribute("end-location-id").toString();
        String vehicleId = session.getAttribute("vehicle-id").toString();

        Order order = orderService.addOrder(
                customerId,
                vehicleId,
                startLocationId,
                endLocationId
        );

        if(order == null)
            session.setAttribute("order-register-status", "invalid");
        else
            session.setAttribute("order-register-status", "success");
    }
}
