<%@ page import="com.cabit.Cab_It.validation.EmployeeValidation" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body>

<%
    EmployeeValidation employeeValidation = new EmployeeValidation();

    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String addressLine1 = request.getParameter("address-line-1");
    String addressLine2 = request.getParameter("address-line-2");
    String addressLine3 = request.getParameter("address-line-3");
    String city = request.getParameter("city");
    String phone = request.getParameter("phone");

    Map<String, String> invalidations = employeeValidation.checkFieldInvalidations(
            username,
            password,
            addressLine1,
            addressLine2,
            city,
            phone
    );

    session.setAttribute("prev-uname", username);
    session.setAttribute("prev-passwd", password);
    session.setAttribute("prev-address-1", addressLine1);
    session.setAttribute("prev-address-2", addressLine2);
    session.setAttribute("prev-address-3", addressLine3);
    session.setAttribute("prev-city", city);
    session.setAttribute("prev-phone", phone);

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

        session.setAttribute("username", username);
        session.setAttribute("password", password);
        session.setAttribute("address-line-1", addressLine1);
        session.setAttribute("address-line-2", addressLine2);
        session.setAttribute("address-line-3", addressLine3);
        session.setAttribute("city", city);
        session.setAttribute("phone", phone);

        session.setAttribute("do-post", 1);

        response.sendRedirect("/Cab_It/update-optional/employee");
    }
    else
    {
        for(String field : invalidations.keySet())
            session.setAttribute(field, invalidations.get(field));

        response.sendRedirect("/Cab_It/employee/optional-update.jsp");
    }
%>
</body>
</html>
