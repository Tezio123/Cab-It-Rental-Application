<%@ page import="com.cabit.Cab_It.validation.VehicleValidation" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

    <body>
    <%
        String model = request.getParameter("model");
        String brand = request.getParameter("brand");
        String fuelType = request.getParameter("fuel-type");
        String engineType = request.getParameter("engine-type");
        String plateNumber = request.getParameter("plate-number");
        String topSpeed = request.getParameter("top-speed");
        String photo = request.getParameter("photo");

        session.setAttribute("prev-model", model);
        session.setAttribute("prev-brand", brand);
        session.setAttribute("prev-fuel-type", fuelType);
        session.setAttribute("prev-engine-type", engineType);
        session.setAttribute("prev-plate-number", plateNumber);
        session.setAttribute("prev-top-speed", topSpeed);
        session.setAttribute("prev-photo", photo);

        VehicleValidation vehicleValidation = new VehicleValidation();

        Map<String, String> invalidations = vehicleValidation.checkFieldInvalidationsForUpdate(
                model,
                brand,
                plateNumber,
                topSpeed
        );

        Enumeration<String> sessionAttributes =  session.getAttributeNames();

        while (sessionAttributes.hasMoreElements())
        {
            String element = sessionAttributes.nextElement();

            if(element.endsWith("invalidation"))
                session.removeAttribute(element);
        }

        if(invalidations.isEmpty())
        {
            sessionAttributes = session.getAttributeNames();

            while (sessionAttributes.hasMoreElements())
            {
                String element = sessionAttributes.nextElement();

                if(element.startsWith("prev"))
                    session.removeAttribute(element);
            }

            session.setAttribute("model", model);
            session.setAttribute("brand", brand);
            session.setAttribute("fuel-type", fuelType);
            session.setAttribute("engine-type", engineType);
            session.setAttribute("plate-number", plateNumber);
            session.setAttribute("top-speed", topSpeed);
            session.setAttribute("photo", photo);

            session.setAttribute("do-post", 1);

            response.sendRedirect("/Cab_It/update/vehicle");
        }
        else
        {
            for(String field : invalidations.keySet())
                session.setAttribute(field, invalidations.get(field));

            response.sendRedirect("/Cab_It/vehicle/update.jsp");
        }
    %>
    </body>
</html>
