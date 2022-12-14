/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.108
 * Generated at: 2021-05-20 20:59:34 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.order;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.time.LocalTime;
import java.util.Map;
import com.cabit.Cab_It.helper.AccessPrivilegeHelper;
import com.cabit.Cab_It.model.Customer;

public final class create_002dstep_002d3_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\r\n");
      out.write("\t<head>\r\n");
      out.write("\t    <title>Rent Your Cab</title>\r\n");
      out.write("\t    <meta charset=\"UTF-8\">\r\n");
      out.write("\t    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("\t    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("\t    <link rel=\"stylesheet\" href=\"CSS/style5.css\">\r\n");
      out.write("\t    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\">\r\n");
      out.write("\t    <link href=\"https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap\" rel=\"stylesheet\">\r\n");
      out.write("\t    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\">    \r\n");
      out.write("\t    <link href=\"https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap\" rel=\"stylesheet\">\r\n");
      out.write("\t</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("    ");

        if(session.getAttribute("logged-customer") == null)
        {
            response.sendRedirect("/Cab_It/login");
            return;
        }
        if(session.getAttribute("start-location-id") == null || session.getAttribute("end-location-id") == null)
        {
            response.sendRedirect("/Cab_It/register/order");
            return;
        }
        AccessPrivilegeHelper accessPrivilegeHelper = new AccessPrivilegeHelper();
        Customer loggedCustomer = (Customer) session.getAttribute("logged-customer");

        if (!accessPrivilegeHelper.checkAccessPrivilegesForOrder(loggedCustomer, "create")) {
            response.sendRedirect("/Cab_It/error/no-access.jsp");
            return;
        }

        Map<String, String[]> parameterMap = request.getParameterMap();

        for(String key : parameterMap.keySet())
        {
            if(key.startsWith("VEH-"))
            {
                session.setAttribute("vehicle-id", key.substring(4));
                break;
            }
        }
    
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <div  class=\"container\">\r\n");
      out.write("        <div class=\"head-te\">\r\n");
      out.write("\t    \t\t<h2>Order Confirmation</h2>\r\n");
      out.write("\t    </div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<div class=\"main-container\">\r\n");
      out.write("\t    \t<font color=\"red\">\r\n");
      out.write("\t\t        <h3>\r\n");
      out.write("\t\t            Note that after 5 seconds you have no chance to make any changes to your order<br>\r\n");
      out.write("\t\t            and your order will be accepted. If you want to do any change to your order, <br>\r\n");
      out.write("\t\t            click below links before 5 seconds!\r\n");
      out.write("\t\t        </h3>\r\n");
      out.write("\t    \t</font>\r\n");
      out.write("\r\n");
      out.write("\t\t    <button onclick=\"doUpdate()\" id=\"signup-btn-1\">Update</button>\r\n");
      out.write("\t\t    <button onclick=\"doDelete()\"  id=\"signup-btn\">Delete</button>\r\n");
      out.write("    \t</div>\r\n");
      out.write("   </div>\r\n");
      out.write("\r\n");
      out.write("    <script>\r\n");
      out.write("        timeout = setTimeout(function () {\r\n");
      out.write("            let request = new XMLHttpRequest();\r\n");
      out.write("\r\n");
      out.write("            request.open(\"POST\", \"/Cab_It/register/order\", false);\r\n");
      out.write("            request.send();\r\n");
      out.write("\r\n");
      out.write("            document.location=\"/Cab_It/order/create-step-4.jsp\";\r\n");
      out.write("\r\n");
      out.write("        }, 5000);\r\n");
      out.write("\r\n");
      out.write("        function stopTimeout() {\r\n");
      out.write("            clearTimeout(timeout);\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        function doUpdate()\r\n");
      out.write("        {\r\n");
      out.write("            stopTimeout();\r\n");
      out.write("\r\n");
      out.write("            document.location=\"/Cab_It/order/create-step-1.jsp\";\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        function doDelete()\r\n");
      out.write("        {\r\n");
      out.write("            stopTimeout();\r\n");
      out.write("\r\n");
      out.write("            document.location=\"/Cab_It/customer/dashboard.jsp\";\r\n");
      out.write("        }\r\n");
      out.write("    </script>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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
