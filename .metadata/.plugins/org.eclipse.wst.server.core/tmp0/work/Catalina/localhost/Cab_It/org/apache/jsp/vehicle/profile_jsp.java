/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.108
 * Generated at: 2021-05-20 22:58:59 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.vehicle;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.cabit.Cab_It.model.Employee;
import com.cabit.Cab_It.model.Vehicle;
import java.time.Period;
import java.util.Base64;
import com.cabit.Cab_It.helper.AccessPrivilegeHelper;

public final class profile_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("\t<head>\r\n");
      out.write("\t    \r\n");
      out.write("\t    <meta charset=\"UTF-8\">\r\n");
      out.write("\t    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("\t    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("\t\t<title>Vehicle Profile</title>\r\n");
      out.write("\t    <link rel=\"stylesheet\" href=\"CSS/style6.css\">\r\n");
      out.write("\t    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\">\r\n");
      out.write("\t    <link href=\"https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap\" rel=\"stylesheet\">\r\n");
      out.write("\t    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\">    \r\n");
      out.write("\t    <link href=\"https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap\" rel=\"stylesheet\">\r\n");
      out.write("\t</head>\r\n");
      out.write("\r\n");
      out.write("\t<body>\r\n");
      out.write("\t    ");

	        if(session.getAttribute("logged-employee") == null)
	        {
	            response.sendRedirect("/Cab_It/login");
	            return;
	        }
	        AccessPrivilegeHelper accessPrivilegeHelper = new AccessPrivilegeHelper();
	        Employee loggedEmployee = (Employee) session.getAttribute("logged-employee");
	
	        if (!accessPrivilegeHelper.checkAccessPrivilegesForVehicle(loggedEmployee, "read")) {
	            response.sendRedirect("/Cab_It/error/no-access.jsp");
	            return;
	        }
	    
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t    ");

	        Employee employee = (Employee) request.getSession().getAttribute("logged-employee");
	        Vehicle vehicle = employee.getVehicle();
	
	        vehicle.setDerivedAttributes();
	    
      out.write("\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<div class=\"head-te\">\r\n");
      out.write("\t\t\t<h2>Vehicle Profile Here</h2>\r\n");
      out.write("\t\t\r\n");
      out.write("\t    ");

	        Object vehicleUpdateStatus = session.getAttribute("vehicle-optional-update-status");
	
	        if(vehicleUpdateStatus != null)
	        {
	            if(vehicleUpdateStatus.toString().equals("success"))
	            {
	                out.println("<font color='blue'>");
	                	out.println("<br><h4>Vehicle Updated Successfully!</h4>");
	                out.println("</font>");
	            }
	            session.removeAttribute("vehicle-optional-update-status");
	        }
	    
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t    ");

	        String titleTemplate = "<h3>%s</h3>";
	        String imageTemplate = "<img src='%s'><br>";
	        String fieldTemplate = "<p><b> %s </b> : %s </p>";
	        String hyperlinkTemplate = "<a href='%s'>%s</a>";
	
	        String id = vehicle.getId();
	        String model = vehicle.getModel();
	        String brand = vehicle.getBrand();
	        String engineType = vehicle.getEngineType();
	        String fuelType = vehicle.getFuelType();
	        String topSpeed = String.valueOf(vehicle.getTopSpeed());
	        String plateNumber = vehicle.getPlateNumber();
	        Period servicePeriod = vehicle.getServiceTime();
	        String serviceTime = "(New Vehicle)";
	        String photoUrl = "data:image/jpg;base64," + Base64.getEncoder().encodeToString(vehicle.getPhoto());
	
	        if(servicePeriod.getYears() > 0)
	            serviceTime = String.valueOf(servicePeriod.getYears()).concat(" years");
	        else if(servicePeriod.getMonths() > 0)
	            serviceTime = String.valueOf(servicePeriod.getMonths()).concat(" months");
	        else if(servicePeriod.getDays() > 0)
	            serviceTime = String.valueOf(servicePeriod.getDays()).concat(" days");
	
	        out.println("<div class='row'>");
	        	out.println("<div class='column'>");
	        		out.println("<div class='card'>");
	        			out.println("<div class='image-btn'>");
							out.println(String.format(imageTemplate, photoUrl));
	        			out.println("</div>");
	        			out.println("<div class='container'>");
					        out.println(String.format(titleTemplate, id));;
					        out.println(String.format(fieldTemplate, "Model", model));
					        out.println(String.format(fieldTemplate, "Brand", brand));
					        out.println(String.format(fieldTemplate, "Engine", engineType));
					        out.println(String.format(fieldTemplate, "Fuel", fuelType));
					        out.println(String.format(fieldTemplate, "Top Speed", topSpeed));
					        out.println(String.format(fieldTemplate, "Plate Number", plateNumber));
					        out.println(String.format(fieldTemplate, "Service Time", serviceTime));
					        out.println(String.format(hyperlinkTemplate, "/Cab_It/optional-update/vehicle", "<h4 id='signup-btn-3'>Update</h4>"));
				        out.println("</div>");
			        out.println("</div>");
		        out.println("</div>");
	        out.println("</div>");
		
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t    <form action=\"/Cab_It/dashboard\">\r\n");
      out.write("\t        <input type=\"submit\" value=\"Dashboard\" id=\"signup-btn-1\">\r\n");
      out.write("\t    </form>\r\n");
      out.write("\t\r\n");
      out.write("\t</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
