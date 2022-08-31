<%@ page import="com.cabit.Cab_It.validation.LocationValidation" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body>

    <%
        String district = request.getParameter("district");
        String zone = request.getParameter("zone");


        session.setAttribute("prev-district", district);
        session.setAttribute("prev-zone", zone);

        LocationValidation locationValidation = new LocationValidation();

        Map<String, String> invalidations = locationValidation.checkFieldInvalidations(
                zone
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

            session.setAttribute("district", district);
            session.setAttribute("zone", zone);

            session.setAttribute("do-post", 1);

            response.sendRedirect("/Cab_It/register/location");
        }
        else
        {
            for(String field : invalidations.keySet())
                session.setAttribute(field, invalidations.get(field));

            response.sendRedirect("/Cab_It/location/create.jsp");
        }
    %>
</body>

</html>
