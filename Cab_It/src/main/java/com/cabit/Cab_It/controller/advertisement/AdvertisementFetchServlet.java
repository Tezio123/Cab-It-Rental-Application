package com.cabit.Cab_It.controller.advertisement;

import com.cabit.Cab_It.model.Advertisement;
import com.cabit.Cab_It.service.AdvertisementService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@WebServlet(name = "advertisement-fetch", value = "/fetch/advertisement")
public class AdvertisementFetchServlet extends HttpServlet {
    /*
     * Servlet class to perform fetching operations related to a advertisement
     * */
    private final AdvertisementService advertisementService = new AdvertisementService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/Cab_It/advertisement/search.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Advertisement> advertisements = advertisementService.getAdvertisements();
        Map<String, Advertisement> advertisementMap = advertisementService.getAdvertisementMap();

        advertisements.sort(
                new Comparator<Advertisement>() {
                    @Override
                    public int compare(Advertisement o1, Advertisement o2) {
                        return o2.getIntroducedDateTime().compareTo(o1.getIntroducedDateTime());
                    }
                }
        );

        String advertisementId = request.getParameter("advertisement-id");

        if(advertisementId.equals("*"))
            session.setAttribute("advertisement-fetch-result", advertisements);

        else if(advertisementMap.containsKey(advertisementId))
            session.setAttribute("advertisement-fetch-result", Arrays.asList(advertisementMap.get(advertisementId)));
        else
            session.setAttribute("advertisement-fetch-result", "not-found");

        response.sendRedirect("/Cab_It/fetch/advertisement");
    }
}
