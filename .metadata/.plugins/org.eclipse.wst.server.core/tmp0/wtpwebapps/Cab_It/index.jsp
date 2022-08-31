<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Login</title>
	    <link rel="stylesheet" href="style.css">
	    <link rel="preconnect" href="https://fonts.gstatic.com">
	    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
	    <link rel="preconnect" href="https://fonts.gstatic.com">    
	    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
	</head>
	<body>
			<div class="container">
	    		<div class="head-te">
    				<h2>Login</h2>
	        <%
	            if(session.getAttribute("customer-delete-status") != null)
	            {
	                if(session.getAttribute("customer-delete-status").toString().equals("success"))
	                    out.println("<font color='blue'> <br><h4>Your account deleted successfully!</h4></font>");
	
	                session.removeAttribute("customer-delete-status");
	            }
	        %>
	       		</div>
	      
	      <div class="main-container">
	        	<form action="/Cab_It/login" method="POST">
		            
		            <label for="uname"><b>Username</b></label>
		            <input type="text" name="username" required="true"><br>
		            
		            <label for="psw"><b>Password</b></label>
		            <input type="password" name="password" required="true"><br>
		
			        <input type="submit" value="Login" id="login-btn">
		      
		            <div class="an-btn">
		                <a href="/Cab_It/register/customer"><h4>Don't have an account?</h4></a>
		            </div>
	             
	             </form>
	             
	            <%=
	       			session.getAttribute("login-status") == "invalid" ? "<h2 id='invalid-btn'>Invalid login!</h2>" : ""
	    		%>
	    	</div>
	    </div>
	</body>
</html>
