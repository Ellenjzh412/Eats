package Util;

import java.util.ArrayList;
import java.util.Locale.Category;

import org.apache.catalina.tribes.group.interceptors.NonBlockingCoordinator.CoordinationEvent;

import javax.xml.stream.Location;

/**
 * The class used to model a business
 */
public class Restaurants {
	private String id;
    private String name;
    private String image_url;
    private String is_closed;
    private String url;
    private int review_count;
    private ArrayList<String> categories;
    private int rating;
    private String price;
    private Location location;
    private String phone;


    public Restaurants(){
    	
    }
    
    public Restaurants(String id, String name, String image_url, String is_closed, String url, int review_count, ArrayList<String> categories, int rating, String price, Location location, String phone) {
        this.id = id;
    	this.name = name;
        this.image_url = image_url;
        this.is_closed = is_closed;
        this.url = url;
        this.review_count = review_count;
        this.categories = categories;
        this.rating = rating;
        this.price = price;
        this.location = location;
        this.phone = phone;
    }
    
    public String getID() {
    	return id;
    }
    
    public void setID(String id) {
    	this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getIs_closed() {
        return is_closed;
    }

    public void setIs_closed(String is_closed) {
        this.is_closed = is_closed;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getReview_count() {
        return review_count;
    }

    public void setReview_count(int review_count) {
        this.review_count = review_count;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    

}

