<%@ page import="com.cabit.Cab_It.model.Employee" %>
<%@ page import="java.time.Period" %>
<%@ page import="java.util.Base64" %>
<%@ page import="com.cabit.Cab_It.helper.AccessPrivilegeHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Employee Profile</title>
	    <link rel="stylesheet" href="CSS/style6.css">
	    <link rel="preconnect" href="https://fonts.gstatic.com">
	    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
	    <link rel="preconnect" href="https://fonts.gstatic.com">    
	    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
	</head>

    <body>
        <%
            if(session.getAttribute("logged-employee") == null)
            {
                response.sendRedirect("/Cab_It/login");
                return;
            }
            AccessPrivilegeHelper accessPrivilegeHelper = new AccessPrivilegeHelper();
            Employee loggedEmployee = (Employee) session.getAttribute("logged-employee");

            if (!accessPrivilegeHelper.checkAccessPrivilegesForEmployee(loggedEmployee, "read")) {
                response.sendRedirect("/Cab_It/error/no-access.jsp");
                return;
            }

            loggedEmployee.setDerivedAttributes();
        %>

        <div class="head-te">
			<h2>Your Profile Here</h2>

        <%
            Object employeeOptionalUpdateStatus = session.getAttribute("employee-optional-update-status");

            if(employeeOptionalUpdateStatus != null)
            {
                if(employeeOptionalUpdateStatus.toString().equals("success"))
                {
                    out.println("<font color='blue'>");
                    out.println("<br><h4>Account Updated Successfully!</h4>");
                    out.println("</font>");
                }
                session.removeAttribute("employee-optional-update-status");
            }
        %>
        
        </div>

        <%
            String titleTemplate = "<h3>%s</h3>";
            String imageTemplate = "<img src='%s'><br>";
            String fieldTemplate = "<p><b>%s</b>  : %s </p>";
            String hyperlinkTemplate = "<a href='%s'>%s</a>";

            String id = loggedEmployee.getId();
            String fullName = loggedEmployee.getFirstName()
                .concat(" ")
                .concat(loggedEmployee.getMidName())
                .concat(" ")
                .concat(loggedEmployee.getLastName());
            String username = loggedEmployee.getUsername();
            String password = loggedEmployee.getPassword();
            String nic = loggedEmployee.getNic();
            String address = loggedEmployee.getAddressLine1()
                .concat(", ")
                .concat(loggedEmployee.getAddressLine2())
                .concat(", ")
                .concat(loggedEmployee.getAddressLine3());
            String city = loggedEmployee.getCity();
            String phone = loggedEmployee.getPhone();
            String bloodGroup = loggedEmployee.getBloodGroup();
            String license = loggedEmployee.getLicense();
            String locationDistrict = loggedEmployee.getLocation().getDistrict();
            String vehiclePlateNumber = loggedEmployee.getVehicle().getPlateNumber();
            String ageInYears = String.valueOf(loggedEmployee.getAgeInYears());
            String gender = loggedEmployee.getGender().toString();
            String dob = loggedEmployee.getDob().toString();
            String serviceTime = "(New employee)";
            Period period = loggedEmployee.getServiceTime();

            if(period.getYears() > 0)
                serviceTime = String.valueOf(period.getYears()).concat(" years");
            else if(period.getMonths() > 0)
                serviceTime = String.valueOf(period.getMonths()).concat(" months");
            else if(period.getDays() > 0)
                serviceTime = String.valueOf(period.getDays()).concat(" days");

            String photoUrl = "data:image/jpg;base64," + Base64.getEncoder().encodeToString(loggedEmployee.getPhoto());

            out.println("<div class='row'>");
            	out.println("<div class='column'>");
            		out.println("<div class='card'>");
            			out.println("<div class='image-btn'>");
            				out.println(String.format(imageTemplate, photoUrl));
            			out.println("</div>");
            			out.println("<div class='container'>");
				            out.println(String.format(titleTemplate, id));
				            out.println(String.format(fieldTemplate, "National Identity Card", nic));
				            out.println(String.format(fieldTemplate, "Username", username));
				            out.println(String.format(fieldTemplate, "Password", password));
				            out.println(String.format(fieldTemplate, "Name", fullName));
				            out.println(String.format(fieldTemplate, "Age", ageInYears));
				            out.println(String.format(fieldTemplate, "Gender", gender));
				            out.println(String.format(fieldTemplate, "Birthday", dob));
				            out.println(String.format(fieldTemplate, "Address", address));
				            out.println(String.format(fieldTemplate, "City", city));
				            out.println(String.format(fieldTemplate, "Phone", phone));
				            out.println(String.format(fieldTemplate, "Blood group", bloodGroup));
				            out.println(String.format(fieldTemplate, "License", license));
				            out.println(String.format(fieldTemplate, "Service Time", serviceTime));
				            out.println(String.format(fieldTemplate, "Service location", locationDistrict));
				            out.println(String.format(fieldTemplate, "Vehicle Plate Number", String.format(hyperlinkTemplate, "/Cab_It/profile/vehicle", vehiclePlateNumber)));
				            out.println(String.format(hyperlinkTemplate, "/Cab_It/update-optional/employee", "<h4 id='signup-btn-3'>Update</h4"));
            			out.println("</div>");
            		out.println("</div>");
            	out.println("</div>");
            out.println("</div>");
        %>

        <form action="/Cab_It/dashboard">
            <input type="submit" value="Dashboard" id="signup-btn-1">
        </form>
    </body>
</html>
