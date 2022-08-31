package com.cabit.Cab_It.controller.order;

import com.cabit.Cab_It.helper.DateTimeHelper;
import com.cabit.Cab_It.model.*;
import com.cabit.Cab_It.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "react-order", value = "/react/order")
public class OrderReactServlet extends HttpServlet {
    /*
     * Servlet class to perform react operations related to a order
     * */
    private final OrderService orderService = new OrderService();
    private final DateTimeHelper dateTimeHelper = new DateTimeHelper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(session.getAttribute("logged-employee") != null)
        {
            Employee loggedEmployee =  (Employee) session.getAttribute("logged-employee");

            boolean isDesc = true;

            if(request.getParameter("asc") != null)
                isDesc = false;

            List<Order> orders = orderService.getOrders(loggedEmployee.getVehicle(), isDesc);

            session.setAttribute("orders", orders);
            response.sendRedirect("/Cab_It/order/react.jsp");
        }
        else
        {
            response.sendRedirect("/Cab_It/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean accepted = request.getParameter("accepted") != null;

        if (accepted) {
            String customerId = request.getParameter("customer-id");
            String vehicleId = request.getParameter("vehicle-id");
            String fromLocationId = request.getParameter("from-location-id");
            String toLocationId = request.getParameter("to-location-id");
            String requestedDateTimeStr = request.getParameter("req-date-time");

            requestedDateTimeStr = dateTimeHelper.formatDateTimeStr(requestedDateTimeStr);
            LocalDateTime requestedDateTime = dateTimeHelper.getFormattedDateTime(requestedDateTimeStr);

            Order order = orderService.acceptOrder(
                    customerId,
                    vehicleId,
                    fromLocationId,
                    toLocationId,
                    requestedDateTime
            );
            if(order == null)
                session.setAttribute("order-accepted-status", "invalid");
            else
                session.setAttribute("order-accepted-status", "success");
        }
        response.sendRedirect("/Cab_It/react/order");
    }
}
