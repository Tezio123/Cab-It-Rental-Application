<%@ page import="com.cabit.Cab_It.model.Advertisement" %>
<%@ page import="com.cabit.Cab_It.service.AdvertisementService" %>
<%@ page import="com.cabit.Cab_It.model.Admin" %>
<%@ page import="com.cabit.Cab_It.helper.AccessPrivilegeHelper" %>
<%@ page import="java.util.Enumeration"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Update Advertisement</title>
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
	        if(session.getAttribute("update-advertisement") == null)
	        {
	            response.sendRedirect("/Cab_It/admin/dashboard");
	            return;
	        }
	        Admin loggedAdmin = (Admin) session.getAttribute("logged-admin");
	        AccessPrivilegeHelper accessPrivilegeHelper = new AccessPrivilegeHelper();
	
	        if(!accessPrivilegeHelper.checkAccessPrivilegesForAdvertisement(loggedAdmin, "update"))
	        {
	            response.sendRedirect("/Cab_It/error/no-access.jsp");
	            return;
	        }
	    %>
	
	    <%
	        Advertisement advertisement = (Advertisement) request.getSession().getAttribute("update-advertisement");
	
	        session.setAttribute("advertisement-id", advertisement.getId());
	    %>
		
		<div class="container">
			<div class="head-te">
				<h2>Update Advertisement</h2>
			</div>
	    	<div class="main-container">
				    <form action="/Cab_It/validation/advertisement/update-validation.jsp" method="POST">
				       <label for=""> Content </label>
				        <textarea name="content">
				            <%= session.getAttribute("prev-content") == null ? advertisement.getContent() : session.getAttribute("prev-content") %>
				        </textarea>
				        <br><br>
				        <font  color="red">
				            <%= session.getAttribute("content-invalidation") == null ? "" : session.getAttribute("content-invalidation") %>
				        </font>
				        <br>
				        <label for="">Photo </label>
				        <input
				                type="file"
				                name="photo"
				                value=<%= session.getAttribute("prev-photo") %>>
				        <br>
				        <input type="submit" value="Update"  id="signup-btn">
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
