import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.Location;

import com.mysql.cj.xdevapi.Statement;

import Util.JsonToDB;
import Util.Restaurants;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class SearchDispatcher
 */
@WebServlet("/SearchDispatcher")
public class SearchDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public SearchDispatcher() {
    }

//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        super.init(config);
//        ServletContext servletContext = getServletContext();
//        // TODO get json file as stream, Initialize FakeYelpAPI by calling its initalize
//        // method
//        
//        JsonToDB toDB = new JsonToDB();
//        toDB.inportToDB();
//        System.out.println("Successfully inport data into Database");
//    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
    		
    		
    		String search_word = request.getParameter("restaurant");
    		String question = request.getParameter("Name");
    		String radioSelection = request.getParameter("best_match");
    		
    		
    	  	try {
    	  		Class.forName("com.mysql.cj.jdbc.Driver");
    	  	}
    	  	catch (ClassNotFoundException e) {
    	  		e.printStackTrace();
    	  	}
    	  	
    	  //if all is valid, then insert into database.
    	  	String mysqlUrl = "jdbc:mysql://localhost:3306/saleats";
    	  	String user = "root"; 
    	  	String pwd = "Ellenjiang0412";
            
    	  	String sql = "SELECT * FROM saleats.Restaurant Where restaurant_name = ?";
    		
    	  	
    	  	PrintWriter out = response.getWriter();
    	  	List<Restaurants> restaurants = new ArrayList<>();
    	  	
    	  	try (Connection conn = DriverManager.getConnection(mysqlUrl, user, pwd);
    	  			PreparedStatement ps = conn.prepareStatement(sql);) {
    	  		
    	  		ps.setString(1,search_word);
    	  		
    	  		ResultSet resultSet = ps.executeQuery();
    	  		
                while (resultSet.next()) 
                {
                	Restaurants currRest = new Restaurants();
                	
                	currRest.setID(resultSet.getString(1));
                	currRest.setName(resultSet.getString(2));
 
                	restaurants.add(currRest);
 
                }
    	  	}
    	  	catch (SQLException ex) {
    	  		System.out.println("SQLException" + ex.getMessage());
    	  	}
    	  	
    	  	
    	  	//String sql2 = "SELECT * FROM saleats.Restaurant_details Where restaurant_name = ?";
    	  
    	  	request.setAttribute("results", restaurants);
    	
    	  	out.println("<p>You have successfully searched huhu!</p>");
    	  	request.getRequestDispatcher("/result.jsp").include(request, response);
    	  	return;
    	  	
    	  	
    	  
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}