package com.cabit.Cab_It.controller.advertisement;

import com.cabit.Cab_It.model.Advertisement;
import com.cabit.Cab_It.service.AdvertisementService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "advertisement-delete", value = "/delete/advertisement")
public class AdvertisementDeleteServlet extends HttpServlet {
    /*
     * Servlet class to perform delete operations related to a advertisement
     * */
    private final AdvertisementService advertisementService = new AdvertisementService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        HttpSession session = request.getSession();

        for(Advertisement advertisement : advertisementService.getAdvertisements())
        {
            if(advertisement.getId().equals(id))
            {
                session.setAttribute("delete-advertisement", advertisement);
                response.sendRedirect("/Cab_It/advertisement/delete.jsp");

                return;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String confirmation = request.getParameter("delete-confirmation");

        if(confirmation.equals("yes"))
        {
            Advertisement advertisement = (Advertisement) request.getSession().getAttribute("delete-advertisement");

            advertisement = advertisementService.deleteAdvertisement(advertisement.getId());

            if(advertisement == null)
                session.setAttribute("advertisement-delete-status", "invalid");

            else
                session.setAttribute("advertisement-delete-status", "success");

        }
        response.sendRedirect("/Cab_It/fetch/advertisement");
    }
}
