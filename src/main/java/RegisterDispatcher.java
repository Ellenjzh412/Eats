
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.Helper;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
* Servlet implementation class RegisterDispatcher
*/

@WebServlet("/RegisterDispatcher")
public class RegisterDispatcher extends HttpServlet {
  @Serial
  private static final long serialVersionUID = 1L;

  /**
   * Default constructor.
   */
  public RegisterDispatcher() {
  }

  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
  	try {
  		Class.forName("com.mysql.cj.jdbc.Driver");
  	}
  	catch (ClassNotFoundException e) {
  		e.printStackTrace();
  	}
  	
  	String email = null;
  	String password = null;
  	String name = null;
  	String confirm_password = null;
  	
  	email = request.getParameter("user_email");
  	name = request.getParameter("user_name");
  	password = request.getParameter("user_password");
  	confirm_password = request.getParameter("confirm_password");
  	  	
  	String errorMessage = "";
  	String ogerrorMessage = "";
  	
  	response.setContentType("text/html");
  	PrintWriter out = response.getWriter();
  	
  	
  	if(name.equals(" ") || !Helper.validName(name)) {
  		errorMessage = "<p>Name is missing or invalid. ";
  	}
  	
  	if(email.equals(" ") || email.indexOf("@") == -1 || !Helper.isValidEmail(email)) {
  		errorMessage = "<p>Email is missing or invalid. ";
  	}
  	
  	if(password.equals(" ")) {
  		errorMessage = "<p>Password is missing or invalid. ";
  	}
  	
  	if(confirm_password.equals(" ")|| !password.equals(confirm_password)) {
  		errorMessage = "<p>Password confirmation does not match password. ";
  	}
  	
  	else if(Helper.emailAlreadyRegistered(email, request, response)) {
  		errorMessage = "<p>User with this email is already registered. Please log in. ";
  	}
  	
  	if(!errorMessage.equals(ogerrorMessage)) {
  		errorMessage += "</p>";
  		out.println(errorMessage);
  		request.getRequestDispatcher("/login.jsp").include(request, response);
  		return;
  	}
  	
  	
  	//if all is valid, then insert into database.
  	String mysqlUrl = "jdbc:mysql://localhost:3306/saleats";
  	String user = "root"; 
  	String pwd = "Ellenjiang0412"; 
  	
  	String sql = "INSERT INTO User(user_id,user_name,user_email,user_password) VALUES (?,?,?,?)";
  	try (Connection conn = DriverManager.getConnection(mysqlUrl, user, pwd);
  			PreparedStatement ps = conn.prepareStatement(sql);) {
  		ps.setInt(1, 1);
  		ps.setString(2, name);
  		ps.setString(3, email);
  		ps.setString(4, password);
  		int row = ps.executeUpdate();

  	}
  	catch (SQLException ex) {
  		System.out.println("SQLException" + ex.getMessage());
  	}
  	
  	out.println("You have successfully registered!");
  	request.getRequestDispatcher("/login.jsp").include(request, response);
  	return;
  }
}


