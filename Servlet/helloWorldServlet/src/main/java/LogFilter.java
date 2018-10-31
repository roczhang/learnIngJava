// Import required java libraries
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

// Implements Filter class
public class LogFilter implements Filter  {

   Logger log = LogManager.getLogger("LogFilter");

   static int count = 0;
   public void  init(FilterConfig config) throws ServletException {
      
      // Get init parameter 
      String testParam = config.getInitParameter("test-param"); 

      //Print the init parameter 
      System.out.println("Test Param: " + testParam + " - " + count++ );
      log.info("Test Param: " + testParam + " - " + count++);
   }
   
   public void  doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws java.io.IOException, ServletException {

      // Get the IP address of client machine.
      String ipAddress = request.getRemoteAddr();
      int port = request.getRemotePort();

      // Log the IP address and current timestamp.
      System.out.println("IP "+ ipAddress + ", Time " + new Date().toString() + " - " + count++);
      log.info("IP "+ ipAddress +":" + port + ", Time " + new Date().toString() + " - " + count++);

      // Pass request back down the filter chain
      chain.doFilter(request,response);
   }

   public void destroy( ) {
      /* Called before the Filter instance is removed from service by the web container*/
   }
}