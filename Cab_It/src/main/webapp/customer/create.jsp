<%@ page import="com.cabit.Cab_It.helper.AccessPrivilegeHelper" %>
<%@ page import="com.cabit.Cab_It.model.Customer" %>
<%@ page import="java.util.Enumeration"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
	<head>
	    <title>Customer Signup</title>
	    <link rel="stylesheet" href="CSS/style1.css">
	    <link rel="preconnect" href="https://fonts.gstatic.com">
	    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght	@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
	    <link rel="preconnect" href="https://fonts.gstatic.com">    
	    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
	</head>
	
	<body>
	
	    <%
	        AccessPrivilegeHelper accessPrivilegeHelper = new AccessPrivilegeHelper();
	
	        if (!accessPrivilegeHelper.checkAccessPrivilegesForCustomer(new Customer(), "create")) {
	            response.sendRedirect("/Cab_It/error/no-access.jsp");
	            return;
	        }
	    %>
	    
	    <div class="container">
        	<div class="head-te">
                <h2>Customer Sign Up</h2>
            </div>
            
	        <div class="main-container">
	           <form action="/Cab_It/validation/customer/create-validation.jsp" method="POST">
		           <label for=""> Enter NIC </label>
		                <input
		                        type="text"
		                        name="nic"
		                        value=<%= session.getAttribute("prev-nic") == null ? "" : session.getAttribute("prev-nic") %>>
		                        <font color="red">
		                            <%= session.getAttribute("nic-invalidation") == null ? "" : session.getAttribute("nic-invalidation") %>
		                        </font>
		            <br>
		
		            <label for="">  Enter First name </label>
		                <input
		                        type="text"
		                        name="first-name"
		                        value=<%= session.getAttribute("prev-fname") == null ? "" : session.getAttribute("prev-fname") %>>
		                        <font color="red">
		                            <%= session.getAttribute("fname-invalidation") == null ? "" : session.getAttribute("fname-invalidation") %>
		                        </font>
		            <br>
		    
		            <label for="">  Enter Mid name </label>
		                <input
		                        type="text"
		                        name="mid-name"
		                        value=<%= session.getAttribute("prev-mname") == null ? "" : session.getAttribute("prev-mname") %>>
		            <br>
		    
		            <label for="">  Enter Last name </label>
		                <input
		                        type="text"
		                        name="last-name"
		                        value=<%= session.getAttribute("prev-lname") == null ? "" : session.getAttribute("prev-lname")%>>
		                        <font color="red" >
		                            <%= session.getAttribute("lname-invalidation") == null ? "" : session.getAttribute("lname-invalidation") %>
		                        </font>
		            <br>
		    
		            <label for="">  Enter User name </label>
		                <input
		                        type="text"
		                        name="username"
		                        value=<%= session.getAttribute("prev-uname") == null ? "" : session.getAttribute("prev-uname")%>>
		                        <font color="red">
		                            <%= session.getAttribute("uname-invalidation") == null ? "" : session.getAttribute("uname-invalidation") %>
		                        </font>
		            <br>
		    
		            <label for="">  Enter Password </label>
		                <input
		                        type="password"
		                        name="password"
		                        value=<%= session.getAttribute("prev-passwd") == null ? "" : session.getAttribute("prev-passwd")%>>
		                        <font color="red">
		                            <%= session.getAttribute("passwd-invalidation") == null ? "" : session.getAttribute("passwd-invalidation") %>
		                        </font>
		            <br>
		                        
		            <label for="">  Re-enter Password </label>
		                <input
		                        type="password"
		                        name="confirm-password"
		                        value=<%= session.getAttribute("prev-confirm-passwd") == null ? "" : session.getAttribute("prev-confirm-passwd") %>>
		                        <font color="red">
		                            <%= session.getAttribute("confirm-passwd-invalidation") == null ? "" : session.getAttribute("confirm-passwd-invalidation") %>
		                        </font>
		            <br>
		            
		            <label for="">  Enter Address line 1  </label>
		           
		                <input
		                        type="text"
		                        name="address-line-1"
		                        value=<%= session.getAttribute("prev-address-1") == null ? "" : session.getAttribute("prev-address-1") %>>
		                        <font color="red">
		                            <%= session.getAttribute("address-1-invalidation") == null ? "" : session.getAttribute("address-1-invalidation") %>
		                        </font>
		            <br>
		    
		            <label for="">  Enter Address line 2  </label>
		                <input
		                        type="text"
		                        name="address-line-2"
		                        value=<%= session.getAttribute("prev-address-2") == null ? "" : session.getAttribute("prev-address-2") %>>
		                        <font color="red">
		                            <%= session.getAttribute("address-2-invalidation") == null ? "" : session.getAttribute("address-2-invalidation") %>
		                        </font>
		            <br>
		    
		            <label for="">  Enter Address line 3  </label>
		                <input
		                        type="text"
		                        name="address-line-3"
		                        value=<%= session.getAttribute("prev-address-3") == null ? "" : session.getAttribute("prev-address-3") %>>
		            <br>
		    
		            <label for="">  Enter City </label>
		                <input
		                        type="text"
		                        name="city"
		                        value=<%= session.getAttribute("prev-city") == null ? "" : session.getAttribute("prev-city") %>>
		                        <font color="red">
		                            <%= session.getAttribute("city-invalidation") == null ? "" : session.getAttribute("city-invalidation") %>
		                        </font>
		            <br>
		    
		           <label for="">  Enter phone </label>
		                <input
		                        type="text"
		                        name="phone"
		                        value=<%= session.getAttribute("prev-phone") == null ? "" : session.getAttribute("prev-phone") %>>
		                        <font color="red">
		                            <%= session.getAttribute("phone-invalidation") == null ? "" : session.getAttribute("phone-invalidation") %>
		                        </font>
		            <br>
		            
		            <label for=""> Choose Profile Image </label>
		            <input type="file" name="photo" class="custom-file-input"><br>
		            <input type="submit" value="Signup" id="signup-btn">
		            
		            <div class="an-btn">
		                <a href="/Cab_It/login"><h4>Already have an account?</h4></a>
		            </div>
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
