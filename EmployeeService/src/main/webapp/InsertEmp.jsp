<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
body{
background-color: #FFE5FF;
}

.container
{
	width:250px;
	display: inline-block;
	position: absolute;
    top: 0;
    right: 0;
    left: 0;
    bottom: 0;
    margin: 0 auto;
}

.container ul li
{
	line-height: 35px;
	list-style-type: none;
}

.left-con{
	width:80px;
	display: inline-block;
	text-align: right;
}

.right-con{
	width:80px;
	display: inline-block;
}


</style>
</head>
<body>
  <form method="POST" action="EmpServlet" name="frmAddEmp">
  		<div class="container">
  		 <h3>Add Employee : </h3>
  			<ul>
	  			<li>
	  				<div class="left-con"><label>Name : </label> </div>
		  			<div class="right-con"><input type="text" name="empName" value="" />  </div>
	  			</li>
	  			<li>
	  				<div class="left-con"><label>Salary : </label> </div>
		  			<div class="right-con"><input type="text" name="salary" value="" /></div>
	  			</li>
	  			
	  			<li>
	  				<div class="left-con"><label>Dept. : </label> </div>
		  			<div class="right-con"><input type="text" name="department" value="" /></div>
	  			</li>
	  			
	  			<li>
	  				<div class="left-con"><label>Address : </label> </div>
		  			<div class="right-con"><input type="text" name="address" value="" /> </div>
	  			</li>
	  			<li> 
	  			    <div class="left-con"><label>   </label> </div>
	  			    <div class="right-con"> <input type="submit" value="Submit" /> </div>
	  			</li>
	  		</ul>
  		</div>
 </form>
</body>
</html>