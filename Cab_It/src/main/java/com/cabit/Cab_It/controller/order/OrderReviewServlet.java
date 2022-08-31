package com.cabit.Cab_It.controller.order;

import com.cabit.Cab_It.helper.DateTimeHelper;
import com.cabit.Cab_It.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "order-review", value = "/review/order")
public class OrderReviewServlet extends HttpServlet {
    /*
     * Servlet class to perform review operations related to a order
     * */
    private final OrderService orderService = new OrderService();
    private final DateTimeHelper dateTimeHelper = new DateTimeHelper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String review = request.getParameter("review");
        String customerId = request.getParameter("customer-id");
        String vehicleId = request.getParameter("vehicle-id");
        String fromLocationId = request.getParameter("from-location-id");
        String toLocationId = request.getParameter("to-location-id");
        String requestedDateTimeStr = request.getParameter("req-date-time");

        requestedDateTimeStr = requestedDateTimeStr.substring(0, 10)
                .concat(" ")
                .concat(requestedDateTimeStr.substring(11, 19));

        LocalDateTime requestedDateTime = dateTimeHelper.getFormattedDateTime(requestedDateTimeStr);

        orderService.
            reviewOrder(
                    customerId,
                    vehicleId,
                    fromLocationId,
                    toLocationId,
                    requestedDateTime,
                    review
            );
        response.sendRedirect("/Cab_It/fetch/order");
    }
}
