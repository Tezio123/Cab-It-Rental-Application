package com.cabit.Cab_It.controller.customer;

import com.cabit.Cab_It.model.Customer;
import com.cabit.Cab_It.service.CustomerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "customer-delete", value = "/delete/customer")
public class CustomerDeleteServlet extends HttpServlet {
    /*
     * Servlet class to perform delete operations related to a customer
     * */
    private final CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/Cab_It/customer/delete.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String confirmation = request.getParameter("delete-confirmation");

        if(confirmation.equals("yes"))
        {
            Customer customer = (Customer) request.getSession().getAttribute("delete-customer");

            customer = customerService.deleteCustomer(customer.getId());

            if(customer == null)
            {
                session.setAttribute("customer-delete-status", "invalid");
                response.sendRedirect("/Cab_It/profile/customer");
            }

            else
            {
                session.setAttribute("customer-delete-status", "success");
                session.removeAttribute("logged-customer");
                response.sendRedirect("/Cab_It/login");
            }
        }
        else
            response.sendRedirect("/Cab_It/profile/customer");
    }
}
