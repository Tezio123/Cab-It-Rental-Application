<%@ page import="com.cabit.Cab_It.validation.AdvertisementValidation" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body>
    <%
        AdvertisementValidation advertisementValidation = new AdvertisementValidation();

        String content = request.getParameter("content");
        String photo = request.getParameter("photo");

        session.setAttribute("prev-content", content);
        session.setAttribute("prev-photo", photo);

        Map<String, String> invalidations = advertisementValidation.checkFieldInvalidations(
                content
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

            session.setAttribute("content", content);
            session.setAttribute("photo", photo);

            session.setAttribute("do-post", 1);

            response.sendRedirect("/Cab_It/register/advertisement");
        }
        else
        {
            for(String field : invalidations.keySet())
                session.setAttribute(field, invalidations.get(field));

            response.sendRedirect("/Cab_It/advertisement/create.jsp");
        }
    %>
</body>
</html>
