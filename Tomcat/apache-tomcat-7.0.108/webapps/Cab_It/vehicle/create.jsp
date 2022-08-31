<%@ page import="java.util.List" %>
<%@ page import="com.cabit.Cab_It.helper.AccessPrivilegeHelper" %>
<%@ page import="com.cabit.Cab_It.model.Admin" %>
<%@ page import="java.util.Enumeration"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Vehicle Register</title>
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
	
	        if (!accessPrivilegeHelper.checkAccessPrivilegesForVehicle(loggedAdmin, "create")) {
	            response.sendRedirect("/Cab_It/error/no-access.jsp");
	            return;
	        }
	    %>
	
	    <div class="container">
	    	<div class="head-te">
		         <h2>Vehicle Sign Up</h2>
		    </div>
	        <div class="main-container">
	            <form action="/Cab_It/validation/vehicle/create-validation.jsp" method="POST">
	                <label for=""> Model</label>
	                     <input
	                             type="text"
	                             name="model"
	                             value=<%= session.getAttribute("prev-model") == null ? "" : session.getAttribute("prev-model") %>>
	                             <font color="red">
	                                 <%= session.getAttribute("model-invalidation") == null ? "" : session.getAttribute("model-invalidation") %>
	                             </font>
	                 <br>
	         
	                 <label for=""> Brand</label>
	                     <input
	                             type="text"
	                             name="brand"
	                             value=<%= session.getAttribute("prev-brand") == null ? "" : session.getAttribute("prev-brand") %>>
	                             <font color="red">
	                                 <%= session.getAttribute("brand-invalidation") == null ? "" : session.getAttribute("brand-invalidation") %>
	                             </font>
	                 <br>
	         
	                 <label for="">Fuel type</label>
	                     <input
	                             type="text"
	                             name="fuel-type"
	                             value=<%= session.getAttribute("prev-fuel-type") == null ? "" : session.getAttribute("prev-fuel-type") %>>
	                 <br>
	         
	                 <label for="">Engine type </label>
	                     <input
	                             type="text"
	                             name="engine-type"
	                             value=<%= session.getAttribute("prev-engine-type") == null ? "" : session.getAttribute("prev-engine-type") %>>
	                 <br>
	         
	                 <label for="">Plate number</label>
	                     <input
	                             type="text"
	                             name="plate-number"
	                             value=<%= session.getAttribute("prev-plate-number") == null ? "" : session.getAttribute("prev-plate-number") %>>
	                             <font color="red">
	                                 <%= session.getAttribute("plate-num-invalidation") == null ? "" : session.getAttribute("plate-num-invalidation") %>
	                             </font>
	                 <br>
	         
	                 <label for="">Top speed</label>
	                     <input
	                             type="text"
	                             name="top-speed"
	                             value=<%= session.getAttribute("prev-top-speed") == null ? "" : session.getAttribute("prev-top-speed") %>>
	                             <font color="red">
	                                 <%= session.getAttribute("top-speed-invalidation") == null ? "" : session.getAttribute("top-speed-invalidation") %>
	                             </font>
	                 <br>
	         
	                 <label for="">Photo </label>
	                     <input
	                             type="file"
	                             name="photo"
	                             value=<%= session.getAttribute("prev-photo") == null ? "" : session.getAttribute("prev-photo") %>>
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
