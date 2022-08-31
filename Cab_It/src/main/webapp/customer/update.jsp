<%@ page import="com.cabit.Cab_It.model.Customer" %>
<%@ page import="com.cabit.Cab_It.helper.AccessPrivilegeHelper" %>
<%@ page import="java.util.Enumeration"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Customer Update</title>
	    <link rel="stylesheet" href="CSS/style5.css">
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
	        Customer loggedCustomer = (Customer) session.getAttribute("logged-customer");
	        loggedCustomer.setDerivedAttributes();
	
	        AccessPrivilegeHelper accessPrivilegeHelper = new AccessPrivilegeHelper();
	
	        if (!accessPrivilegeHelper.checkAccessPrivilegesForCustomer(loggedCustomer, "update")) {
	            response.sendRedirect("/Cab_It/error/no-access.jsp");
	            return;
	        }
	    %>
	
	    <%
	        session.setAttribute("customer-id", loggedCustomer.getId());
	    %>
	
	    <div class="container">
	    	<div class="head-te">
	            <h2>Update Your Details</h2>
	        </div>
        
	        <div class="main-container">
		    	<form action="/Cab_It/validation/customer/update-validation.jsp" method="POST">
		
		        <label for="" >Enter NIC</label>
		            <input
		                    type="text"
		                    name="nic"
		                    value=<%= session.getAttribute("prev-nic") == null ? loggedCustomer.getNic() : session.getAttribute("prev-nic") %>>
		                    <font color="red">
		                        <%= session.getAttribute("nic-invalidation") == null ? "" : session.getAttribute("nic-invalidation") %>
		                    </font>
		        <br>
		
		        <label for="">Enter first name</label>
		            <input
		                    type="text"
		                    name="first-name"
		                    value=<%= session.getAttribute("prev-fname") == null ? loggedCustomer.getFirstName() : session.getAttribute("prev-fname") %>>
		                    <font color="red">
		                        <%= session.getAttribute("fname-invalidation") == null ? "" : session.getAttribute("fname-invalidation") %>
		                    </font>
		        <br>
		
		        <label for="">Enter mid name</label>
		        <input
		                type="text"
		                name="mid-name"
		                value=<%= session.getAttribute("prev-mname") == null ? loggedCustomer.getMidName() : session.getAttribute("prev-mname") %>>
		        <br>
		
		        <label for="">Enter last name</label>
		        <input
		                type="text"
		                name="last-name"
		                value=<%= session.getAttribute("prev-lname") == null ? loggedCustomer.getLastName() : session.getAttribute("prev-lname")%>>
		                <font color="red" >
		                    <%= session.getAttribute("lname-invalidation") == null ? "" : session.getAttribute("lname-invalidation") %>
		                </font>
		        <br>
		
		        <label for="">Enter username</label>
		        <input
		                type="text"
		                name="username"
		                value=<%= session.getAttribute("prev-uname") == null ? loggedCustomer.getUsername() : session.getAttribute("prev-uname")%>>
		                <font color="red">
		                    <%= session.getAttribute("uname-invalidation") == null ? "" : session.getAttribute("uname-invalidation") %>
		                </font>
		        <br>
		
		        <label for="">Enter password</label>
		        <input
		                type="password"
		                name="password"
		                value=<%= session.getAttribute("prev-passwd") == null ? loggedCustomer.getPassword() : session.getAttribute("prev-passwd")%>>
		                <font color="red">
		                    <%= session.getAttribute("passwd-invalidation") == null ? "" : session.getAttribute("passwd-invalidation") %>
		                </font>
		        <br>
		
		        <label for="">Enter address line 1</label>
		        <input
		                type="text"
		                name="address-line-1"
		                value=<%= session.getAttribute("prev-address-1") == null ? loggedCustomer.getAddressLine1() : session.getAttribute("prev-address-1") %>>
		                <font color="red">
		                    <%= session.getAttribute("address-1-invalidation") == null ? "" : session.getAttribute("address-1-invalidation") %>
		                </font>
		        <br>
		
		       <label for="">Enter address line 2</label>
		        <input
		                type="text"
		                name="address-line-2"
		                value=<%= session.getAttribute("prev-address-2") == null ? loggedCustomer.getAddressLine2() : session.getAttribute("prev-address-2") %>>
		                <font color="red">
		                    <%= session.getAttribute("address-2-invalidation") == null ? "" : session.getAttribute("address-2-invalidation") %>
		                </font>
		        <br>
		
		        <label for="">Enter address line 3</label>
		        <input
		                type="text"
		                name="address-line-3"
		                value=<%= session.getAttribute("prev-address-3") == null ? loggedCustomer.getAddressLine3() : session.getAttribute("prev-address-3") %>>
		        <br>
		
		       <label for=""> Enter city </label>
		        <input
		                type="text"
		                name="city"
		                value=<%= session.getAttribute("prev-city") == null ? loggedCustomer.getCity() : session.getAttribute("prev-city") %>>
		                <font color="red">
		                    <%= session.getAttribute("city-invalidation") == null ? "" : session.getAttribute("city-invalidation") %>
		                </font>
		        <br>
		
		        <label for="">Enter phone</label>
		        <input
		                type="text"
		                name="phone"
		                value=<%= session.getAttribute("prev-phone") == null ? loggedCustomer.getPhone() : session.getAttribute("prev-phone") %>>
		                <font color="red">
		                    <%= session.getAttribute("phone-invalidation") == null ? "" : session.getAttribute("phone-invalidation") %>
		                </font>
		        <br>
		
		        <label for="">Photo </label>
		        <input type="file" name="photo"><br>
		        <input type="submit" value="Update" id="signup-btn">
		    </form>
		
		    <form action="/Cab_It/dashboard">
		        <input type="submit" value="Dashboard"  id="signup-btn-1">
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
