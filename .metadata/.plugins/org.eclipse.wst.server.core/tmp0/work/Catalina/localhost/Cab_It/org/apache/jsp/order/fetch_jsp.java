/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.108
 * Generated at: 2021-05-20 20:51:45 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.order;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.cabit.Cab_It.model.Order;
import java.util.List;
import java.time.LocalDateTime;
import com.cabit.Cab_It.helper.AccessPrivilegeHelper;
import com.cabit.Cab_It.model.Customer;
import com.cabit.Cab_It.model.Admin;
import com.cabit.Cab_It.helper.DateTimeHelper;

public final class fetch_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("\r\n");
      out.write("\t<head>\r\n");
      out.write("\t    <meta charset=\"UTF-8\">\r\n");
      out.write("\t    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("\t    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("\t    <title>Orders</title>\r\n");
      out.write("\t    <link rel=\"stylesheet\" href=\"CSS/style3.css\">\r\n");
      out.write("\t    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\">\r\n");
      out.write("\t    <link href=\"https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap\" rel=\"stylesheet\">\r\n");
      out.write("\t    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\">    \r\n");
      out.write("\t    <link href=\"https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap\" rel=\"stylesheet\">\r\n");
      out.write("\t</head>\r\n");
      out.write("\r\n");
      out.write("\t<body>\r\n");
      out.write("\t    ");

	        if(session.getAttribute("logged-customer") == null && session.getAttribute("logged-admin") == null)
	        {
	            response.sendRedirect("/Cab_It/login");
	            return;
	        }
	        AccessPrivilegeHelper accessPrivilegeHelper = new AccessPrivilegeHelper();
	        Customer loggedCustomer = (Customer) session.getAttribute("logged-customer");
	        Admin loggedAdmin = (Admin) session.getAttribute("logged-admin");
	
	        if (!accessPrivilegeHelper.checkAccessPrivilegesForOrder(loggedCustomer, "read") &&
	            !accessPrivilegeHelper.checkAccessPrivilegesForOrder(loggedAdmin, "read"))
	        {
	            response.sendRedirect("/Cab_It/error/no-access.jsp");
	            return;
	        }
	    
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t    <div class=\"head-te\">\r\n");
      out.write("\t \t\t<h2>Orders here..</h2>\r\n");
      out.write("\t \t\t<br><br>\r\n");
      out.write("\t \t\t<h4>Fetching criteria</h4>\r\n");
      out.write("\t\t\t    <a href=\"/Cab_It/fetch/order?asc\">Requested date time in ASC</a><br>\r\n");
      out.write("\t\t\t    <a href=\"/Cab_It/fetch/order\">Requested date time in DESC</a>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t    \r\n");
      out.write("\t    ");

	        if(session.getAttribute("logged-admin") != null)
	        {
	            if(session.getAttribute("order-delete-status") != null)
	            {
	                Object orderDeleteStatus = session.getAttribute("order-delete-status");
	
	                if(orderDeleteStatus.toString().equals("success"))
	                {
	                	out.println("<div class='head-te'>");
		                    out.println("<font color='blue'>");
		                    	out.println("<h3>Order deleted successfully!</h3>");
		                    out.println("</font>");
	                    out.println("</div>");
	                }
	                else
	                {
	                	out.println("<div class='head-te'>");
		                    out.println("<font color='red'>");
		                    	out.println("<center><h3>Order did not deleted successfully!</h3></center>");
		                    out.println("</font>");
	                    out.println("</div>");
	                }
	                session.removeAttribute("order-delete-status");
            	}
            
      out.write("\r\n");
      out.write("\t            \r\n");
      out.write("            ");
 
	            List<Order> orders = (List<Order>) session.getAttribute("orders");
	
	            int numAcceptedOrders = 0;
	            int numNonAcceptedOrders = 0;
	
	            for(Order order : orders)
	            {
	                if(order.getResponse() != null)
	                    numAcceptedOrders++;
	
	                else
	                    numNonAcceptedOrders++;
	            }
	            out.println("<div class='btn-container'>");
	            	out.println("<font color='blue'>");
	           
			            out.println(String.format(
			                    "<h3>Number of Accepted orders yet: %s / %s</h3>", numAcceptedOrders, numAcceptedOrders + numNonAcceptedOrders)
			            );
	           		out.println("</font>");

		            out.println("<font color='red'>");
			            out.println(String.format(
			                    "<h3>Number of Non-accepted orders yet: %s / %s</h3><br><br>", numNonAcceptedOrders, numAcceptedOrders + numNonAcceptedOrders)
			            );
		            out.println("</font>");
	            out.println("</div>");
	        }
	    
      out.write("\r\n");
      out.write("\t\t");

	        DateTimeHelper dateTimeHelper = new DateTimeHelper();
	        List<Order> orders = (List<Order>) session.getAttribute("orders");
	
	        String titleTemplate = "<h3>%s</h3><br>";
	        String fieldTemplate = "<p><b> %s </b> : %s </p>";
	
	        for(Order order : orders)
	        {
	            String customerId = order.getCustomer().getId();
	            String vehicleId = order.getVehicle().getId();
	            String fromLocation = order.getFromLocation().getDistrict();
	            String toLocation = order.getToLocation().getDistrict();
	            String requestedOn =  order.getRequestedDateTime().toString();
	            String requestedDateTime = "On: ".concat(dateTimeHelper.formatDateTimeStr(requestedOn));
	            LocalDateTime acceptedOn = order.getRespondedDateTime();
	            String acceptedDateTime = "Not Yet";
	
	            if(acceptedOn != null)
	            {
	                String acceptedOnDt = acceptedOn.toString();
	
	                acceptedDateTime = dateTimeHelper.formatDateTimeStr(acceptedOnDt);
	            }
	            String review = order.getReview();
	
	            out.println("<div class='row'>");
	            	out.println("<div class='column'>");
	            		out.println("<div class='card'>");
	            			out.println("<div class='container'>");
	            				out.println(String.format(titleTemplate, requestedDateTime));
	            				out.println(String.format(fieldTemplate, "Customer-ID", customerId));
	            				out.println(String.format(fieldTemplate, "Vehicle-ID", vehicleId));
	            				out.println(String.format(fieldTemplate, "Journey start", fromLocation));
	            				out.println(String.format(fieldTemplate, "Journey end", toLocation));
	            				out.println(String.format(fieldTemplate, "Accepted on", acceptedDateTime));
	            				out.println(review != null ? String.format(fieldTemplate, "Review", review) : "");
		         			out.println("</div>");
		 				out.println("</div>");
					out.println("</div>");
				out.println("</div>");
	            
	            if(session.getAttribute("logged-customer") != null) {
	                if (review == null) {
			            out.println("<form action='/Cab_It/review/order' method='POST'>");
	                    out.println("<br><br>");
	                    
	                    out.println("<h2 id='make-review'>Make some review :</h2>");
	                    out.println("<textarea name='review'></textarea>");
	                    out.println(String.format("<input type='text' hidden='true' value='%s' name='customer-id'>", order.getCustomer().getId()));
	                    out.println(String.format("<input type='text' hidden='true' value='%s' name='vehicle-id'>", order.getVehicle().getId()));
	                    out.println(String.format("<input type='text' hidden='true' value='%s' name='from-location-id'>", order.getFromLocation().getId()));
	                    out.println(String.format("<input type='text' hidden='true' value='%s' name='to-location-id'>", order.getToLocation().getId()));
	                    out.println(String.format("<input type='text' hidden='true' value='%s' name='req-date-time'>", order.getRequestedDateTime()));
	
	                    out.println("<input type='submit' value='Post Review' id='signup-btn-4'> ");
	                    out.println("</form>");
	                }
	            }
		    	
	            if(session.getAttribute("logged-admin") != null) {
	
	                out.println("<form action='/Cab_It/order/delete.jsp' method='POST'>");
	
	                out.println(String.format("<input type='text' hidden='true' value='%s' name='del-customer-id'>", order.getCustomer().getId()));
	                out.println(String.format("<input type='text' hidden='true' value='%s' name='del-vehicle-id'>", order.getVehicle().getId()));
	                out.println(String.format("<input type='text' hidden='true' value='%s' name='del-from-location-id'>", order.getFromLocation().getId()));
	                out.println(String.format("<input type='text' hidden='true' value='%s' name='del-to-location-id'>", order.getToLocation().getId()));
	                out.println(String.format("<input type='text' hidden='true' value='%s' name='del-req-date-time'>", order.getRequestedDateTime()));
	                out.println("<input type='submit' value='Delete' id='signup-btn-3'>");
	
	                out.println("</form>");
	            }
	        }
	    
      out.write("\r\n");
      out.write("\t    <form action=\"/Cab_It/dashboard\">\r\n");
      out.write("\t        <input type=\"submit\" value=\"Dashboard\" id=\"signup-btn-1\">\r\n");
      out.write("\t    </form>\r\n");
      out.write("\t</body>\r\n");
      out.write("\r\n");
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
