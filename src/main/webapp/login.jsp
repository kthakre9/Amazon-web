<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Login Page</title>
</head>

<style>
div
{
background-color:#006699
}
</style>

<body>


<form action="login.htm" method="get" name="Login" >

<div>

	<center><h1> Welcome To AMAZON</h1></center>
	
	<center><h3> Please enter your login details</h3></center>
	
  <center><label>User Name &nbsp;
  <input type="text" name="userName" />
  </label></center>
  


  <p>
    <center><label>Password &nbsp;&nbsp;&nbsp;
    <input type="password" name="password" />
    </label></center>
  </p>
  <p>
    <center><label>
    <input type="submit" name="Submit" value="Log In" />
    </label></center>
  </p>
  <a href="addUserAccount.jsp">Click to register</a>
  </div>
</form>
</body>
</html>
