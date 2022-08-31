<%@ page import="com.cabit.Cab_It.helper.AccessPrivilegeHelper" %>
<%@ page import="com.cabit.Cab_It.model.Admin" %>
<%@ page import="java.util.Enumeration"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
	    <title>Register Location</title>
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
        if(session.getAttribute("logged-admin") == null)
        {
            response.sendRedirect("/Cab_It/login");
            return;
        }
        AccessPrivilegeHelper accessPrivilegeHelper = new AccessPrivilegeHelper();
        Admin loggedAdmin = (Admin) session.getAttribute("logged-admin");

        if (!accessPrivilegeHelper.checkAccessPrivilegesForLocation(loggedAdmin, "create")) {
            response.sendRedirect("/Cab_It/error/no-access.jsp");
            return;
        }
    %>
        <div class="container">
    		<div class="head-te">
            	<h2>Register Location</h2>
        	</div>
        	<div class="main-container">
	        	<form action="/Cab_It/validation/location/create-validation.jsp">
	
	           	<label for="District ">District</label>
	            <select name="district">
	                <option value="Colombo">Colombo</option>
	                <option value="Kurunegala">Kurunegala</option>
	                <option value="Kandy">Kandy</option>
	                <option value="Anuradhapura">Anuradhapura</option>
	                <option value="Galle">Galle</option>
	                <option value="Gampaha">Gampaha</option>
	                <option value="Kegalle">Kegalle</option>
	                <option value="Nuwara Eliya">Nuwara Eliya</option>
	            </select>
	            <br>
				
				<label for="zone">Zone</label> 
	            <input
	                    type="text"
	                    name="zone"
	                    placeholder = "Enter Your Zone"
	                    value=<%= session.getAttribute("prev-zone") == null ? "" : session.getAttribute("prev-zone") %>>
	                    <font color="red">
	                        <%= session.getAttribute("zone-invalidation") == null ? "" : session.getAttribute("zone-invalidation") %>
	                    </font>
	            <br>
	            <input type="submit" value="Register" id="signup-btn">
	        	</form>

	        	<form action="/Cab_It/dashboard">
	            	<input type="submit" value="Dashboard" id="signup-btn-1">
	        	</form>
        	</div>
      </div>
        
       <%
    
	   Enumeration<String> sessionAttributes =  session.getAttributeNames();
	
	    while (sessionAttributes.hasMoreElements())
	    {
	        String element = sessionAttributes.nextElement();
	
	        if(element.endsWith("invalidation") || element.startsWith("prev"))
	            session.removeAttribute(element);
	    }
   	%>
    </body>
</html>
