<%@ page import="com.cabit.Cab_It.validation.EmployeeValidation" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<body>
    <%
        EmployeeValidation employeeValidation = new EmployeeValidation();

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
        String bloodGroup = request.getParameter("blood-group");
        String license = request.getParameter("license");
        String location = request.getParameter("location");
        String vehicle = request.getParameter("vehicle");
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
        session.setAttribute("prev-blood-group", bloodGroup);
        session.setAttribute("prev-license", license);

        Map<String, String> invalidations = employeeValidation.checkFieldInvalidations(
                nic,
                username,
                password,
                confirmPassword,
                firstName,
                lastName,
                addressLine1,
                addressLine2,
                city,
                phone,
                bloodGroup,
                license
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
            session.setAttribute("blood-group", bloodGroup);
            session.setAttribute("license", license);
            session.setAttribute("location", location);
            session.setAttribute("vehicle", vehicle);
            session.setAttribute("photo", photo);

            session.setAttribute("do-post", 1);

            response.sendRedirect("/Cab_It/register/employee");
        }
        else
        {
            for(String field : invalidations.keySet())
                session.setAttribute(field, invalidations.get(field));

            response.sendRedirect("/Cab_It/employee/create.jsp");
        }
    %>
</body>
</html>
