package com.myapp.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.myapp.dbaccess.dao.EmpDao;
import com.myapp.model.Employee;
import com.myapp.servlets.EmpServlet;

public class TestEmpServletWorkflow2{

	private EmpServlet servInstance;

	@Before
	public void setUp() throws Exception {
		servInstance = new EmpServlet();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testViewEmployees() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher reqDispatcher = mock(RequestDispatcher.class);

		when(request.getParameter("action")).thenReturn("listEmp");
		when(request.getRequestDispatcher("/EmpView.jsp")).thenReturn(reqDispatcher);
		try {
			servInstance.doGet(request, response);
		} catch (ServletException e) {
			System.out.println("Exception occured while invoking servlet." + e.getMessage());
		} catch (IOException e) {
			System.out.println("Exception occured while doing I/O opearation." + e.getMessage());
		}

		/*List<Employee> returnedList = (List<Employee>) request.getAttribute("EmployeeList");
		assertEquals(returnedList, EmpDao.getAllEmployees());*/

	}

	@Test
	public void testInsertEmployee() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher reqDispatcher = mock(RequestDispatcher.class);

		List<Employee> empList = EmpDao.getAllEmployees();
		int initialListSize = empList.size();
		Employee customEmp = new Employee("SampleJunit", "1000", "Pune", "IT");

		when(request.getRequestDispatcher("/EmpView.jsp")).thenReturn(reqDispatcher);
		when(request.getParameter("empName")).thenReturn(customEmp.getEmpName());
		when(request.getParameter("salary")).thenReturn(customEmp.getSalary());
		when(request.getParameter("address")).thenReturn(customEmp.getAddress());
		when(request.getParameter("department")).thenReturn(customEmp.getDepartment());
		try {
			servInstance.doPost(request, response);
		} catch (ServletException e) {
			System.out.println("Exception occured while invoking servlet." + e.getMessage());
		} catch (IOException e) {
			System.out.println("Exception occured while doing I/O opearation." + e.getMessage());
		}

		List<Employee> empListPostInsert = EmpDao.getAllEmployees();
		Assert.assertNotNull(empListPostInsert);
		int listSizePostInsert = empListPostInsert.size();
		Employee newEmp = empListPostInsert.get(listSizePostInsert - 1);
		assertEquals(initialListSize + 1, listSizePostInsert);
		assertTrue(customEmp.equals(newEmp));
	}

	@Test
	public void testUpdateEmployee() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher reqDispatcher = mock(RequestDispatcher.class);

		List<Employee> empList = EmpDao.getAllEmployees();
		Employee emp = empList.get(empList.size() - 1);
		emp.setEmpName("NewJunit");
		emp.setSalary("2000");
		emp.setAddress("Mumbai");

		when(request.getRequestDispatcher("/EmpView.jsp")).thenReturn(reqDispatcher);
		when(request.getParameter("empId")).thenReturn(new Integer(emp.getEmpId()).toString());
		when(request.getParameter("empName")).thenReturn(emp.getEmpName());
		when(request.getParameter("salary")).thenReturn(emp.getSalary());
		when(request.getParameter("address")).thenReturn(emp.getAddress());
		when(request.getParameter("department")).thenReturn(emp.getDepartment());
		try {
			servInstance.doPost(request, response);
		} catch (ServletException e) {
			System.out.println("Exception occured while invoking servlet." + e.getMessage());
		} catch (IOException e) {
			System.out.println("Exception occured while doing I/O opearation." + e.getMessage());
		}

		Employee updatedEmp = EmpDao.getEmpById(emp.getEmpId());
		assertTrue(updatedEmp.equals(emp));
	}

	@Test
	public void testDeleteEmployee() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher reqDispatcher = mock(RequestDispatcher.class);

		List<Employee> empList = EmpDao.getAllEmployees();
		int initialListSize = empList.size();
		Employee sampleEmp = empList.get(empList.size() - 1);

		when(request.getParameter("action")).thenReturn("delete");
		when(request.getParameter("empId")).thenReturn(new Integer(sampleEmp.getEmpId()).toString());
		when(request.getRequestDispatcher("/EmpView.jsp")).thenReturn(reqDispatcher);
		try {
			servInstance.doGet(request, response);
		} catch (ServletException e) {
			System.out.println("Exception occured while invoking servlet." + e.getMessage());
		} catch (IOException e) {
			System.out.println("Exception occured while doing I/O opearation." + e.getMessage());
		}

		List<Employee> empListPostDelete = EmpDao.getAllEmployees();
		int listSizePostDelete = empListPostDelete.size();
		Assert.assertEquals(initialListSize - 1, listSizePostDelete);
		Employee emp = EmpDao.getEmpById(sampleEmp.getEmpId());
		assertNull(emp);
	}

}
