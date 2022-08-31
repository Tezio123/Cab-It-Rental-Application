<%@ page import="com.cabit.Cab_It.model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.time.Period" %>
<%@ page import="com.cabit.Cab_It.service.EmployeeService" %>
<%@ page import="com.cabit.Cab_It.helper.AccessPrivilegeHelper" %>
<%@ page import="com.cabit.Cab_It.model.Admin" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Employee Search</title>
	    <link rel="stylesheet" href="CSS/style3.css">
	    <link rel="preconnect" href="https://fonts.gstatic.com">
	    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
	    <link rel="preconnect" href="https://fonts.gstatic.com">    
	    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
	</head>
    <body>

        <%
            if(session.getAttribute("logged-admin") == null)
            {
                response.sendRedirect("/Cab_It/login");
                return;
            }
            AccessPrivilegeHelper accessPrivilegeHelper = new AccessPrivilegeHelper();
            Admin loggedAdmin = (Admin) session.getAttribute("logged-admin");

            if (!accessPrivilegeHelper.checkAccessPrivilegesForEmployee(loggedAdmin, "read")) {
                response.sendRedirect("/Cab_It/error/no-access.jsp");
                return;
            }
        %>

        <div class="head-te">
            <h2>Find an employee here</h2>

        <%
            Object employeeSignupStatus = session.getAttribute("employee-signup-status");
            Object employeeUpdateStatus = session.getAttribute("employee-update-status");
            Object employeeDeleteStatus = session.getAttribute("employee-delete-status");

            if(employeeSignupStatus != null)
            {
                if(employeeSignupStatus.toString().equals("success"))
                {
                    out.println("<font color='blue'>");
                    out.println("<br><h4>Employee Registered Successfully!</h4>");
                    out.println("</font>");
                }
                session.removeAttribute("employee-signup-status");
            }
            else if(employeeUpdateStatus != null)
            {
                if(employeeUpdateStatus.toString().equals("success"))
                {
                    out.println("<font color='blue'>");
                    out.println("<br><h4>Employee Updated Successfully!</h4>");
                    out.println("</font>");
                }
                session.removeAttribute("employee-update-status");
            }

            else if(employeeDeleteStatus != null)
            {
                if(employeeDeleteStatus.toString().equals("success"))
                {
                    out.println("<font color='blue'>");
                    out.println("<br><h4>Employee Deleted Successfully!</h4>");
                }

                else if(employeeDeleteStatus.toString().equals("invalid"))
                {
                    out.println("<font color='red'>");
                    out.println("<br><h4>Employee in-use or unable to delete</h4>");
                }
                out.println("</font>");
                session.removeAttribute("employee-delete-status");
            }
        %>
		
		</div>
		<div class="main-container">
        
           <form action="/Cab_It/fetch/employee" method="POST">
               <input type="text" placeholder="Employee-ID.." name="employee-id">
           	<button type="submit" >Search</button>
           </form>
           
           <form action="/Cab_It/dashboard">
               <input type="submit" value="Dashboard" id="signup-btn-1">
           </form>
           <br>
        </div>
        
        <%
            Object fetchResult = session.getAttribute("employee-fetch-result");
            EmployeeService employeeService = new EmployeeService();

            if(fetchResult != null) {
                if (!fetchResult.toString().equals("not-found")) {
                    List<Employee> employees = (List<Employee>) session.getAttribute("employee-fetch-result");

                    String titleTemplate = "<h3>%s</h3>";
                    String imageTemplate = "<img src='%s'><br>";
                    String fieldTemplate = "<p> <b>%s</b>  : %s </p>";
                    String hyperlinkTemplate = "<a href='%s'>%s</a>";

                    for (Employee employee : employees) {

                        String id = employee.getId();
                        String fullName = employee.getFirstName()
                                .concat(" ")
                                .concat(employee.getMidName())
                                .concat(" ")
                                .concat(employee.getLastName());
                        String username = employee.getUsername();
                        String password = employee.getPassword();
                        String nic = employee.getNic();
                        String address = employee.getAddressLine1()
                                .concat(", ")
                                .concat(employee.getAddressLine2())
                                .concat(", ")
                                .concat(employee.getAddressLine3());
                        String city = employee.getCity();
                        String phone = employee.getPhone();
                        String bloodGroup = employee.getBloodGroup();
                        String license = employee.getLicense();
                        String locationDistrict = employee.getLocation().getDistrict();
                        String vehiclePlateNumber = employee.getVehicle().getPlateNumber();
                        String ageInYears = String.valueOf(employee.getAgeInYears());
                        String gender = employee.getGender().toString();
                        String dob = employee.getDob().toString();
                        String serviceTime = "(New employee)";
                        Period period = employee.getServiceTime();

                        String onlineOfflineStatus = employeeService
                                .getLoggedEmployeeMap()
                                .containsKey(id) ? "Online" : "Offline";

                        if(period.getYears() > 0)
                            serviceTime = String.valueOf(period.getYears()).concat(" years");
                        else if(period.getMonths() > 0)
                            serviceTime = String.valueOf(period.getMonths()).concat(" months");
                        else if(period.getDays() > 0)
                            serviceTime = String.valueOf(period.getDays()).concat(" days");

                        String photoUrl = "data:image/jpg;base64," + Base64.getEncoder().encodeToString(employee.getPhoto());

                        out.println("<div class='row'>");
                        	out.println("<div class='column'>");
                        		out.println("<div class='card'>");
                        			out.println("<div class='image-btn'>");
                        				out.println(String.format(imageTemplate, photoUrl));
                        			out.println("</div>");
                        			out.println("<div class='container'>");
                        				out.println(String.format(titleTemplate, id));
				                        out.println(String.format(fieldTemplate, "Status", onlineOfflineStatus));
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
				                        out.println(String.format(fieldTemplate, "Vehicle", vehiclePlateNumber));
				                        out.println(String.format(hyperlinkTemplate, "/Cab_It/update/employee?id=".concat(id), "<h4 id='signup-btn-2'>Update</h4>"));
				                        out.println(String.format(hyperlinkTemplate, "/Cab_It/delete/employee?id=".concat(id), "<h4 id='signup-btn-3'>Delete</h4>"));
                        			out.println("</div>");
                        		out.println("</div>");
                        	out.println("</div>");
                        out.println("</div>");
                    }
                }
                else if(fetchResult.toString().equals("not-found"))
                {
                	out.println("<center><h3>Employee not found!</h3></center>");
                }
                session.removeAttribute("employee-fetch-result");
            }
        %>
    </body>
</html>
