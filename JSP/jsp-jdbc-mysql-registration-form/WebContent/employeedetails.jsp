<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@page import="proj.employeeregistration.database.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

 <jsp:useBean id="employee"
  class="proj.employeeregistration.bean.Employee" />

 <jsp:setProperty property="*" name="employee" />

 <%
  EmployeeDao employeeDao = new EmployeeDao();
  int status = employeeDao.registerEmployee(employee);
  if (status > 0) {
   out.print("You are successfully registered");
  }
 %>
</body>
</html>