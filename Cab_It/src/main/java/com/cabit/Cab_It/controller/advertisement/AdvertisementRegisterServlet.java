package com.cabit.Cab_It.controller.advertisement;

import com.cabit.Cab_It.model.Advertisement;
import com.cabit.Cab_It.service.AdvertisementService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "advertisement-register", value = "/register/advertisement")
public class AdvertisementRegisterServlet extends HttpServlet {
    /*
     * Servlet class to perform register operations related to a advertisement
     * */
    private final AdvertisementService advertisementService = new AdvertisementService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(session.getAttribute("do-post") != null)
        {
            session.removeAttribute("do-post");
            doPost(request, response);
        }
        else
            response.sendRedirect("/Cab_It/advertisement/create.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String content = session.getAttribute("content").toString();
        String photoPath = session.getAttribute("photo").toString();
        File photoFile = photoPath.isEmpty() ? null : new File(photoPath);

        Advertisement advertisement = advertisementService.addAdvertisement(
                content,
                photoFile
        );

        if(advertisement == null)
        {
            session.setAttribute("advertisement-register-status", "invalid");
            response.sendRedirect("/Cab_It/register/advertisement");
        }
        else
        {
            session.setAttribute("advertisement-register-status", "success");
            response.sendRedirect("/Cab_It/fetch/advertisement");
        }
    }
}
