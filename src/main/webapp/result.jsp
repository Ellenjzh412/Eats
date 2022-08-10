<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@page import="Util.Business"%>
<%@page import="Util.BusinessId"%>
<%@page import="Util.Service"%>
         

 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    
    <title>Search</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
            href="https://fonts.googleapis.com/css2?family=Lobster&family=Roboto:wght@300&display=swap"
            rel="stylesheet">
    <link rel="stylesheet" href="index.css">
    <script src="https://kit.fontawesome.com/3204349982.js"
            crossorigin="anonymous"></script>
            
 	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="pragma" content="no-cache" />
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link href= "index.css" rel="stylesheet" type="text/css">
	<link rel = "stylesheet" href='https://fonts.googleapis.com/css?family=Lobster'>
	<script src="https://kit.fontawesome.com/aef7737b1c.js" crossorigin="anonymous"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 

    <%
        //TODO perform search
        //TODO check if logged in
    %>
</head>
<body>
<!-- TODO -->
	<!--  HeadLine -->
	<nav class="navbar navbar-expand-md navbar-light">
		<h1>
			<a class="header-style" href="index.jsp">SalEats!</a>
		</h1>
		
		<div class="collapse navbar-collapse">
  			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="login.jsp">Login / Sign Up</a></li>
			</ul>										
		</div>
	</nav>
	
	
	<form action="SearchDispatcher" method="GET">
			
				<select id="select-choice">
					<option value="Category">Category</option>
					<option value="Name">Name</option>	
				</select>
				
				<input id="search-bar" type="text" placeholder="Name" name="restaurant" class="textbox" required>
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
	
<h3>
	Results for
	
	<%=request.getParameter("search")%>
		in
	<%=request.getParameter("type")%></h3>

	<%
	Service service = Service.getInstance();
	String type = request.getParameter("type");
	String query = request.getParameter("search");
	String sort = request.getParameter("sort");
	BusinessId[] businessIds = service.search(type, query, sort);
	Business[] business = service.searchBusinesses(businessIds);
	if (business != null && business.length > 0) {
		for (Business b : business) {
			if (b != null) {
	%>
	
	<a class="no-deco" href="details.jsp?uid=<%=b.getId()%>">
		<div class="search-row">
			<div class="search-row-image-div">
				<img class="search-row-image" src="<%=b.getImage()%>"
					alt="<%=b.getName()%>">

			</div>
			<div class="search-row-info">

				<h3>
					<%=b.getName()%>
				</h3>

				<p class="no-deco">
					Price:
					<%=b.getPrice() != null ? b.getPrice() : "Not Provided"%></p>
				<p class="no-deco">
					Review Count:
					<%=b.getReviewCount()%></p>
				<p class="no-deco">
					Rating:
					<%
				for (int i = 0; i < (int) b.getRating(); i++) {
				%>
					<span class="fa fa-star"></span>
					<%
					}
					%>
					<%
					// half star
					if (b.getRating() % 1 != 0) {
					%>
					<span class="fa fa-star-half"></span>
					<%
					}
					%>
				</p>
				</p>
				<p class="no-deco">
					<a class="yelp-url" href="<%=b.getUrl()%>" target="_blank"> <i
						class="fab fa-yelp"></i> Yelp Link
					</a>
				</p>
			</div>
		</div>
	</a>
	<%
	}
	}
	} else {
	%>
	<h3>No results found.</h3>
	<%
	}
	%>

	
</body>
</html>