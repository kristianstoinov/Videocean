<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Clients Page</title>
</head>
<body>
<p>Gosho</p>
<a href="./clients/5">Show details for Gosho</a>

<springForm:form method="post" commandName="client">
<label>Enter client's name:</label>
<springForm:input type="text" path="name"/>
<br/>

<label>Enter client's salary:</label>
<springForm:input type="text" path="salary" />

<br/>

<input type="submit" value="Add new client"/>
</springForm:form>

</body>
</html>