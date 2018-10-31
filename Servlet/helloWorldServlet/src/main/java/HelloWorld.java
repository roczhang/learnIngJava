// Import required java libraries

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

// Extend HttpServlet class
public class HelloWorld extends HttpServlet {

    private String message;

    public void init() throws ServletException {
        // Do required initialization
        message = "Hello World";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set response content type
        response.setContentType("text/html");

        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        out.println("<h1>" + message + "</h1>");

        // Create cookies for first and last names.
        Cookie firstName = new Cookie("first_name", "first_name");
        Cookie lastName = new Cookie("last_name", "last_name");

        // Set expiry date after 24 Hrs for both the cookies.
        firstName.setMaxAge(60 * 60 * 24);
        lastName.setMaxAge(60 * 60 * 24);

        // Add both the cookies in the response header.
        response.addCookie(firstName);
        response.addCookie(lastName);


        // Create a session object if it is already not  created.
        HttpSession session = request.getSession(true);
        String title = "Welcome Back to my website";
        Integer visitCount = new Integer(0);
        String visitCountKey = new String("visitCount");
        String userIDKey = new String("userID");
        String userID = new String("ABCD");

        // Check if this is new comer on your web page.
        if (session.isNew()) {
            title = "Welcome to my website";
            session.setAttribute(userIDKey, userID);
        } else {
            visitCount = (Integer) session.getAttribute(visitCountKey);
            if( visitCount == null) visitCount=1;

            visitCount = visitCount + 1;
            userID = (String) session.getAttribute(userIDKey);
        }
        session.setAttribute(visitCountKey, visitCount);

        // Set response content type
        response.setContentType("text/html");


        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " +
                        "transitional//en\">\n";

        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +

                "<body bgcolor = \"#f0f0f0\">\n" +
                "<h1 align = \"center\">" + title + "</h1>\n" +
                "<h2 align = \"center\">Session Infomation</h2>\n" +
                "<table border = \"1\" align = \"center\">\n" +

                "<tr bgcolor = \"#949494\">\n" +
                "  <th>Session info</th><th>value</th></tr>\n" +

                "<tr>\n" +
                "  <td>id</td>\n" +
                "  <td>" + session.getId() + "</td>       </tr>\n" +


                "<tr>\n" +
                "  <td>User ID</td>\n" +
                "  <td>" + userID + "  </td>               </tr >\n " +

                "<tr>\n" +
                "  <td>Number of visits</td>\n" +
                "  <td>" + visitCount + "</td>                </tr >\n " +
                "</table>\n" +
                "</body>                </html > "
        );

    }

    public void destroy() {
        // do nothing.
    }
}