package Util;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Helper {
    /**
     * check if name is valid
     *
     * @param name the name user provides
     * @return valid or not valid
     */
    public static boolean validName(String name) {
        return Constant.namePattern.matcher(name).matches();
    }

    /**
     * check if email is valid
     *
     * @param email the email user provides
     * @return valid or not valid
     */
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return Constant.emailPattern.matcher(email).matches();
    }

    /**
     * Get username with the email
     *
     * @param email
     * @return userName
     * @throws SQLException
     */
    public static String getUserName(String email) throws SQLException {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    	}
    	catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	
        //Getting the connection
        String mysqlUrl = "jdbc:mysql://localhost:3306/saleats";
        try {
			Connection con = DriverManager.getConnection(mysqlUrl, "root", "Ellenjiang0412");
			
			String sql = "SELECT user_name FROM User WHERE user_email=?";
	    	
	    	PreparedStatement ps = con.prepareStatement(sql);
	    	ps.setString(1, email);
	    	
	    	ResultSet rs= ps.executeQuery();
	    	
	    	String output = "";
	    	if(rs.next()) {
	    		output = rs.getString(1);
	    	}
	    	
	        return output;
        }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "";
    }
    	
    public static int getUserID(String email) throws SQLException {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    	}
    	catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	
    	
        //Getting the connection
        String mysqlUrl = "jdbc:mysql://localhost:3306/saleats";
        try {
			Connection con = DriverManager.getConnection(mysqlUrl, "root", "Ellenjiang0412");
			
			String sql = "SELECT user_name FROM User WHERE user_email=?";
			PreparedStatement ps = con.prepareStatement(sql);
	    	ps.setString(1, email);
	    	
	    	ResultSet rs= ps.executeQuery();
	    	
	    	int output = 0;
	    	if(rs.next()) {
	    		output = rs.getInt(1);
	    	}
	    	
	    	return output;
        }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return -1;
    }
    	
    public static boolean checkPassword(String useremail, String password) throws SQLException {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		System.out.println("Connection1 Success!");
    	}
    	catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	
        String mysqlUrl = "jdbc:mysql://localhost:3306/saleats";
        try {
			Connection con = DriverManager.getConnection(mysqlUrl, "root", "Ellenjiang0412");
			System.out.println("Connection2 Success!");
			String sql = "SELECT user_password FROM User WHERE user_email=?";
			PreparedStatement ps = con.prepareStatement(sql);
	    	
			ps.setString(1, useremail);	    	
	    	ResultSet rs= ps.executeQuery();
    		if(rs.next()) {
    			if(rs.getString("user_password").equals(password)){
        			return true;
    			}
    		}
    		else {
    			return false;
    		}
    		
        }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return false;
    }
    	
    
    public static boolean emailAlreadyRegistered(String email, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
	    	try {
	    		Class.forName("com.mysql.cj.jdbc.Driver");
	    	}
	    	catch (ClassNotFoundException e) {
	    		e.printStackTrace();
	    	}
    					
	        String sql = "SELECT * FROM User WHERE user_email = ?";
	        String url = "jdbc:mysql://localhost:3306/saleats"; 
	    	String user = "root"; 
	    	String pwd = "Ellenjiang0412";  //your secret database pwd
	        	
	    	try (Connection conn = DriverManager.getConnection(url, user, pwd);
	    			PreparedStatement statment = conn.prepareStatement(sql);) {
	    		statment.setString(1, email);
	    		ResultSet rs= statment.executeQuery();
	    		if(rs.next()) {
	    			return true;
	    		}
	    	}
	    	catch (SQLException ex) {
	    		System.out.println("SQLException" + ex.getMessage());
	    	}
	        return false;
	    }

	}   	

