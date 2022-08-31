<%@ page import="com.cabit.Cab_It.model.Customer" %>
<%@ page import="java.util.Base64" %>
<%@ page import="com.cabit.Cab_It.helper.AccessPrivilegeHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Customer Profile</title>
	    <link rel="stylesheet" href="CSS/style3.css">
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
	
	        if (!accessPrivilegeHelper.checkAccessPrivilegesForCustomer(loggedCustomer, "read")) {
	            response.sendRedirect("/Cab_It/error/no-access.jsp");
	            return;
	        }
	
	    %>
	    <div class="head-te">
	    	<h2>Your Profile Here</h2>
	    	
	    <%
	        if(session.getAttribute("customer-delete-status") != null)
	        {
	            if(session.getAttribute("customer-delete-status").toString().equals("invalid"))
	            {
	                out.println("<font color='red'>");
	                out.println("<br><h4>Your account is unable to delete. Try again later!</h4>");
	                out.println("</font>");
	            }
	            session.removeAttribute("customer-delete-status");
	        }
	    %>
	    
	    <%
	        Object customerUpdateStatus = session.getAttribute("customer-update-status");
	
	        if(customerUpdateStatus != null)
	        {
	            if(customerUpdateStatus.toString().equals("success"))
	            {
	                out.println("<font color='blue'>");
	                out.println("<br><h4>Account Updated Successfully!</h4>");
	                out.println("</font>");
	            }
	            session.removeAttribute("customer-update-status");
	        }
	    %>
	    
	    </div>
	
	    <%
	        String titleTemplate = "<h3>%s</h3>";
	        String imageTemplate = "<img src='%s'><br>";
	        String fieldTemplate = "<p> <b>%s</b>  : %s </p>";
	        String hyperlinkTemplate = "<a href='%s'>%s</a>";
	
	        String id = loggedCustomer.getId();
	        String fullName = loggedCustomer.getFirstName()
	                .concat(" ")
	                .concat(loggedCustomer.getMidName())
	                .concat(" ")
	                .concat(loggedCustomer.getLastName());
	        String username = loggedCustomer.getUsername();
	        String password = loggedCustomer.getPassword();
	        String nic = loggedCustomer.getNic();
	        String address = loggedCustomer.getAddressLine1()
	                .concat(", ")
	                .concat(loggedCustomer.getAddressLine2())
	                .concat(", ")
	                .concat(loggedCustomer.getAddressLine3());
	        String city = loggedCustomer.getCity();
	        String phone = loggedCustomer.getPhone();
	        String ageInYears = String.valueOf(loggedCustomer.getAgeInYears());
	        String gender = loggedCustomer.getGender().toString();
	        String dob = loggedCustomer.getDob().toString();
	        String photoUrl = "data:image/jpg;base64," + Base64.getEncoder().encodeToString(loggedCustomer.getPhoto());
			
	        out.println("<div class='row'>");
	        	out.println("<div class='column'>");
	        		out.println("<div class='card'>");
	        			out.println("<div class='image-btn'>");
	        				out.println(String.format(imageTemplate, photoUrl));
	        			out.println("</div>");
	        			out.println("<div class='container'>");
	        				out.println(String.format(titleTemplate, id));
					        out.println(String.format(fieldTemplate, "National Identity Card", nic));
					        out.println(String.format(fieldTemplate, "Username", username));
					        out.println(String.format(fieldTemplate, "Password", password));
					        out.println(String.format(fieldTemplate, "Name", fullName));
					        out.println(String.format(fieldTemplate, "Age", ageInYears));
					        out.println(String.format(fieldTemplate, "Gender", gender));
					        out.println(String.format(fieldTemplate, "Birthday", dob));
					        out.println(String.format(fieldTemplate, "Address", address));
					        out.println(String.format(fieldTemplate, "City", city));
					        out.println(String.format(fieldTemplate, "Phone", phone));
					        out.println(String.format(hyperlinkTemplate, "/Cab_It/update/customer", "<h4 id='signup-btn-2'>Update</h4>"));
					        out.println(String.format(hyperlinkTemplate, "/Cab_It/delete/customer", "<h4 id='signup-btn-3'>Delete</h4>"));
	        			out.println("</div>");
	        		out.println("</div>");
	        	out.println("</div>");
	        out.println("</div>");
	    %>
	
	    <form action="/Cab_It/dashboard">
        	<input type="submit" value="Dashboard" id="signup-btn-1">
    	</form>
	</body>
</html>