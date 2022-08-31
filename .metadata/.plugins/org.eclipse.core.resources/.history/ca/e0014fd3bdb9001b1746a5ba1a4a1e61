<%@ page import="com.cabit.Cab_It.model.Employee" %>
<%@ page import="com.cabit.Cab_It.model.Vehicle" %>
<%@ page import="java.time.Period" %>
<%@ page import="java.util.Base64" %>
<%@ page import="com.cabit.Cab_It.helper.AccessPrivilegeHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
	<head>
	    
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Vehicle Profile</title>
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
	
	        if (!accessPrivilegeHelper.checkAccessPrivilegesForVehicle(loggedEmployee, "read")) {
	            response.sendRedirect("/Cab_It/error/no-access.jsp");
	            return;
	        }
	    %>
	
	    <%
	        Employee employee = (Employee) request.getSession().getAttribute("logged-employee");
	        Vehicle vehicle = employee.getVehicle();
	
	        vehicle.setDerivedAttributes();
	    %>
		
		<div class="head-te">
			<h2>Vehicle Profile Here</h2>
		
	    <%
	        Object vehicleUpdateStatus = session.getAttribute("vehicle-optional-update-status");
	
	        if(vehicleUpdateStatus != null)
	        {
	            if(vehicleUpdateStatus.toString().equals("success"))
	            {
	                out.println("<font color='blue'>");
	                	out.println("<br><h4>Vehicle Updated Successfully!</h4>");
	                out.println("</font>");
	            }
	            session.removeAttribute("vehicle-optional-update-status");
	        }
	    %>
		</div>
		
	    <%
	        String titleTemplate = "<h3>%s</h3>";
	        String imageTemplate = "<img src='%s'><br>";
	        String fieldTemplate = "<p><b> %s </b> : %s </p>";
	        String hyperlinkTemplate = "<a href='%s'>%s</a>";
	
	        String id = vehicle.getId();
	        String model = vehicle.getModel();
	        String brand = vehicle.getBrand();
	        String engineType = vehicle.getEngineType();
	        String fuelType = vehicle.getFuelType();
	        String topSpeed = String.valueOf(vehicle.getTopSpeed());
	        String plateNumber = vehicle.getPlateNumber();
	        Period servicePeriod = vehicle.getServiceTime();
	        String serviceTime = "(New Vehicle)";
	        String photoUrl = "data:image/jpg;base64," + Base64.getEncoder().encodeToString(vehicle.getPhoto());
	
	        if(servicePeriod.getYears() > 0)
	            serviceTime = String.valueOf(servicePeriod.getYears()).concat(" years");
	        else if(servicePeriod.getMonths() > 0)
	            serviceTime = String.valueOf(servicePeriod.getMonths()).concat(" months");
	        else if(servicePeriod.getDays() > 0)
	            serviceTime = String.valueOf(servicePeriod.getDays()).concat(" days");
	
	        out.println("<div class='row'>");
	        	out.println("<div class='column'>");
	        		out.println("<div class='card'>");
	        			out.println("<div class='image-btn'>");
							out.println(String.format(imageTemplate, photoUrl));
	        			out.println("</div>");
	        			out.println("<div class='container'>");
					        out.println(String.format(titleTemplate, id));;
					        out.println(String.format(fieldTemplate, "Model", model));
					        out.println(String.format(fieldTemplate, "Brand", brand));
					        out.println(String.format(fieldTemplate, "Engine", engineType));
					        out.println(String.format(fieldTemplate, "Fuel", fuelType));
					        out.println(String.format(fieldTemplate, "Top Speed", topSpeed));
					        out.println(String.format(fieldTemplate, "Plate Number", plateNumber));
					        out.println(String.format(fieldTemplate, "Service Time", serviceTime));
					        out.println(String.format(hyperlinkTemplate, "/Cab_It/optional-update/vehicle", "<h4 id='signup-btn-3'>Update</h4>"));
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
