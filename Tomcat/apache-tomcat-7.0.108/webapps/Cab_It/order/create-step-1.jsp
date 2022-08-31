<%@ page import="com.cabit.Cab_It.model.Location" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.cabit.Cab_It.model.Customer" %>
<%@ page import="com.cabit.Cab_It.helper.AccessPrivilegeHelper" %>
<%@ page import="com.cabit.Cab_It.model.Admin" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

	<head>
	    <title>Rent Your Cab</title>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link rel="stylesheet" href="CSS/style1.css">
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
	        if(session.getAttribute("locations") == null)
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
	
	    <%
	        Customer customer = (Customer) session.getAttribute("logged-customer");
	        session.setAttribute("customer-id", customer.getId());
	    %>
		
		
		<%
	        String contentTemplate = "<option value='%s'>%s</option>";
	        Map<String, Location> locationMap = (Map) session.getAttribute("locations");
	    %>
	
	    <div class="container">
	    	<div class="head-te">
		    	<h2>To rent a cab please follow these steps</h2><br>
		    	<h3>1.Select the start and end of your journey</h3>
		    </div>
		    
		   <div class="main-container">
		   		<form action="/Cab_It/fetch/location/vehicles" method="GET">
			        Start :
			        <select name="start-location">
			            <%
			                Set<String> visited = new HashSet<String>();
			
			                for(Location location : locationMap.values()) {
			
			                    String district = location.getDistrict();
			
			                    if(!visited.contains(district)) {
			                        String itemContent = String.format(contentTemplate,
			                                location.getId(),
			                                district
			                        );
			
			                        visited.add(district);
			
			                        out.println(itemContent);
			                    }
			                }
			            %>
			        </select><br>
			
			        End :
			        <select name="end-location">
			            <%
			                visited = new HashSet<String>();
			
			                for(Location location : locationMap.values()) {
			
			                    String district = location.getDistrict();
			
			                    if(!visited.contains(district)) {
			                        String itemContent = String.format(contentTemplate,
			                                location.getId(),
			                                district
			                        );
			
			                        visited.add(district);
			
			                        out.println(itemContent);
			                    }
			                }
			            %>
			        </select><br>
			        <input type="submit" value="Next" id="signup-btn">
			    </form>
		
			    <form action="/Cab_It/dashboard">
			        <input type="submit" value="Dashboard" id="signup-btn-1">
			    </form>
		    </div>
	 	</div>
	</body>
</html>