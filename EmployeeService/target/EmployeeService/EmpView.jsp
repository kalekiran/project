<%@page import="java.util.List"%>
<%@page import="com.myapp.servlets.EmpServlet"%>
<%@page import="com.myapp.model.Employee"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee List</title>
<style>
body {
	background-color: #FFE5FF;
}

table {
	width: 70%;
}

th {
	text-align: left;
	background-color: #D2A477;
	height: 30px;
}

table,th,td {
	border: 1px solid black;
	border-collapse: collapse;
}

th,td {
	padding: 10px;
}
</style>
</head>

<body>
	<h2>Employee Grid</h2>
	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Salary</th>
			<th>Department</th>
			<th>Address</th>
			<th>Action</th>
		</tr>

		<%
			List<Employee> empList = (List<Employee>) request.getAttribute("EmployeeList");
			for (Employee emp : empList) {
		%>
		<tr>
			<td><%=emp.getEmpId()%></td>
			<td><%=emp.getEmpName()%></td>
			<td><%=emp.getSalary()%></td>
			<td><%=emp.getDepartment()%></td>
			<td><%=emp.getAddress()%></td>
			<td><a href="EmpServlet?action=edit&empId= <%=emp.getEmpId()%> ">
					Update</a>&nbsp;| &nbsp; <a
				href="EmpServlet?action=delete&empId= <%=emp.getEmpId()%>">
					Delete</a></td>
		</tr>
		<%
			}
		%>
	</table>
	<h3>
		<a href="EmpServlet?action=insert">Add Employee</a>
	</h3>
</body>
</html>