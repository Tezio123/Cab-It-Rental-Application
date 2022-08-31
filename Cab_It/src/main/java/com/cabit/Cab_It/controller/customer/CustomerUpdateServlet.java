package com.cabit.Cab_It.controller.customer;

import com.cabit.Cab_It.model.Customer;
import com.cabit.Cab_It.service.CustomerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "customer-update", value = "/update/customer")
public class CustomerUpdateServlet extends HttpServlet {
    /*
     * Servlet class to perform update operations related to a customer
     * */
    private final CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(session.getAttribute("do-post") != null)
        {
            doPost(request, response);
            session.removeAttribute("do-post");
        }
        else if(session.getAttribute("logged-customer") != null)
        {
            response.sendRedirect("/Cab_It/customer/update.jsp");
        }
        else
        {
            response.sendRedirect("/Cab_It/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String id = session.getAttribute("customer-id").toString();
        String nic = session.getAttribute("nic").toString();
        String username = session.getAttribute("username").toString();
        String password = session.getAttribute("password").toString();
        String firstName = session.getAttribute("first-name").toString();
        String midName = session.getAttribute("mid-name").toString();
        String lastName = session.getAttribute("last-name").toString();
        String addressLine1 = session.getAttribute("address-line-1").toString();
        String addressLine2 = session.getAttribute("address-line-2").toString();
        String addressLine3 = session.getAttribute("address-line-3").toString();
        String city = session.getAttribute("city").toString();
        String phone = session.getAttribute("phone").toString();
        String photoPath = session.getAttribute("photo").toString();
        File photo = photoPath.isEmpty() ? null : new File(photoPath);

        Customer customer = customerService.setCustomer(
                id,
                username,
                password,
                nic,
                firstName,
                midName,
                lastName,
                addressLine1,
                addressLine2,
                addressLine3,
                city,
                phone,
                photo
        );

        if(customer == null)
        {
            session.setAttribute("customer-update-status", "invalid");
            response.sendRedirect("/Cab_It/update/customer");
        }
        else
        {
            session.setAttribute("customer-update-status", "success");

            session.setAttribute("logged-customer", customer);
            response.sendRedirect("/Cab_It/profile/customer");
        }
    }
}
