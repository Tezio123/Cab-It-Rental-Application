package com.cabit.Cab_It.controller.advertisement;

import com.cabit.Cab_It.model.Advertisement;
import com.cabit.Cab_It.service.AdvertisementService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "advertisement-update", value = "/update/advertisement")
public class AdvertisementUpdateServlet extends HttpServlet {
    /*
     * Servlet class to perform update operations related to a advertisement
     * */
    private final AdvertisementService advertisementService = new AdvertisementService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        HttpSession session = request.getSession();

        if(id != null) {
            for (Advertisement advertisement : advertisementService.getAdvertisements()) {
                if (advertisement.getId().equals(id)) {
                    session.setAttribute("update-advertisement", advertisement);
                    break;
                }
            }
            response.sendRedirect("/Cab_It/advertisement/update.jsp");
        }
        else if(session.getAttribute("do-post") != null)
        {
            session.removeAttribute("do-post");
            doPost(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String id = session.getAttribute("advertisement-id").toString();
        String content = session.getAttribute("content").toString();
        String photoPath = session.getAttribute("photo").toString();
        File photoFile = photoPath.isEmpty() ? null : new File(photoPath);

        Advertisement advertisement = advertisementService.setAdvertisement(
                id,
                content,
                photoFile
        );

        if(advertisement == null)
        {
            session.setAttribute("advertisement-update-status", "invalid");
            response.sendRedirect("/Cab_It/update/advertisement");
        }
        else
        {
            session.setAttribute("advertisement-update-status", "success");
            response.sendRedirect("/Cab_It/fetch/advertisement");
        }
    }
}
