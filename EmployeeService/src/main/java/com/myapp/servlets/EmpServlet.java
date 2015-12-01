package com.myapp.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.dbaccess.dao.EmpDao;
import com.myapp.model.Employee;

/**
 * Servlet implementation class EmpServlet
 */
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String EDIT_EMP = "/UpdateEmp.jsp";
	private static String INSERT_EMP = "/InsertEmp.jsp";
	private static String VIEW_EMP = "/EmpView.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String forward = "";
		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("delete")) {
			int empId = Integer.parseInt(request.getParameter("empId").trim());
			EmpDao.deleteEmployeeById(empId);
			forward = VIEW_EMP;
			request.setAttribute("EmployeeList", EmpDao.getAllEmployees());
		} else if (action.equalsIgnoreCase("edit")) {
			forward = EDIT_EMP;
			int empId = Integer.parseInt(request.getParameter("empId").trim());
			Employee emp = EmpDao.getEmpById(empId);
			request.setAttribute("Employee", emp);
		} else if (action.equalsIgnoreCase("listEmp")) {
			forward = VIEW_EMP;
			List<Employee> empList = EmpDao.getAllEmployees();
			request.setAttribute("EmployeeList", empList);
		} else {
			forward = INSERT_EMP;
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Employee emp = new Employee();
		emp.setEmpName(request.getParameter("empName"));
		emp.setSalary(request.getParameter("salary"));
		emp.setAddress(request.getParameter("address"));
		emp.setDepartment(request.getParameter("department"));

		String empId = request.getParameter("empId");
		if (empId == null || empId.isEmpty()) {
			EmpDao.insertEmployee(emp);

		} else {
			emp.setEmpId(Integer.parseInt(empId.trim()));
			EmpDao.updateEmployee(emp);
		}

		
		RequestDispatcher view = request.getRequestDispatcher(VIEW_EMP);
		request.setAttribute("EmployeeList", EmpDao.getAllEmployees());
		view.forward(request, response);
		response.sendRedirect(VIEW_EMP);
	}

}
