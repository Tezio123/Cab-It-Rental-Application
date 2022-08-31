<%@ page import="com.cabit.Cab_It.model.Vehicle" %>
<%@ page import="com.cabit.Cab_It.model.Employee" %>
<%@ page import="com.cabit.Cab_It.helper.AccessPrivilegeHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Vehicle Update</title>
	    <link rel="stylesheet" href="CSS/style7.css">
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
	
	        if (!accessPrivilegeHelper.checkAccessPrivilegesForVehicle(loggedEmployee, "update")) {
	            response.sendRedirect("/Cab_It/error/no-access.jsp");
	            return;
	        }
	    %>
	
	    <%
	        Employee employee = (Employee) request.getSession().getAttribute("logged-employee");
	        Vehicle vehicle = employee.getVehicle();
	
	        session.setAttribute("vehicle-id", vehicle.getId());
	    %>
	
	    <div class="container">
	    	<div class="head-te">
	        	<h2>Vehicle Update</h2>
        	</div>
    		<div class="main-container">
    			<form action="/Cab_It/optional-update/vehicle" method="POST">

        			<label for="">Engine type</label>
		            <input
		                    type="text"
		                    name="engine-type"
		                    value=<%= vehicle.getEngineType()%> >
		        	<br>

        			<label for="">Photo</label> 
	           	 	<input
	                    	type="file"
	                    	name="photo"
	                    	value=<%= vehicle.getPhoto()%> >
        			<br>

        			<input type="submit" value="Update" id="signup-btn">
    			</form>

    			<form action="/Cab_It/dashboard">
        			<input type="submit" value="Dashboard" id="signup-btn-1">
    			</form>
    		</div>
    	</div>
	</body>
</html>
