<%@ page import="com.cabit.Cab_It.model.Vehicle" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.Period" %>
<%@ page import="java.util.Base64" %>
<%@ page import="com.cabit.Cab_It.helper.AccessPrivilegeHelper" %>
<%@ page import="com.cabit.Cab_It.model.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
	    <title>Rent Your Cab</title>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link rel="stylesheet" href="CSS/style4.css">
	    <link rel="preconnect" href="https://fonts.gstatic.com">
	    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
	    <link rel="preconnect" href="https://fonts.gstatic.com">    
	    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
	</head>
	<body>
	    <%
	        if(session.getAttribute("logged-customer") == null)
	        {
	            response.sendRedirect("/Cab_It/login");
	            return;
	        }
	        if(session.getAttribute("location-vehicles") == null)
	        {
	            response.sendRedirect("/Cab_It/dashboard/customer");
	            return;
	        }
	        AccessPrivilegeHelper accessPrivilegeHelper = new AccessPrivilegeHelper();
	        Customer loggedCustomer = (Customer) session.getAttribute("logged-customer");
	
	        if (!accessPrivilegeHelper.checkAccessPrivilegesForOrder(loggedCustomer, "create")) {
	            response.sendRedirect("/Cab_It/error/no-access.jsp");
	            return;
	        }
	    %>
        
        <div class="head-te">
        	<h2>Cabs near you..</h2>
        </div>
        
        <%
	        List<Vehicle> vehicles = (List<Vehicle>) session.getAttribute("location-vehicles");
	
	        String titleTemplate = "<h3>%s</h3>";
	        String imageTemplate = "<img src='%s'><br>";
	        String fieldTemplate = "<p><b>%s<b/>  : %s </p>";
	
	        for(Vehicle vehicle : vehicles)
	        {
	            vehicle.setDerivedAttributes();
	
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
					            out.println(String.format(titleTemplate, plateNumber));
					            out.println(String.format(fieldTemplate, "Model", model));
					            out.println(String.format(fieldTemplate, "Brand", brand));
					            out.println(String.format(fieldTemplate, "Engine", engineType));
					            out.println(String.format(fieldTemplate, "Fuel", fuelType));
					            out.println(String.format(fieldTemplate, "Top Speed", topSpeed));
					            out.println(String.format(fieldTemplate, "Service Time", serviceTime));
	
					            out.println("<form action='/Cab_It/order/create-step-3.jsp' method='GET'>");
					            	out.println(String.format("<input type='submit' value='Choose' name=VEH-%s id='signup-btn-7'>", vehicle.getId()));
					            out.println("</form>");
				
	           	 			out.println("</div>");
	            		out.println("</div>");
	            	out.println("</div>");
	            out.println("</div>");
	        }
	    %>
	    <form action="/Cab_It/order/create-step-1.jsp" method="GET">
	        <input type="submit" value="Back" id="signup-btn">
	    </form>
		
	   <form action="/Cab_It/dashboard">
	       <input type="submit" value="Dashboard" id="signup-btn-1">
	   </form>
    	
	</body>
</html>
