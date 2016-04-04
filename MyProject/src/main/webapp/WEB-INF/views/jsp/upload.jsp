<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="springForm"
	uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Videocean</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- bootstrap -->
<link href="css/bootstrap.min.css" rel='stylesheet' type='text/css' media="all" />
<!-- //bootstrap -->
<link href="css/dashboard.css" rel="stylesheet">
<!-- Custom Theme files -->
<link href="css/style.css" rel='stylesheet' type='text/css' media="all" />
<script src="js/jquery-1.11.1.min.js"></script>
<!--start-smoth-scrolling-->
<!-- fonts -->
<link href='//fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
<!-- //fonts -->
</head>
  <body>
 <%@ include file="/static/loggedHeader.html" %>
		<!-- upload -->
		<div class="upload">
			<!-- container -->
			<div class="container">
				<div class="upload-grids">
					<div class="upload-right">
						<div class="upload-file">
							<div class="services-icon">
								<span class="glyphicon glyphicon-open" aria-hidden="true"></span>
							</div>
							<input type="file" value="Choose file..">
						</div>
						<div class="upload-info">
							<h5>Select files to upload</h5>
							<span>or</span>
							<p>Drag and drop files</p>
						</div>
					</div>
					<div class="upload-right-bottom-grids">
						<div class="col-md-4 upload-right-bottom-left">
							<h4>Help and Suggestions</h4>
							<div class="upload-right-top-list">
								<ul>
									<li><a href="#">Lorem ipsum dolor sit amet</a></li>
									<li><a href="#">Fusce egestas tincidunt massa</a></li>
									<li><a href="#">Pellentesque vehicula quis tellus</a></li>
									<li><a href="#">Etiam gravida feugiat tortor eget eleifend</a></li>
									<li><a href="#">Etiam iaculis facilisis metus a viverra</a></li>
									<li><a href="#">Fusce sed enim non orci molestie</a></li>
									<li><a href="#">Lorem ipsum dolor sit amet</a></li>
								</ul>
							</div>
						</div>
						<div class="col-md-4 upload-right-bottom-left">
							<h4>Import videos</h4>
							<div class="upload-right-top-list">
								<ul>
									<li><a href="#">Lorem ipsum dolor sit amet</a></li>
									<li><a href="#">Fusce egestas tincidunt massa</a></li>
									<li><a href="#">Pellentesque vehicula quis tellus</a></li>
									<li><a href="#">Etiam gravida feugiat tortor eget eleifend</a></li>
									<li><a href="#">Etiam iaculis facilisis metus a viverra</a></li>
									<li><a href="#">Fusce sed enim non orci molestie</a></li>
									<li><a href="#">Lorem ipsum dolor sit amet</a></li>
								</ul>
							</div>
						</div>
						<div class="col-md-4 upload-right-bottom-left">
							<h4>Live streaming</h4>
							<div class="upload-right-top-list">
								<ul>
									<li><a href="#">Lorem ipsum dolor sit amet</a></li>
									<li><a href="#">Fusce egestas tincidunt massa</a></li>
									<li><a href="#">Pellentesque vehicula quis tellus</a></li>
									<li><a href="#">Etiam gravida feugiat tortor eget eleifend</a></li>
									<li><a href="#">Etiam iaculis facilisis metus a viverra</a></li>
									<li><a href="#">Fusce sed enim non orci molestie</a></li>
									<li><a href="#">Lorem ipsum dolor sit amet</a></li>
								</ul>
							</div>
						</div>
						<div class="clearfix"> </div>
					</div>
				</div>
			</div>
			<!-- //container -->
		</div>
		<!-- //upload -->
			<!-- footer -->
		<%@ include file="/static/footer.html" %>
			<!-- //footer -->
		<div class="clearfix"> </div>
	<div class="drop-menu">
		<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu4">
		  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Regular link</a></li>
		  <li role="presentation" class="disabled"><a role="menuitem" tabindex="-1" href="#">Disabled link</a></li>
		  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Another link</a></li>
		</ul>
	</div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
  </body>
</html>