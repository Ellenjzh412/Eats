package Util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class JsonToDB {
    public static Connection ConnectToDB() throws Exception {
        //Registering the Driver
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        //Getting the connection
        String mysqlUrl = "jdbc:mysql://localhost:3306/saleats";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "Ellenjiang0412");
        System.out.println("Connection established......");
        return con;
    }
    
    public static void main(String args[]) {
        //Creating a JSONParser object
        JSONParser jsonParser = new JSONParser();
        try {
            //Parsing the contents of the JSON file
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("restaurant_data.json"));
            //Retrieving the array
            JSONArray jsonArray = (JSONArray) jsonObject.get("businesses");
            Connection con = ConnectToDB();
            //Insert a row into the Rating_details table            
   
            for(Object object : jsonArray) {
            	JSONObject business = (JSONObject) object;
    			
            	String rating_id = (String) business.get("id");
            	long reviewCount = (long)business.get("review_count");
    			Double rating = (Double)business.get("rating");
    			PreparedStatement pstmt = con.prepareStatement("INSERT INTO Rating_details (rating_id, review_count, rating) VALUES (?, ? , ?)");
    			
                pstmt.setString(1, rating_id);
                pstmt.setLong(2, reviewCount);
                pstmt.setDouble(3, rating);
                
                PreparedStatement pstmt1 = con.prepareStatement("INSERT INTO Restaurant_details (details_id, restaurant_name, image_url, display_address1, phone_no, "
                		+ "estimated_price, yelp_url) VALUES (?, ?, ?, ?, ?, ?, ?)");
                String details_id = (String) business.get("id");
                String restaurant_name = (String) business.get("name");
                String image_url = (String) business.get("image_url");
                
                JSONObject location = (JSONObject)business.get("location");
                
                JSONArray display_addressArray = (JSONArray)location.get("display_address");
                String display_address = "";
                for(int i = 0 ; i < display_addressArray.size(); i++) {
                	display_address += display_addressArray.get(i);
                }
                
                String phone_no = (String)business.get("phone");
                String estimated_price = (String)business.get("price");
                String yelp_url = (String)business.get("url");
                
                pstmt1.setString(1, details_id);
                pstmt1.setString(2, restaurant_name);
                pstmt1.setString(3, image_url);
                pstmt1.setString(4, display_address);
 
                pstmt1.setString(5, phone_no);
                pstmt1.setString(6, estimated_price);
                pstmt1.setString(7, yelp_url);

                
                PreparedStatement pstmt2 = con.prepareStatement("INSERT INTO Restaurant (restaurant_id, restaurant_name, details_id, rating_id) VALUES (?, ?, ?, ?)");
                String restaurant_id = (String) business.get("id");
                
                pstmt2.setString(1, restaurant_id);
                pstmt2.setString(2, restaurant_name);
                pstmt2.setString(3, details_id);
                pstmt2.setString(4, rating_id);
                
                PreparedStatement pstmt3 = con.prepareStatement("INSERT INTO Category (category_id, category_name, restaurant_id) VALUES (?, ?, ?)");
                
                JSONArray category = (JSONArray)business.get("categories");
                
                for(int i = 0 ; i < category.size(); i++) {
                	
                	jsonObject = (JSONObject)category.get(i);
                	//System.out.println(jsonObject.get("title"));
                	String title = (String)jsonObject.get("title");
                	
                	pstmt3.setString(1, restaurant_id);
                	pstmt3.setString(2, title);
                	pstmt3.setString(3, restaurant_id);
                	
                }
                          
                
                pstmt.executeUpdate();
                pstmt1.executeUpdate();
                pstmt2.executeUpdate();
                pstmt3.executeUpdate();
                
                
            }
            
            System.out.println("Records inserted.....");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
