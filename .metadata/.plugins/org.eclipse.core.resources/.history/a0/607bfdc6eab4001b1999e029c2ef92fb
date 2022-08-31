<%@ page import="com.cabit.Cab_It.helper.AccessPrivilegeHelper" %>
<%@ page import="java.util.*" %>
<%@ page import="com.cabit.Cab_It.model.*" %>
<%@ page import="java.util.Enumeration"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Employee Sign up</title>
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
	        if(session.getAttribute("locations") == null || session.getAttribute("vehicles") == null)
	        {
	            response.sendRedirect("/Cab_It/employee");
	            return;
	        }
	        AccessPrivilegeHelper accessPrivilegeHelper = new AccessPrivilegeHelper();
	        Admin loggedAdmin = (Admin) session.getAttribute("logged-admin");
	
	        if (!accessPrivilegeHelper.checkAccessPrivilegesForEmployee(loggedAdmin, "create")) {
	            response.sendRedirect("/Cab_It/error/no-access.jsp");
	            return;
	        }
	    %>
	
	    <div class="container">
	        <div class="head-te">
	            <h2>Employee Sign Up</h2>
	        </div>
	        <div class="main-container">
	            <form action="/Cab_It/validation/employee/create-validation.jsp" method="POST">
	        
	                <label for="">Enter NIC</label>
	                    <input
	                            type="text"
	                            name="nic"
	                            value=<%= session.getAttribute("prev-nic") == null ? "" : session.getAttribute("prev-nic") %>>
	                            <font color="red">
	                                <%= session.getAttribute("nic-invalidation") == null ? "" : session.getAttribute("nic-invalidation") %>
	                            </font>
	                <br>
	                
	                <label for="">Enter first name</label>
	                    <input
	                            type="text"
	                            name="first-name"
	                            value=<%= session.getAttribute("prev-fname") == null ? "" : session.getAttribute("prev-fname") %>>
	                            <font color="red">
	                                <%= session.getAttribute("fname-invalidation") == null ? "" : session.getAttribute("fname-invalidation") %>
	                            </font>
	                <br>
	        
	                <label for="">Enter mid name</label>
	                    <input
	                            type="text"
	                            name="mid-name"
	                            value=<%= session.getAttribute("prev-mname") == null ? "" : session.getAttribute("prev-mname") %>>
	                <br>
	        
	                <label for="">Enter last name</label>
	                    <input
	                            type="text"
	                            name="last-name"
	                            value=<%= session.getAttribute("prev-lname") == null ? "" : session.getAttribute("prev-lname")%>>
	                            <font color="red" >
	                                <%= session.getAttribute("lname-invalidation") == null ? "" : session.getAttribute("lname-invalidation") %>
	                            </font>
	                <br>
	        
	                <label for="">Enter username</label>
	                    <input
	                            type="text"
	                            name="username"
	                            value=<%= session.getAttribute("prev-uname") == null ? "" : session.getAttribute("prev-uname")%>>
	                            <font color="red">
	                                <%= session.getAttribute("uname-invalidation") == null ? "" : session.getAttribute("uname-invalidation") %>
	                            </font>
	                <br>
	        
	                <label for="">Enter password</label>
	                    <input
	                            type="password"
	                            name="password"
	                            value=<%= session.getAttribute("prev-passwd") == null ? "" : session.getAttribute("prev-passwd")%>>
	                            <font color="red">
	                                 <%= session.getAttribute("passwd-invalidation") == null ? "" : session.getAttribute("passwd-invalidation") %>
	                            </font>
	                <br>
	        
	                <label for="">Confirm password </label>
	                    <input
	                            type="password"
	                            name="confirm-password"
	                            value=<%= session.getAttribute("prev-confirm-passwd") == null ? "" : session.getAttribute("prev-confirm-passwd") %>>
	                            <font color="red">
	                                <%= session.getAttribute("confirm-passwd-invalidation") == null ? "" : session.getAttribute("confirm-passwd-invalidation") %>
	                            </font>
	                <br>
	        
	                <label for="">Enter address line 1 </label>
	                    <input
	                            type="text"
	                            name="address-line-1"
	                            value=<%= session.getAttribute("prev-address-1") == null ? "" : session.getAttribute("prev-address-1") %>>
	                            <font color="red">
	                                <%= session.getAttribute("address-1-invalidation") == null ? "" : session.getAttribute("address-1-invalidation") %>
	                            </font>
	                <br>
	        
	                <label for=""> Enter address line 2</label>
	                    <input
	                            type="text"
	                            name="address-line-2"
	                            value=<%= session.getAttribute("prev-address-2") == null ? "" : session.getAttribute("prev-address-2") %>>
	                             <font color="red">
	                                 <%= session.getAttribute("address-2-invalidation") == null ? "" : session.getAttribute("address-2-invalidation") %>
	                             </font>
	                <br>
	        
	                <label for="">Enter address line 3 </label>
	                    <input
	                            type="text"
	                            name="address-line-3"
	                            value=<%= session.getAttribute("prev-address-3") == null ? "" : session.getAttribute("prev-address-3") %>>
	                <br>
	        
	                <label for="">Enter city </label>
	                    <input
	                            type="text"
	                            name="city"
	                            value=<%= session.getAttribute("prev-city") == null ? "" : session.getAttribute("prev-city") %>>
	                            <font color="red">
	                                <%= session.getAttribute("city-invalidation") == null ? "" : session.getAttribute("city-invalidation") %>
	                            </font>
	                <br>
	        
	                <label for="">Enter phone</label>
	                    <input
	                            type="text"
	                            name="phone"
	                            value=<%= session.getAttribute("prev-phone") == null ? "" : session.getAttribute("prev-phone") %>>
	                            <font color="red">
	                                <%= session.getAttribute("phone-invalidation") == null ? "" : session.getAttribute("phone-invalidation") %>
	                            </font>
	                <br>
	        
	                <label for="">Enter blood group</label>
	                    <input
	                            type="text"
	                            name="blood-group"
	                            value=<%= session.getAttribute("prev-blood-group") == null ? "" : session.getAttribute("prev-blood-group") %>>
	                            <font color="red">
	                                <%= session.getAttribute("blood-group-invalidation") == null ? "" : session.getAttribute("blood-group-invalidation") %>
	                            </font>
	                <br>
	        
	                <label for="">Enter license </label>
	                    <input
	                            type="text"
	                            name="license"
	                            value=<%= session.getAttribute("prev-license") == null ? "" : session.getAttribute("prev-license") %>>
	                            <font color="red">
	                                <%= session.getAttribute("license-invalidation") == null ? "" : session.getAttribute("license-invalidation") %>
	                            </font>
	                <br>
	              <label for="">Select Location </label>
	                <div class="custom-select" style="width:100%;">
	                    <select name="location">
	                        <%
	                            String contentTemplate = "<option value='%s'> %s</option>";
	                            Map<String, Location> locationMap = (Map) session.getAttribute("locations");
	                            Set<String> visited = new HashSet<String>();
	        
	        
	                            for(Location location : locationMap.values()) {
	        
	                                String district = location.getDistrict();
	        
	                                if(!visited.contains(district)) {
	                                    String itemContent = String.format(contentTemplate,
	                                            location.getId(),
	                                            district
	                                    );
	        
	                                    visited.add(district);
	        
	                                    out.println(itemContent);
	                                }
	                            }
	                        %>
	                    </select><br>
	                </div>
	                    
	        		<label for="">SelectVehicle </label>
	                <div class="custom-select" style="width:100%;">
	                    <select name="vehicle">
	                        <%
	                            contentTemplate = "<option value='%s'> %s</option>";
	                            Map<String, Vehicle> vehicleMap = (Map) session.getAttribute("vehicles");
	                            Map<String, Employee> employeeMap = (Map) session.getAttribute("employees");
	                            Collection<Vehicle> vehicles = vehicleMap.values();
	                            Collection<Employee> employees = employeeMap.values();
	                            List<Vehicle> availableVehicles = new ArrayList<Vehicle>();
	            
	                            for(Vehicle vehicle : vehicles)
	                            {
	                                boolean found = false;
	            
	                                for (Employee employee : employees)
	                                {
	                                    if(employee.getVehicle().getId().equals(vehicle.getId()))
	                                    {
	                                        found = true;
	                                        break;
	                                    }
	                                }
	                                if(!found)
	                                    availableVehicles.add(vehicle);
	                            }
	            
	                            for(Vehicle vehicle : availableVehicles) {
	            
	                                String plateNumber = vehicle.getPlateNumber();
	                                String itemContent = String.format(contentTemplate,
	                                        vehicle.getId(),
	                                        plateNumber
	                                );
	                                out.println(itemContent);
	                            }
	                        %>
	                    </select><br>
	                </div>
	        
	                <label for="">Photo</label>
	                <input type="file" name="photo" class="custom-file-input"><br>
	                <input type="submit" value="Signup" id="signup-btn">
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
