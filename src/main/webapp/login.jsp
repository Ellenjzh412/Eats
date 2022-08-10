<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Util.Helper"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login/Sign Up</title>

	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="pragma" content="no-cache" />
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link href= "index.css" rel="stylesheet" type="text/css">
	<link rel = "stylesheet" href='https://fonts.googleapis.com/css?family=Lobster'>
	<script src="https://kit.fontawesome.com/aef7737b1c.js" crossorigin="anonymous"></script>
	

</head>
<body>

	<!-- HEADER-->
	<header>
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
			
			for(Cookie c : cookies){
				if(c.getName().equals("cookie_name")){
					if(!c.getValue().equals("")){
						
						
						hasCookie = true;
						userName = Helper.getUserName(c.getValue());
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
		  					
		  				
		  				%>
		  				</a></li>
						<li class="nav-item"><a class="nav-link" href="index.jsp">Home
						<%
						  out.println(userName);
						%></a></li>
					</ul>	
												
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
   	</header>
   	<br>
   	<hr>
	<br>
	
	<div class="container">
    	<div class="row">
    		<!-- Restaurant Name -->
      		<div class="col-6">
	      		<h2>Login</h2>
	      		<form action="LoginDispatcher" method="GET">
	      			<div class="form-group">
	      				<label>Useremail</label>
	      				<input type="text" class="form-control" name="user_email" required>
	      				<label>Password</label>
	      				<input type="password" class="form-control" name="user_password" required>
	      				<br>
	      				<button type="Submit" class="btn-block form-controll btn search"><i class="fa fa-sign-in" aria-hidden="true"></i> Sign in</button>
					</div>
	      		</form>
      		</div>

      		<div class="col-6">
      			<h2>Sign Up</h2>
	      		<form action="RegisterDispatcher" method="GET">
	      			<div class="form-group">
		      			<label>Email</label>
		      			<input type="email" class="form-control" name="user_email" required>
		      			<label>Username</label>
		      			<input type="text" class="form-control" name="user_name" required>
		      			<label>Password</label>
		      			<input type="password" class="form-control" name="user_password" required>
		      			<label>Confirm Password</label>
		      			<input type="password" class="form-control" name="confirm_password" required>
		      			<br>
		      			
					  	<div class="form-check">
					    	<input type="checkbox" class="form-check-input">
					    	<label>I have read and agree to all terms and conditions in saleats.</label>
					  	</div>
		      			<button type="Submit" class="search btn-block form-controll btn "><i class="fa fa-user-plus" aria-hidden="true"></i> Create Account</button>
	      			</div>
	      		</form>
      		</div>
      	</div>
      </div>

</body>
</html>