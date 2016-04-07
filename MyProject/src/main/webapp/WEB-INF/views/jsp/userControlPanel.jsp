<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="springForm"
	uri="http://www.springframework.org/tags/form"%>
<%@ page errorPage="error.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Videocean</title>
<!-- added -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Augment Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
 <!-- Bootstrap Core CSS -->
<link href="css/bootstrap1.min.css" rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="css/fieldsStyle.css" rel='stylesheet' type='text/css' />
<!-- Graph CSS -->
<link href="css/font-awesome.css" rel="stylesheet"> 
<!-- jQuery -->
<link href='//fonts.googleapis.com/css?family=Roboto:700,500,300,100italic,100,400' rel='stylesheet' type='text/css'>
<!-- lined-icons -->
<link rel="stylesheet" href="css/icon-font.min.css" type='text/css' />
<!-- //lined-icons -->
<script src="jss/jquery-1.10.2.min.js"></script>
<script src="jss/amcharts.js"></script>	
<script src="jss/serial.js"></script>	
<script src="jss/light.js"></script>	
<script src="jss/radar.js"></script>	
<link href="css/barChart.css" rel='stylesheet' type='text/css' />
<link href="css/fabochart.css" rel='stylesheet' type='text/css' />
<!--clock init-->
<script src="jss/css3clock.js"></script>
<!--Easy Pie Chart-->
<!--skycons-icons-->
<script src="jss/skycons.js"></script>

<script src="jss/jquery.easydropdown.js"></script>
<!-- added -->



