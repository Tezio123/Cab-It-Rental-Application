<%@ page import="com.cabit.Cab_It.model.Customer" %>
<%@ page import="com.cabit.Cab_It.helper.AccessPrivilegeHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Customer Delete</title>
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
	        AccessPrivilegeHelper accessPrivilegeHelper = new AccessPrivilegeHelper();
	        Customer loggedCustomer = (Customer) session.getAttribute("logged-customer");
	
	        if (!accessPrivilegeHelper.checkAccessPrivilegesForCustomer(loggedCustomer, "delete")) {
	            response.sendRedirect("/Cab_It/error/no-access.jsp");
	            return;
	        }
	    %>
	
	    <%
	        session.setAttribute("delete-customer", loggedCustomer);
	    %>
	
	    <div class="container">
        	<div class="main-container">
            	<form action="/Cab_It/delete/customer" method="POST">
	                <%
	                    out.println("<div class='head-te'><h2>Are you sure do you really want to delete your acoount <br>".concat(loggedCustomer.getId()).concat("?</h2></div><br><br>"));
	                %>
	                <label class="check" >Yes<input type="checkbox" checked="checked" value="yes" name="delete-confirmation"> <span class="checkmark"></span></label><br>
	                <label class="check" >No<input type="checkbox" value="no" name="delete-confirmation"><span class="checkmark"></span></label><br>
	                <input type="submit" value="delete" id = "signup-btn" >
            	</form>

	            <form action="/Cab_It/dashboard">
	                <input type="submit" value="Dashboard" id = "signup-btn-1">
	            </form>
        	</div>
   		</div>
	</body>
</html>
