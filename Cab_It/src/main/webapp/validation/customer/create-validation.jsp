<%@ page import="com.cabit.Cab_It.validation.CustomerValidation" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body>

</body>
    <%
        CustomerValidation customerValidation = new CustomerValidation();

        String nic = request.getParameter("nic");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");
        String firstName = request.getParameter("first-name");
        String midName = request.getParameter("mid-name");
        String lastName = request.getParameter("last-name");
        String addressLine1 = request.getParameter("address-line-1");
        String addressLine2 = request.getParameter("address-line-2");
        String addressLine3 = request.getParameter("address-line-3");
        String city = request.getParameter("city");
        String phone = request.getParameter("phone");
        String photo = request.getParameter("photo");

        session.setAttribute("prev-nic", nic);
        session.setAttribute("prev-uname", username);
        session.setAttribute("prev-passwd", password);
        session.setAttribute("prev-confirm-passwd", confirmPassword);
        session.setAttribute("prev-fname", firstName);
        session.setAttribute("prev-mname", midName);
        session.setAttribute("prev-lname", lastName);
        session.setAttribute("prev-address-1", addressLine1);
        session.setAttribute("prev-address-2", addressLine2);
        session.setAttribute("prev-address-3", addressLine3);
        session.setAttribute("prev-city", city);
        session.setAttribute("prev-phone", phone);

        Map<String, String> invalidations = customerValidation.checkFieldInvalidations(
                nic,
                username,
                password,
                confirmPassword,
                firstName,
                lastName,
                addressLine1,
                addressLine2,
                city,
                phone
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

            session.setAttribute("nic", nic);
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            session.setAttribute("first-name", firstName);
            session.setAttribute("mid-name", midName);
            session.setAttribute("last-name", lastName);
            session.setAttribute("address-line-1", addressLine1);
            session.setAttribute("address-line-2", addressLine2);
            session.setAttribute("address-line-3", addressLine3);
            session.setAttribute("city", city);
            session.setAttribute("phone", phone);
            session.setAttribute("photo", photo);

            session.setAttribute("do-post", 1);

            response.sendRedirect("/Cab_It/register/customer");
        }
        else
        {
            for(String field : invalidations.keySet())
                session.setAttribute(field, invalidations.get(field));

            response.sendRedirect("/Cab_It/customer/create.jsp");
        }
    %>
</html>
