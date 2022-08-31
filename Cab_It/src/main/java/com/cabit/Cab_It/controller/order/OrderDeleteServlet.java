package com.cabit.Cab_It.controller.order;

import com.cabit.Cab_It.helper.DateTimeHelper;
import com.cabit.Cab_It.model.Order;
import com.cabit.Cab_It.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "order-delete", value = "/delete/order")
public class OrderDeleteServlet extends HttpServlet {
    /*
     * Servlet class to perform delete operations related to a order
     * */
    private final OrderService orderService = new OrderService();
    private final DateTimeHelper dateTimeHelper = new DateTimeHelper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String confirmation = request.getParameter("delete-confirmation");

        if(confirmation.equals("yes"))
        {
            Order order = (Order) session.getAttribute("delete-order");

            order = orderService.deleteOrder(
                    order.getCustomer().getId(),
                    order.getVehicle().getId(),
                    order.getFromLocation().getId(),
                    order.getToLocation().getId(),
                    order.getRequestedDateTime()
            );

            if(order == null)
                session.setAttribute("order-delete-status", "invalid");
            else
                session.setAttribute("order-delete-status", "success");
        }
        response.sendRedirect("/Cab_It/fetch/order");
    }
}
