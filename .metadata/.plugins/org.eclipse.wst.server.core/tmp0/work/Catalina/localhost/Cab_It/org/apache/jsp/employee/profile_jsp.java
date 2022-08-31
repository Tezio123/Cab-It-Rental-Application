/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.108
 * Generated at: 2021-05-20 23:02:43 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.employee;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.cabit.Cab_It.model.Employee;
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
      out.write("<html>\r\n");
      out.write("\t<head>\r\n");
      out.write("\t    <meta charset=\"UTF-8\">\r\n");
      out.write("\t    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("\t    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("\t\t<title>Employee Profile</title>\r\n");
      out.write("\t    <link rel=\"stylesheet\" href=\"CSS/style6.css\">\r\n");
      out.write("\t    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\">\r\n");
      out.write("\t    <link href=\"https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap\" rel=\"stylesheet\">\r\n");
      out.write("\t    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\">    \r\n");
      out.write("\t    <link href=\"https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap\" rel=\"stylesheet\">\r\n");
      out.write("\t</head>\r\n");
      out.write("\r\n");
      out.write("    <body>\r\n");
      out.write("        ");

            if(session.getAttribute("logged-employee") == null)
            {
                response.sendRedirect("/Cab_It/login");
                return;
            }
            AccessPrivilegeHelper accessPrivilegeHelper = new AccessPrivilegeHelper();
            Employee loggedEmployee = (Employee) session.getAttribute("logged-employee");

            if (!accessPrivilegeHelper.checkAccessPrivilegesForEmployee(loggedEmployee, "read")) {
                response.sendRedirect("/Cab_It/error/no-access.jsp");
                return;
            }

            loggedEmployee.setDerivedAttributes();
        
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <div class=\"head-te\">\r\n");
      out.write("\t\t\t<h2>Your Profile Here</h2>\r\n");
      out.write("\r\n");
      out.write("        ");

            Object employeeOptionalUpdateStatus = session.getAttribute("employee-optional-update-status");

            if(employeeOptionalUpdateStatus != null)
            {
                if(employeeOptionalUpdateStatus.toString().equals("success"))
                {
                    out.println("<font color='blue'>");
                    out.println("<br><h4>Account Updated Successfully!</h4>");
                    out.println("</font>");
                }
                session.removeAttribute("employee-optional-update-status");
            }
        
      out.write("\r\n");
      out.write("        \r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        ");

            String titleTemplate = "<h3>%s</h3>";
            String imageTemplate = "<img src='%s'><br>";
            String fieldTemplate = "<p><b>%s</b>  : %s </p>";
            String hyperlinkTemplate = "<a href='%s'>%s</a>";

            String id = loggedEmployee.getId();
            String fullName = loggedEmployee.getFirstName()
                .concat(" ")
                .concat(loggedEmployee.getMidName())
                .concat(" ")
                .concat(loggedEmployee.getLastName());
            String username = loggedEmployee.getUsername();
            String password = loggedEmployee.getPassword();
            String nic = loggedEmployee.getNic();
            String address = loggedEmployee.getAddressLine1()
                .concat(", ")
                .concat(loggedEmployee.getAddressLine2())
                .concat(", ")
                .concat(loggedEmployee.getAddressLine3());
            String city = loggedEmployee.getCity();
            String phone = loggedEmployee.getPhone();
            String bloodGroup = loggedEmployee.getBloodGroup();
            String license = loggedEmployee.getLicense();
            String locationDistrict = loggedEmployee.getLocation().getDistrict();
            String vehiclePlateNumber = loggedEmployee.getVehicle().getPlateNumber();
            String ageInYears = String.valueOf(loggedEmployee.getAgeInYears());
            String gender = loggedEmployee.getGender().toString();
            String dob = loggedEmployee.getDob().toString();
            String serviceTime = "(New employee)";
            Period period = loggedEmployee.getServiceTime();

            if(period.getYears() > 0)
                serviceTime = String.valueOf(period.getYears()).concat(" years");
            else if(period.getMonths() > 0)
                serviceTime = String.valueOf(period.getMonths()).concat(" months");
            else if(period.getDays() > 0)
                serviceTime = String.valueOf(period.getDays()).concat(" days");

            String photoUrl = "data:image/jpg;base64," + Base64.getEncoder().encodeToString(loggedEmployee.getPhoto());

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
				            out.println(String.format(fieldTemplate, "Blood group", bloodGroup));
				            out.println(String.format(fieldTemplate, "License", license));
				            out.println(String.format(fieldTemplate, "Service Time", serviceTime));
				            out.println(String.format(fieldTemplate, "Service location", locationDistrict));
				            out.println(String.format(fieldTemplate, "Vehicle Plate Number", String.format(hyperlinkTemplate, "/Cab_It/profile/vehicle", vehiclePlateNumber)));
				            out.println(String.format(hyperlinkTemplate, "/Cab_It/update-optional/employee", "<h4 id='signup-btn-3'>Update</h4"));
            			out.println("</div>");
            		out.println("</div>");
            	out.println("</div>");
            out.println("</div>");
        
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <form action=\"/Cab_It/dashboard\">\r\n");
      out.write("            <input type=\"submit\" value=\"Dashboard\" id=\"signup-btn-1\">\r\n");
      out.write("        </form>\r\n");
      out.write("    </body>\r\n");
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
