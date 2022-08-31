/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.108
 * Generated at: 2021-05-21 14:50:27 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.customer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.cabit.Cab_It.helper.AccessPrivilegeHelper;
import com.cabit.Cab_It.model.Customer;
import java.util.Enumeration;

public final class create_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t    <title>Customer Signup</title>\r\n");
      out.write("\t    <link rel=\"stylesheet\" href=\"CSS/style1.css\">\r\n");
      out.write("\t    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\">\r\n");
      out.write("\t    <link href=\"https://fonts.googleapis.com/css2?family=Poppins:ital,wght\t@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap\" rel=\"stylesheet\">\r\n");
      out.write("\t    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\">    \r\n");
      out.write("\t    <link href=\"https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap\" rel=\"stylesheet\">\r\n");
      out.write("\t</head>\r\n");
      out.write("\t\r\n");
      out.write("\t<body>\r\n");
      out.write("\t\r\n");
      out.write("\t    ");

	        AccessPrivilegeHelper accessPrivilegeHelper = new AccessPrivilegeHelper();
	
	        if (!accessPrivilegeHelper.checkAccessPrivilegesForCustomer(new Customer(), "create")) {
	            response.sendRedirect("/Cab_It/error/no-access.jsp");
	            return;
	        }
	    
      out.write("\r\n");
      out.write("\t    \r\n");
      out.write("\t    <div class=\"container\">\r\n");
      out.write("        \t<div class=\"head-te\">\r\n");
      out.write("                <h2>Customer Sign Up</h2>\r\n");
      out.write("            </div>\r\n");
      out.write("            \r\n");
      out.write("\t        <div class=\"main-container\">\r\n");
      out.write("\t           <form action=\"/Cab_It/validation/customer/create-validation.jsp\" method=\"POST\">\r\n");
      out.write("\t\t           <label for=\"\"> Enter NIC </label>\r\n");
      out.write("\t\t                <input\r\n");
      out.write("\t\t                        type=\"text\"\r\n");
      out.write("\t\t                        name=\"nic\"\r\n");
      out.write("\t\t                        value=");
      out.print( session.getAttribute("prev-nic") == null ? "" : session.getAttribute("prev-nic") );
      out.write(">\r\n");
      out.write("\t\t                        <font color=\"red\">\r\n");
      out.write("\t\t                            ");
      out.print( session.getAttribute("nic-invalidation") == null ? "" : session.getAttribute("nic-invalidation") );
      out.write("\r\n");
      out.write("\t\t                        </font>\r\n");
      out.write("\t\t            <br>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t            <label for=\"\">  Enter First name </label>\r\n");
      out.write("\t\t                <input\r\n");
      out.write("\t\t                        type=\"text\"\r\n");
      out.write("\t\t                        name=\"first-name\"\r\n");
      out.write("\t\t                        value=");
      out.print( session.getAttribute("prev-fname") == null ? "" : session.getAttribute("prev-fname") );
      out.write(">\r\n");
      out.write("\t\t                        <font color=\"red\">\r\n");
      out.write("\t\t                            ");
      out.print( session.getAttribute("fname-invalidation") == null ? "" : session.getAttribute("fname-invalidation") );
      out.write("\r\n");
      out.write("\t\t                        </font>\r\n");
      out.write("\t\t            <br>\r\n");
      out.write("\t\t    \r\n");
      out.write("\t\t            <label for=\"\">  Enter Mid name </label>\r\n");
      out.write("\t\t                <input\r\n");
      out.write("\t\t                        type=\"text\"\r\n");
      out.write("\t\t                        name=\"mid-name\"\r\n");
      out.write("\t\t                        value=");
      out.print( session.getAttribute("prev-mname") == null ? "" : session.getAttribute("prev-mname") );
      out.write(">\r\n");
      out.write("\t\t            <br>\r\n");
      out.write("\t\t    \r\n");
      out.write("\t\t            <label for=\"\">  Enter Last name </label>\r\n");
      out.write("\t\t                <input\r\n");
      out.write("\t\t                        type=\"text\"\r\n");
      out.write("\t\t                        name=\"last-name\"\r\n");
      out.write("\t\t                        value=");
      out.print( session.getAttribute("prev-lname") == null ? "" : session.getAttribute("prev-lname"));
      out.write(">\r\n");
      out.write("\t\t                        <font color=\"red\" >\r\n");
      out.write("\t\t                            ");
      out.print( session.getAttribute("lname-invalidation") == null ? "" : session.getAttribute("lname-invalidation") );
      out.write("\r\n");
      out.write("\t\t                        </font>\r\n");
      out.write("\t\t            <br>\r\n");
      out.write("\t\t    \r\n");
      out.write("\t\t            <label for=\"\">  Enter User name </label>\r\n");
      out.write("\t\t                <input\r\n");
      out.write("\t\t                        type=\"text\"\r\n");
      out.write("\t\t                        name=\"username\"\r\n");
      out.write("\t\t                        value=");
      out.print( session.getAttribute("prev-uname") == null ? "" : session.getAttribute("prev-uname"));
      out.write(">\r\n");
      out.write("\t\t                        <font color=\"red\">\r\n");
      out.write("\t\t                            ");
      out.print( session.getAttribute("uname-invalidation") == null ? "" : session.getAttribute("uname-invalidation") );
      out.write("\r\n");
      out.write("\t\t                        </font>\r\n");
      out.write("\t\t            <br>\r\n");
      out.write("\t\t    \r\n");
      out.write("\t\t            <label for=\"\">  Enter Password </label>\r\n");
      out.write("\t\t                <input\r\n");
      out.write("\t\t                        type=\"password\"\r\n");
      out.write("\t\t                        name=\"password\"\r\n");
      out.write("\t\t                        value=");
      out.print( session.getAttribute("prev-passwd") == null ? "" : session.getAttribute("prev-passwd"));
      out.write(">\r\n");
      out.write("\t\t                        <font color=\"red\">\r\n");
      out.write("\t\t                            ");
      out.print( session.getAttribute("passwd-invalidation") == null ? "" : session.getAttribute("passwd-invalidation") );
      out.write("\r\n");
      out.write("\t\t                        </font>\r\n");
      out.write("\t\t            <br>\r\n");
      out.write("\t\t                        \r\n");
      out.write("\t\t            <label for=\"\">  Re-enter Password </label>\r\n");
      out.write("\t\t                <input\r\n");
      out.write("\t\t                        type=\"password\"\r\n");
      out.write("\t\t                        name=\"confirm-password\"\r\n");
      out.write("\t\t                        value=");
      out.print( session.getAttribute("prev-confirm-passwd") == null ? "" : session.getAttribute("prev-confirm-passwd") );
      out.write(">\r\n");
      out.write("\t\t                        <font color=\"red\">\r\n");
      out.write("\t\t                            ");
      out.print( session.getAttribute("confirm-passwd-invalidation") == null ? "" : session.getAttribute("confirm-passwd-invalidation") );
      out.write("\r\n");
      out.write("\t\t                        </font>\r\n");
      out.write("\t\t            <br>\r\n");
      out.write("\t\t            \r\n");
      out.write("\t\t            <label for=\"\">  Enter Address line 1  </label>\r\n");
      out.write("\t\t           \r\n");
      out.write("\t\t                <input\r\n");
      out.write("\t\t                        type=\"text\"\r\n");
      out.write("\t\t                        name=\"address-line-1\"\r\n");
      out.write("\t\t                        value=");
      out.print( session.getAttribute("prev-address-1") == null ? "" : session.getAttribute("prev-address-1") );
      out.write(">\r\n");
      out.write("\t\t                        <font color=\"red\">\r\n");
      out.write("\t\t                            ");
      out.print( session.getAttribute("address-1-invalidation") == null ? "" : session.getAttribute("address-1-invalidation") );
      out.write("\r\n");
      out.write("\t\t                        </font>\r\n");
      out.write("\t\t            <br>\r\n");
      out.write("\t\t    \r\n");
      out.write("\t\t            <label for=\"\">  Enter Address line 2  </label>\r\n");
      out.write("\t\t                <input\r\n");
      out.write("\t\t                        type=\"text\"\r\n");
      out.write("\t\t                        name=\"address-line-2\"\r\n");
      out.write("\t\t                        value=");
      out.print( session.getAttribute("prev-address-2") == null ? "" : session.getAttribute("prev-address-2") );
      out.write(">\r\n");
      out.write("\t\t                        <font color=\"red\">\r\n");
      out.write("\t\t                            ");
      out.print( session.getAttribute("address-2-invalidation") == null ? "" : session.getAttribute("address-2-invalidation") );
      out.write("\r\n");
      out.write("\t\t                        </font>\r\n");
      out.write("\t\t            <br>\r\n");
      out.write("\t\t    \r\n");
      out.write("\t\t            <label for=\"\">  Enter Address line 3  </label>\r\n");
      out.write("\t\t                <input\r\n");
      out.write("\t\t                        type=\"text\"\r\n");
      out.write("\t\t                        name=\"address-line-3\"\r\n");
      out.write("\t\t                        value=");
      out.print( session.getAttribute("prev-address-3") == null ? "" : session.getAttribute("prev-address-3") );
      out.write(">\r\n");
      out.write("\t\t            <br>\r\n");
      out.write("\t\t    \r\n");
      out.write("\t\t            <label for=\"\">  Enter City </label>\r\n");
      out.write("\t\t                <input\r\n");
      out.write("\t\t                        type=\"text\"\r\n");
      out.write("\t\t                        name=\"city\"\r\n");
      out.write("\t\t                        value=");
      out.print( session.getAttribute("prev-city") == null ? "" : session.getAttribute("prev-city") );
      out.write(">\r\n");
      out.write("\t\t                        <font color=\"red\">\r\n");
      out.write("\t\t                            ");
      out.print( session.getAttribute("city-invalidation") == null ? "" : session.getAttribute("city-invalidation") );
      out.write("\r\n");
      out.write("\t\t                        </font>\r\n");
      out.write("\t\t            <br>\r\n");
      out.write("\t\t    \r\n");
      out.write("\t\t           <label for=\"\">  Enter phone </label>\r\n");
      out.write("\t\t                <input\r\n");
      out.write("\t\t                        type=\"text\"\r\n");
      out.write("\t\t                        name=\"phone\"\r\n");
      out.write("\t\t                        value=");
      out.print( session.getAttribute("prev-phone") == null ? "" : session.getAttribute("prev-phone") );
      out.write(">\r\n");
      out.write("\t\t                        <font color=\"red\">\r\n");
      out.write("\t\t                            ");
      out.print( session.getAttribute("phone-invalidation") == null ? "" : session.getAttribute("phone-invalidation") );
      out.write("\r\n");
      out.write("\t\t                        </font>\r\n");
      out.write("\t\t            <br>\r\n");
      out.write("\t\t            \r\n");
      out.write("\t\t            <label for=\"\"> Choose Profile Image </label>\r\n");
      out.write("\t\t            <input type=\"file\" name=\"photo\" class=\"custom-file-input\"><br>\r\n");
      out.write("\t\t            <input type=\"submit\" value=\"Signup\" id=\"signup-btn\">\r\n");
      out.write("\t\t            \r\n");
      out.write("\t\t            <div class=\"an-btn\">\r\n");
      out.write("\t\t                <a href=\"/Cab_It/login\"><h4>Already have an account?</h4></a>\r\n");
      out.write("\t\t            </div>\r\n");
      out.write("\t        \t</form>\r\n");
      out.write("\t          \t\r\n");
      out.write("\t          \r\n");
      out.write("\t          \r\n");
      out.write("\t    \t\t\r\n");
      out.write("\t        </div>\r\n");
      out.write("\t    </div>\r\n");
      out.write("\t    \r\n");
      out.write("\t    ");

		    
		   Enumeration<String> sessionAttributes =  session.getAttributeNames();
		
		    while (sessionAttributes.hasMoreElements())
		    {
		        String element = sessionAttributes.nextElement();
		
		        if(element.endsWith("invalidation") || element.startsWith("prev"))
		            session.removeAttribute(element);
		    }
	    
      out.write("\r\n");
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