<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="My Play Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
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
     <%@ include file="/static/loggedSidebar.html" %>
     <br/>
      <br/>
       <br/>
       <!-- added -->
       <div class="custom-widgets">
												   <div class="row-one">
														<div class="col-md-3 widget">
															<div class="stats-left ">
																<h5>Today</h5>
																<h4> Users</h4>
															</div>
															<div class="stats-right">
																<label>90</label>
															</div>
															<div class="clearfix"> </div>	
														</div>
														<div class="col-md-3 widget states-mdl">
															<div class="stats-left">
																<h5>Today</h5>
																<h4>Visitors</h4>
															</div>
															<div class="stats-right">
																<label> 85</label>
															</div>
															<div class="clearfix"> </div>	
														</div>
														<div class="col-md-3 widget states-thrd">
															<div class="stats-left">
																<h5>Today</h5>
																<h4>Tasks</h4>
															</div>
															<div class="stats-right">
																<label>51</label>
															</div>
															<div class="clearfix"> </div>	
														</div>
														<div class="col-md-3 widget states-last">
															<div class="stats-left">
																<h5>Today</h5>
																<h4>Alerts</h4>
															</div>
															<div class="stats-right">
																<label>30</label>
															</div>
															<div class="clearfix"> </div>	
														</div>
														<div class="clearfix"> </div>	
													</div>
												</div>
       
     <!-- added -->
      <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="show-top-grids">
				<div class="col-sm-10 show-grid-left main-grids">
					<div class="recommended">
						<div class="recommended-grids english-grid">
							<div class="recommended-info">
								<div class="heading">
									<h3>My Clips</h3>
								</div>
								
								<div class="clearfix"> </div>
							</div>
							<c:forEach var="clip" items="${clip}">
					<div class="col-md-4 resent-grid recommended-grid slider-top-grids">
						<div class="resent-grid-img recommended-grid-img">
							<a href="playlist{id}"><img src="${clip.clipURL}" alt="" /></a>
							<div class="time">
								<p>3:04</p>
							</div>
							<div class="clck">
								<span class="glyphicon glyphicon-time" aria-hidden="true"></span>
							</div>
						</div>
						<div class="resent-grid-info recommended-grid-info">
							<h3><a href="single.html" class="title title-info">${clip.name}</a></h3>
							<ul>
								<li><p class="author author-info"><a href="#" class="author"> ${clip.owner.fullName}</a></p></li>
								<li class="right-list"><p class="views views-info"> ${clip.views} views</p></li>
							</ul>
						</div>
					</div>
					</c:forEach>
							<div class="col-md-3 resent-grid recommended-grid movie-video-grid">
								<div class="resent-grid-img recommended-grid-img">
									<a href="single"><img src="images/mv2.jpg" alt="" /></a>
									<div class="time small-time show-time movie-time">
										<p>9:34</p>
									</div>
									<div class="clck movie-clock">
										<span class="glyphicon glyphicon-time" aria-hidden="true"></span>
									</div>
								</div>
								<div class="resent-grid-info recommended-grid-info recommended-grid-movie-info">
									<h5><a href="single" class="title">Varius sit sed viverra Nullam interdum metus</a></h5>
									<ul>
										<li><p class="author author-info"><a href="#" class="author">John Maniya</a></p></li>
										<li class="right-list"><p class="views views-info">2,114,200 views</p></li>
									</ul>
								</div>
							</div>
							<div class="col-md-3 resent-grid recommended-grid movie-video-grid">
								<div class="resent-grid-img recommended-grid-img">
									<a href="single"><img src="images/mv3.jpg" alt="" /></a>
									<div class="time small-time show-time movie-time">
										<p>3:04</p>
									</div>
									<div class="clck movie-clock">
										<span class="glyphicon glyphicon-time" aria-hidden="true"></span>
									</div>
								</div>
								<div class="resent-grid-info recommended-grid-info recommended-grid-movie-info">
									<h5><a href="single" class="title">Varius sit sed viverra Nullam interdum metus</a></h5>
									<ul>
										<li><p class="author author-info"><a href="#" class="author">John Maniya</a></p></li>
										<li class="right-list"><p class="views views-info">2,114,200 views</p></li>
									</ul>
								</div>
							</div>
							<div class="col-md-3 resent-grid recommended-grid movie-video-grid">
								<div class="resent-grid-img recommended-grid-img">
									<a href="single"><img src="images/mv4.jpg" alt="" /></a>
									<div class="time small-time show-time movie-time">
										<p>2:06</p>
									</div>
									<div class="clck movie-clock">
										<span class="glyphicon glyphicon-time" aria-hidden="true"></span>
									</div>
								</div>
								<div class="resent-grid-info recommended-grid-info recommended-grid-movie-info">
									<h5><a href="single" class="title">Varius sit sed viverra Nullam interdum metus</a></h5>
									<ul>
										<li><p class="author author-info"><a href="#" class="author">John Maniya</a></p></li>
										<li class="right-list"><p class="views views-info">2,114,200 views</p></li>
									</ul>
								</div>
							</div>
							
							<div class="clearfix"> </div>
						</div>
					</div>
					<div class="recommended">
						<div class="recommended-grids">
							<div class="recommended-info">
								
								<div class="clearfix"> </div>
							</div>
							<div class="col-md-3 resent-grid recommended-grid movie-video-grid">
								<div class="resent-grid-img recommended-grid-img">
									<a href="single"><img src="images/ch1.jpg" alt="" /></a>
									<div class="time small-time show-time movie-time">
										<p>2:34</p>
									</div>
									<div class="clck movie-clock">
										<span class="glyphicon glyphicon-time" aria-hidden="true"></span>
									</div>
								</div>
								<div class="resent-grid-info recommended-grid-info recommended-grid-movie-info">
									<h5><a href="single" class="title">Varius sit sed viverra Nullam interdum metus</a></h5>
									<ul>
										<li><p class="author author-info"><a href="#" class="author">John Maniya</a></p></li>
										<li class="right-list"><p class="views views-info">2,114,200 views</p></li>
									</ul>
								</div>
							</div>
							<div class="col-md-3 resent-grid recommended-grid movie-video-grid">
								<div class="resent-grid-img recommended-grid-img">
									<a href="single"><img src="images/ch2.jpg" alt="" /></a>
									<div class="time small-time show-time movie-time">
										<p>3:45</p>
									</div>
									<div class="clck movie-clock">
										<span class="glyphicon glyphicon-time" aria-hidden="true"></span>
									</div>
								</div>
								<div class="resent-grid-info recommended-grid-info recommended-grid-movie-info">
									<h5><a href="single" class="title">Varius sit sed viverra Nullam interdum metus</a></h5>
									<ul>
										<li><p class="author author-info"><a href="#" class="author">John Maniya</a></p></li>
										<li class="right-list"><p class="views views-info">2,114,200 views</p></li>
									</ul>
								</div>
							</div>
							<div class="col-md-3 resent-grid recommended-grid movie-video-grid">
								<div class="resent-grid-img recommended-grid-img">
									<a href="single"><img src="images/ch3.jpg" alt="" /></a>
									<div class="time small-time show-time movie-time">
										<p>7:34</p>
									</div>
									<div class="clck movie-clock">
										<span class="glyphicon glyphicon-time" aria-hidden="true"></span>
									</div>
								</div>
								<div class="resent-grid-info recommended-grid-info recommended-grid-movie-info">
									<h5><a href="single" class="title">Varius sit sed viverra Nullam interdum metus</a></h5>
									<ul>
										<li><p class="author author-info"><a href="#" class="author">John Maniya</a></p></li>
										<li class="right-list"><p class="views views-info">2,114,200 views</p></li>
									</ul>
								</div>
							</div>
							<div class="col-md-3 resent-grid recommended-grid movie-video-grid">
								<div class="resent-grid-img recommended-grid-img">
									<a href="single"><img src="images/ch4.jpg" alt="" /></a>
									<div class="time small-time show-time movie-time">
										<p>6:30</p>
									</div>
									<div class="clck movie-clock">
										<span class="glyphicon glyphicon-time" aria-hidden="true"></span>
									</div>
								</div>
								<div class="resent-grid-info recommended-grid-info recommended-grid-movie-info">
									<h5><a href="single" class="title">Varius sit sed viverra Nullam interdum metus</a></h5>
									<ul>
										<li><p class="author author-info"><a href="#" class="author">John Maniya</a></p></li>
										<li class="right-list"><p class="views views-info">2,114,200 views</p></li>
									</ul>
								</div>
							</div>
							<div class="clearfix"> </div>
						</div>
					</div>
					<div class="recommended">
						<div class="recommended-grids">
							<div class="recommended-info">
								<div class="heading">
									<h3>My Playlists</h3>
								</div>
							
								<div class="clearfix"> </div>
							</div>
							<c:forEach var="clip" items="${playlist}">
					<div class="col-md-4 resent-grid recommended-grid slider-top-grids">
						<div class="resent-grid-img recommended-grid-img">
							<a href="playlist{id}"><img src="${playlist.getFirstClip.clipURL}" alt="" /></a>
							<div class="time">
								<p>3:04</p>
							</div>
							<div class="clck">
								<span class="glyphicon glyphicon-time" aria-hidden="true"></span>
							</div>
						</div>
						<div class="resent-grid-info recommended-grid-info">
							<h3><a href="single.html" class="title title-info">${playlist.name}</a></h3>
							<ul>
								<li><p class="author author-info"><a href="#" class="author"> ${playlist.owner.fullName}</a></p></li>
								<li class="right-list"><p class="views views-info"> ${playlist.viewsOfPlaylist} views</p></li>
							</ul>
						</div>
					</div>
					</c:forEach>
							<div class="col-md-3 resent-grid recommended-grid movie-video-grid">
								<div class="resent-grid-img recommended-grid-img">
									<a href="single"><img src="images/h2.jpg" alt="" /></a>
									<div class="time small-time show-time movie-time">
										<p>3:45</p>
									</div>
									<div class="clck movie-clock">
										<span class="glyphicon glyphicon-time" aria-hidden="true"></span>
									</div>
								</div>
								<div class="resent-grid-info recommended-grid-info recommended-grid-movie-info">
									<h5><a href="single" class="title">Varius sit sed viverra Nullam interdum metus</a></h5>
									<ul>
										<li><p class="author author-info"><a href="#" class="author">John Maniya</a></p></li>
										<li class="right-list"><p class="views views-info">2,114,200 views</p></li>
									</ul>
								</div>
							</div>
							<div class="col-md-3 resent-grid recommended-grid movie-video-grid">
								<div class="resent-grid-img recommended-grid-img">
									<a href="single"><img src="images/h3.jpg" alt="" /></a>
									<div class="time small-time show-time movie-time">
										<p>7:34</p>
									</div>
									<div class="clck movie-clock">
										<span class="glyphicon glyphicon-time" aria-hidden="true"></span>
									</div>
								</div>
								<div class="resent-grid-info recommended-grid-info recommended-grid-movie-info">
									<h5><a href="single" class="title">Varius sit sed viverra Nullam interdum metus</a></h5>
									<ul>
										<li><p class="author author-info"><a href="#" class="author">John Maniya</a></p></li>
										<li class="right-list"><p class="views views-info">2,114,200 views</p></li>
									</ul>
								</div>
							</div>
							<div class="col-md-3 resent-grid recommended-grid movie-video-grid">
								<div class="resent-grid-img recommended-grid-img">
									<a href="single"><img src="images/h4.jpg" alt="" /></a>
									<div class="time small-time show-time movie-time">
										<p>6:30</p>
									</div>
									<div class="clck movie-clock">
										<span class="glyphicon glyphicon-time" aria-hidden="true"></span>
									</div>
								</div>
								<div class="resent-grid-info recommended-grid-info recommended-grid-movie-info">
									<h5><a href="single" class="title">Varius sit sed viverra Nullam interdum metus</a></h5>
									<ul>
										<li><p class="author author-info"><a href="#" class="author">John Maniya</a></p></li>
										<li class="right-list"><p class="views views-info">2,114,200 views</p></li>
									</ul>
								</div>
							</div>
							<div class="clearfix"> </div>
						</div>
					</div>
					<div class="recommended">
						<div class="recommended-grids">
							<div class="recommended-info">
								<div class="clearfix"> </div>
							</div>
							<div class="col-md-3 resent-grid recommended-grid movie-video-grid">
								<div class="resent-grid-img recommended-grid-img">
									<a href="single"><img src="images/tm1.jpg" alt="" /></a>
									<div class="time small-time show-time movie-time">
										<p>5:32</p>
									</div>
									<div class="clck movie-clock">
										<span class="glyphicon glyphicon-time" aria-hidden="true"></span>
									</div>
								</div>
								<div class="resent-grid-info recommended-grid-info recommended-grid-movie-info">
									<h5><a href="single" class="title">Varius sit sed viverra Nullam interdum metus</a></h5>
									<ul>
										<li><p class="author author-info"><a href="#" class="author">John Maniya</a></p></li>
										<li class="right-list"><p class="views views-info">2,114,200 views</p></li>
									</ul>
								</div>
							</div>
							<div class="col-md-3 resent-grid recommended-grid movie-video-grid">
								<div class="resent-grid-img recommended-grid-img">
									<a href="single"><img src="images/tm2.jpg" alt="" /></a>
									<div class="time small-time show-time movie-time">
										<p>2:34</p>
									</div>
									<div class="clck movie-clock">
										<span class="glyphicon glyphicon-time" aria-hidden="true"></span>
									</div>
								</div>
								<div class="resent-grid-info recommended-grid-info recommended-grid-movie-info">
									<h5><a href="single" class="title">Varius sit sed viverra Nullam interdum metus</a></h5>
									<ul>
										<li><p class="author author-info"><a href="#" class="author">John Maniya</a></p></li>
										<li class="right-list"><p class="views views-info">2,114,200 views</p></li>
									</ul>
								</div>
							</div>
							<div class="col-md-3 resent-grid recommended-grid movie-video-grid movie-video-grid-left">
								<div class="resent-grid-img recommended-grid-img">
									<a href="single"><img src="images/tm3.jpg" alt="" /></a>
									<div class="time small-time show-time movie-time">
										<p>8:26</p>
									</div>
									<div class="clck movie-clock">
										<span class="glyphicon glyphicon-time" aria-hidden="true"></span>
									</div>
								</div>
								<div class="resent-grid-info recommended-grid-info recommended-grid-movie-info">
									<h5><a href="single" class="title">Varius sit sed viverra Nullam interdum metus</a></h5>
									<ul>
										<li><p class="author author-info"><a href="#" class="author">John Maniya</a></p></li>
										<li class="right-list"><p class="views views-info">2,114,200 views</p></li>
									</ul>
								</div>
							</div>
							<div class="col-md-3 resent-grid recommended-grid movie-video-grid movie-video-grid-right">
								<div class="resent-grid-img recommended-grid-img">
									<a href="single"><img src="images/tm4.jpg" alt="" /></a>
									<div class="time small-time show-time movie-time">
										<p>3:44</p>
									</div>
									<div class="clck movie-clock">
										<span class="glyphicon glyphicon-time" aria-hidden="true"></span>
									</div>
								</div>
								<div class="resent-grid-info recommended-grid-info recommended-grid-movie-info">
									<h5><a href="single" class="title">Varius sit sed viverra Nullam interdum metus</a></h5>
									<ul>
										<li><p class="author author-info"><a href="#" class="author">John Maniya</a></p></li>
										<li class="right-list"><p class="views views-info">2,114,200 views</p></li>
									</ul>
								</div>
							</div>
							<div class="clearfix"> </div>
						</div>
					</div>
				</div>
				<div class="clearfix"> </div>
			</div>
			<!-- footer -->
			<%@ include file="/static/footer.html" %>
			<!-- //footer -->
		</div>
		<div class="clearfix"> </div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
  </body>
</html>