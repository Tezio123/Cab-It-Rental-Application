<%@ page import="com.cabit.Cab_It.model.Employee" %>
<%@ page import="com.cabit.Cab_It.helper.AccessPrivilegeHelper" %>
<%@ page import="java.util.Enumeration"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Employee Update</title>
	    <link rel="stylesheet" href="CSS/style8.css">
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
	
	        if (!accessPrivilegeHelper.checkAccessPrivilegesForEmployee(loggedEmployee, "update")) {
	            response.sendRedirect("/Cab_It/error/no-access.jsp");
	            return;
	        }
	    %>
	
	    <%
	        Employee employee = (Employee) request.getSession().getAttribute("logged-employee");
	
	        session.setAttribute("employee-id", employee.getId());
	    %>
	
	    <div class="container">
	    	<div class="head-te">
	        	<h2>Employee Update</h2>
        	</div>
	    	<div class="main-container">
	    		<form action="/Cab_It/validation/employee/optional-update-validation.jsp" method="POST">
	
			        <label for="">Enter username</label>
			        <input
			                type="text"
			                name="username"
			                value=<%= session.getAttribute("prev-uname") == null ? employee.getUsername() : session.getAttribute("prev-uname")%>>
			        <font color="red">
			            <%= session.getAttribute("uname-invalidation") == null ? "" : session.getAttribute("uname-invalidation") %>
			        </font>
			        <br>
			
			        <label for="">Enter password</label> 
			        <input
			                type="password"
			                name="password"
			                value=<%= session.getAttribute("prev-passwd") == null ? employee.getPassword() : session.getAttribute("prev-passwd")%>>
			        <font color="red">
			            <%= session.getAttribute("passwd-invalidation") == null ? "" : session.getAttribute("passwd-invalidation") %>
			        </font>
			        <br>
			
			        <label for="">Enter address line 1 </label>
			        <input
			                type="text"
			                name="address-line-1"
			                value=<%= session.getAttribute("prev-address-1") == null ? employee.getAddressLine1() : session.getAttribute("prev-address-1") %>>
			        <font color="red">
			            <%= session.getAttribute("address-1-invalidation") == null ? "" : session.getAttribute("address-1-invalidation") %>
			        </font>
			        <br>
			
			        <label for="">Enter address line 2</label>
			        <input
			                type="text"
			                name="address-line-2"
			                value=<%= session.getAttribute("prev-address-2") == null ? employee.getAddressLine2() : session.getAttribute("prev-address-2") %>>
			        <font color="red">
			            <%= session.getAttribute("address-2-invalidation") == null ? "" : session.getAttribute("address-2-invalidation") %>
			        </font>
			        <br>
			
			        <label for="">Enter address line 3</label>
			        <input
			                type="text"
			                name="address-line-3"
			                value=<%= session.getAttribute("prev-address-3") == null ? employee.getAddressLine3() : session.getAttribute("prev-address-3") %>>
			        <br>
			
			        <label for="">Enter city</label>
			        <input
			                type="text"
			                name="city"
			                value=<%= session.getAttribute("prev-city") == null ? employee.getCity() : session.getAttribute("prev-city") %>>
			        <font color="red">
			            <%= session.getAttribute("city-invalidation") == null ? "" : session.getAttribute("city-invalidation") %>
			        </font>
			        <br>
			
			        <label for="">Enter phone</label>
			        <input
			                type="text"
			                name="phone"
			                value=<%= session.getAttribute("prev-phone") == null ? employee.getPhone() : session.getAttribute("prev-phone") %>>
			        <font color="red">
			            <%= session.getAttribute("phone-invalidation") == null ? "" : session.getAttribute("phone-invalidation") %>
			        </font>
			        <br>
			
			        <input type="submit" value="Update" id="signup-btn">
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
