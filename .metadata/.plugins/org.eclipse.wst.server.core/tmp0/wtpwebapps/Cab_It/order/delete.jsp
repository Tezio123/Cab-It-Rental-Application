<%@ page import="com.cabit.Cab_It.model.Vehicle" %>
<%@ page import="com.cabit.Cab_It.model.Order" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="com.cabit.Cab_It.helper.DateTimeHelper" %>
<%@ page import="com.cabit.Cab_It.service.CustomerService" %>
<%@ page import="com.cabit.Cab_It.service.VehicleService" %>
<%@ page import="com.cabit.Cab_It.service.LocationService" %>
<%@ page import="com.cabit.Cab_It.helper.AccessPrivilegeHelper" %>
<%@ page import="com.cabit.Cab_It.model.Admin" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
	<head>
	    <title>Delete Order</title>
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link rel="stylesheet" href="CSS/style5.css">
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

        if (!accessPrivilegeHelper.checkAccessPrivilegesForOrder(loggedAdmin, "delete")) {
            response.sendRedirect("/Cab_It/error/no-access.jsp");
            return;
        }
    %>

    <%
        DateTimeHelper dateTimeHelper = new DateTimeHelper();

        String customerId = request.getParameter("del-customer-id");
        String vehicleId = request.getParameter("del-vehicle-id");
        String fromLocationId = request.getParameter("del-from-location-id");
        String toLocationId = request.getParameter("del-to-location-id");
        String requestedDateTimeStr = request.getParameter("del-req-date-time");

        requestedDateTimeStr = requestedDateTimeStr.substring(0, 10)
                .concat(" ")
                .concat(requestedDateTimeStr.substring(11, 19));

        LocalDateTime requestedDateTime = dateTimeHelper.getFormattedDateTime(requestedDateTimeStr);
    %>

    <div class="container">
    	<div class="main-container">
			    <form action="/Cab_It/delete/order" method="POST">
			        <%
			            Order order = new Order(
			                    CustomerService.customerMap.get(customerId),
			                    VehicleService.vehicleMap.get(vehicleId),
			                    LocationService.locationMap.get(fromLocationId),
			                    LocationService.locationMap.get(toLocationId),
			                    requestedDateTime
			            );
			
			            session.setAttribute("delete-order", order);
			        %>
                     <div class='head-te'><h2>Are you sure do you really want to delete this Order?</h2></div>
			        
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
