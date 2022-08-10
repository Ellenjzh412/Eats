DROP DATABASE IF EXISTS saleats;

CREATE DATABASE saleats;

USE saleats;

DROP TABLE IF EXISTS Restaurant_details;

CREATE TABLE Restaurant_details (
  details_id VARCHAR(255) PRIMARY KEY ,
  restaurant_name VARCHAR(50) NOT NULL,
  image_url VARCHAR(512) NOT NULL,
  display_address1 VARCHAR(512) ,
  phone_no VARCHAR(512) ,
  estimated_price VARCHAR(512),
  yelp_url VARCHAR(512) NOT NULL
);

DROP TABLE IF EXISTS Rating_details;

CREATE TABLE Rating_details (
  rating_id VARCHAR(255) PRIMARY KEY  ,
  review_count INTEGER NOT NULL,
  rating DOUBLE NOT NULL
);

DROP TABLE IF EXISTS Restaurant;

CREATE TABLE Restaurant (
  restaurant_id VARCHAR(255) PRIMARY KEY,
  restaurant_name VARCHAR(50) NOT NULL,
  details_id VARCHAR(255) NOT NULL,
  rating_id VARCHAR(255) NOT NULL,
  FOREIGN KEY (details_id) REFERENCES Restaurant_details(details_id),
  FOREIGN KEY (rating_id) REFERENCES Rating_details(rating_id)
);

DROP TABLE IF EXISTS Category;

CREATE TABLE Category (
  category_id VARCHAR(255) PRIMARY KEY,
  category_name VARCHAR(50) NOT NULL,
  restaurant_id VARCHAR(255) NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES Restaurant(restaurant_id)
);

DROP TABLE IF EXISTS User;

CREATE TABLE User(
  user_id int NOT NULL,
  user_name VARCHAR(50) NOT NULL,
  user_email VARCHAR(50) NOT NULL,
  user_password VARCHAR(500) NOT NULL,
  PRIMARY KEY (user_email)
) 