<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Util.Helper"%>
<!DOCTYPE html>
<html>

<head>
	<title>SalEats</title>

	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="pragma" content="no-cache" />
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link href= "index.css" rel="stylesheet" type="text/css">
	<link rel = "stylesheet" href='https://fonts.googleapis.com/css?family=Lobster'>
	<script src="https://kit.fontawesome.com/aef7737b1c.js" crossorigin="anonymous"></script>

</head>


<body>
	<!--  HeadLine -->
	<nav class="navbar navbar-expand-md navbar-light">
		<h1>
			<a class="header-style" href="index.jsp">SalEats!</a>
		</h1>
		

		<div class="collapse navbar-collapse">
				
			<%
			boolean hasCookie = false; 
			String userName = ""; 
			Helper helper = new Helper();
			Cookie cookies[] = request.getCookies();
			
			if(request.getCookies()!= null){
				for(Cookie c : cookies){
					if(c.getName().equals("cookie_name")){
						if(!c.getValue().equals("")){
							
							hasCookie = true;
							userName = Helper.getUserName(c.getValue());
	
						}
					}
				}
			}
			%>
			
			<%
				if(hasCookie){
			
			%>
		  			<ul class="navbar-nav ml-auto">
		  				<li class="nav-item"><a class="nav-link" href="index.jsp">Hi! 
		  				<%
						  out.println(userName);
						%></a></li>
						<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
					</ul>	
										
					<form action="LogoutDispatcher" method="GET">
		  				<button class="navbar-nav ml-auto nav-item btn btn-light" type="submit">Logout</button>
					</form>				
			<%
				}else{
			
			%>		
		  			<ul class="navbar-nav ml-auto">
						<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
						<li class="nav-item"><a class="nav-link" href="login.jsp">Login / Sign Up</a></li>
					</ul>
					
			<%	
				}
			%>
	
		</div>
	</nav>
	
	<!-- IMAGE -->
    <div id="fill">
    	<img src="banner.jpeg" alt="" >
    </div>

	<br>
	
	<form action="SearchDispatcher" method="GET">
	
				
				<select id="select-choice">
					<option value="Category">Category</option>
					<option value="Name">Name</option>	
				</select>
				
				<input id="search-bar" type="text" placeholder="Name" name="restaurant" required>
					<button type="submit" class="btn btn-danger" id="red-button">
						<i class="fas fa-search"></i>
					</button>
						
					<div class="col-3 " id="btns">
						<div class="form-check-inline radio">
						<input class="form-check-input radio-item" type="radio" name="radio" value="best_match" checked>
						<label class="form-check-label">Best Match</label>
					</div>
					<br>
					<div class="form-check-inline radio">
						<input class="form-check-input radio-item" type="radio" name="radio" value="review_count">
						<label class="form-check-label">Review Count</label>
					</div>
					<br>
					<div class="form-check-inline radio">
						<input class="form-check-input radio-item" type="radio" name="radio" value="rating">
						<label class="form-check-label">Rating</label>
					</div>
				</div>
			

	</form>

    
</body>
</html>