<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Enumeration"%>
<html>
	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Customer Console</title>
	    <link rel="stylesheet" href="CSS/style2.css">
	    <link rel="stylesheet" href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
	    <link rel="preconnect" href="https://fonts.gstatic.com">
	    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
	    <link rel="preconnect" href="https://fonts.gstatic.com">    
	    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
	</head>

	<body>
	    <%
	        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	
	        if(session.getAttribute("logged-customer") == null)
	        {
	            response.sendRedirect("/Cab_It/login");
	            return;
	        }
	        session.setAttribute("logout-entity", "customer");
	
	    %>
	
	    <div class="container">
    
	        <div class="first-row">
	            <div class="main-text">
	                <h1>Customer Dashboard</h1>
	            </div>
	        
	            <div class="responsive">
	                <div class="gallery">
	                  <a  href="/Cab_It/logout">
	                    <i class="las la-unlock"></i>
	                  </a>
	                  <div class="desc"><a href="/Cab_It/logout">Click here to logout</a></div>
	                </div>
	            </div>
	    
	              <div class="responsive">
	                <div class="gallery">
	                  <a href="/Cab_It/profile/customer">
	                    <i class="las la-user-circle"></i>
	                  </a>
	                  <div class="desc"><a href="/Cab_It/profile/customer">Click here to view profile</a></div>
	                </div>
	              </div>
	    
	              <div class="responsive">
	                <div class="gallery">
	                  <a  href="/Cab_It/update/customer">
	                    <i class="las la-user-edit"></i>
	                  </a>
	                  <div class="desc"><a href="/Cab_It/update/customer">Click here to update account </a></div>
	                </div>
	              </div>
	    
	              <div class="responsive">
	                <div class="gallery">
	                  <a  href="/Cab_It/delete/customer">
	                    <i class="las la-user-slash"></i>
	                  </a>
	                  <div class="desc"><a href="/Cab_It/delete/customer">Click here to  delete account </a></div>
	                </div>
	              </div>
	        </div>
    
    
        	<div class="first-row">
            
	            <div class="responsive">
	                <div class="gallery">
	                  <a  href="/Cab_It/register/order">
	                    <i class="las la-list"></i>
	                  </a>
	                  <div class="desc"><a href="/Cab_It/register/order">Click here to register an order</a></div>
	                </div>
	              </div>
	              
	              <div class="last-2">
	              	<div class="responsive">
	                <div class="gallery">
	                  <a  href="/Cab_It/fetch/order">
	                    <i class="las la-clipboard-list"></i>
	                  </a>
	                  <div class="desc"><a href="/Cab_It/fetch/order"> Click here to view orders </a></div>
	                </div>
	              	</div>
	              </div>
	              
	             
	             <div class="last-3">
	             	<div class="responsive">
	                <div class="gallery">
	                  <a  href="/Cab_It/fetch/advertisement">
	                    <i class="las la-ad"></i>
	                  </a>
	                  <div class="desc"><a href="/Cab_It/fetch/advertisement">Click here to view advertisements </a></div>
	                </div>
	              </div>
	            </div>
        	</div>
    	</div>
		
		<%
		   Enumeration<String> sessionAttributes =  session.getAttributeNames();
		
		    while (sessionAttributes.hasMoreElements())
		    {
		        String element = sessionAttributes.nextElement();
		
		        if(element.endsWith("invalidation"))
		            session.removeAttribute(element);
		    }
	    %>
	</body>
</html>
