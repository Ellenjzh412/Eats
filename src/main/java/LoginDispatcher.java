
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.Helper;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.sql.SQLException;

/**
 * Servlet implementation class LoginDispatcher
 */

@WebServlet("/LoginDispatcher")
public class LoginDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	String useremail = request.getParameter("user_email");
		String password = request.getParameter("user_password");
		
		String errorMessage = "<p>Invalid Credentials. Please try again.</p>";
    	
		PrintWriter out = response.getWriter();
   
    	try {
			if(!Helper.checkPassword(useremail, password)) {
				out.println(errorMessage);
				request.getRequestDispatcher("/login.jsp").include(request, response);
				return;
			}
		} catch (SQLException | ServletException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    	
    	//set up cookie 
    	String emailCookie = request.getParameter("user_email");
       	Cookie cookieName = new Cookie("cookie_name", emailCookie);
       	cookieName.setMaxAge(60 * 60);
    	response.addCookie(cookieName);
    	response.sendRedirect("index.jsp");

		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
