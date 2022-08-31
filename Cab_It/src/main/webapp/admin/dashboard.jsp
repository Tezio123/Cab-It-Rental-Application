<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Panel</title>
    <link rel="stylesheet" href="CSS/style.css">
    <link rel="stylesheet" href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.gstatic.com">    
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
</head>
<body>

    <%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

        if(session.getAttribute("logged-admin") == null)
        {
            response.sendRedirect("/Cab_It/login");
            return;
        }
        else if(session.getAttribute("logged-employee") != null)
        {
            session.removeAttribute("logged-employee");
        }
        session.setAttribute("logout-entity", "admin");
    %>
	
	<div class="container">
    
        <div class="first-row">
            <div class="main-text">
                <h1>Admin Dashboard</h1>
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
                  <a  href="/Cab_It/register/employee">
                    <i class="las la-user-circle"></i>
                  </a>
                  <div class="desc"><a href="/Cab_It/register/employee">Click here to employee signup</a></div>
                </div>
              </div>
    
              <div class="responsive">
                <div class="gallery">
                  <a  href="/Cab_It/fetch/employee">
                    <i class="las la-eye"></i>
                  </a>
                  <div class="desc"><a href="/Cab_It/fetch/employee">Click here to view employee </a></div>
                </div>
              </div>
    
              <div class="responsive">
                <div class="gallery">
                  <a  href="/Cab_It/register/vehicle">
                    <i class="las la-taxi"></i>
                  </a>
                  <div class="desc"><a href="/Cab_It/register/vehicle">Click here to register a vehicle </a></div>
                </div>
              </div>
        </div>
    
        <div class="first-row">
            
            <div class="responsive">
                <div class="gallery">
                  <a  href="/Cab_It/fetch/vehicle">
                    <i class="las la-car"></i>
                  </a>
                  <div class="desc"><a href="/Cab_It/fetch/vehicle">Click here to view vehicle</a></div>
                </div>
              </div>
    
              <div class="responsive">
                <div class="gallery">
                  <a  href="/Cab_It/register/location">
                    <i class="las la-map-marked"></i>
                  </a>
                  <div class="desc"><a href="/Cab_It/register/location">Click here to register a location </a></div>
                </div>
              </div>
    
              <div class="responsive">
                <div class="gallery">
                  <a  href="/Cab_It/fetch/location">
                    <i class="las la-map-marked-alt"></i>
                  </a>
                  <div class="desc"><a href="/Cab_It/fetch/location">Click here to view location </a></div>
                </div>
              </div>
    
              <div class="responsive">
                <div class="gallery">
                  <a  href="/Cab_It/register/advertisement">
                    <i class="las la-ad"></i>
                  </a>
                  <div class="desc"><a href="/Cab_It/register/advertisement">Click here to  register advertisement  </a></div>
                </div>
              </div>
        </div>
    
        <div class="first-row">
            
            <div class="responsive">
                <div class="gallery">
                  <a  href="/Cab_It/fetch/advertisement">
                    <i class="las la-ad"></i>
                  </a>
                  <div class="desc"><a href="/Cab_It/fetch/advertisement">Click here to view advertisement </a></div>
                </div>
              </div>
              
              <div class="last-btn">
                <div class="responsive">
                    <div class="gallery">
                      <a  href="/Cab_It/fetch/order">
                        <i class="las la-clipboard-list"></i>
                      </a>
                      <div class="desc"><a href="/Cab_It/fetch/order">Click here to  view orders </a></div>
                    </div>
                  </div>
              </div>
        </div>
    </div>   
</body>
</html>
