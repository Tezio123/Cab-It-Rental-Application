<%@ page import="com.cabit.Cab_It.helper.AccessPrivilegeHelper" %>
<%@ page import="com.cabit.Cab_It.model.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
	    <title>Rent Your Cab</title>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link rel="stylesheet" href="CSS/style6.css">
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
	        if(session.getAttribute("order-register-status") == null)
	        {
	            response.sendRedirect("/Cab_It/register/order");
	            return;
	        }
	        AccessPrivilegeHelper accessPrivilegeHelper = new AccessPrivilegeHelper();
	        Customer loggedCustomer = (Customer) session.getAttribute("logged-customer");
	
	        if (!accessPrivilegeHelper.checkAccessPrivilegesForOrder(loggedCustomer, "create")) {
	            response.sendRedirect("/Cab_It/error/no-access.jsp");
	            return;
	        }
	    %>
	    
    	<div class="container">
	         <div class="head-te">
	         		<h2>Order Status</h2>
	         </div>
	
		    <%
		        if(session.getAttribute("order-register-status").toString().equals("success"))
		        {
		            out.println("<font color='blue'>");
		            out.println("<center><h3>Your cab ordered successfully! It may be on the way :) </h3></center>");
		            out.println("</font>");
		        }
		        else
		        {
		            out.println("<font color='red'>");
		            out.println("<center><h3 >Sorry! Something went wrong :(</h3></center>");
		            out.println("</font>");
		        }
		    %>
		
		    <form action="/Cab_It/dashboard">
		        <input type="submit" value="Dashboard" id="signup-btn-1">
		    </form>
		</div>
	</body>
</html>
