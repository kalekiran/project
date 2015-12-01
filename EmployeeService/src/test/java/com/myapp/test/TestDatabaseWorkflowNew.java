package com.myapp.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.myapp.dbaccess.dao.EmpDao;
import com.myapp.model.Employee;

public class TestDatabaseWorkflowNew {

	@Before
	public void setUp() throws Exception {
		Employee emp = new Employee("AK", "15000", "Mumbai", "IT ");
		Employee empNew = new Employee("KK", "10000", "Pune", "IT ");
		EmpDao.insertEmployee(emp);
		EmpDao.insertEmployee(empNew);
	}

	@Test
	public void testInsertEmployee() {
		List<Employee> empList = EmpDao.getAllEmployees();
		Assert.assertNotNull(empList);
		int initialListSize = empList.size();
		Employee emp = new Employee("SK", "20000", "Pune", "IT ");
		EmpDao.insertEmployee(emp);
		int listSizePostInsert = EmpDao.getAllEmployees().size();
		Assert.assertEquals(initialListSize + 1, listSizePostInsert);
	}

	@Test
	public void testDeleteEmployee() {
		List<Employee> empList = EmpDao.getAllEmployees();
		Assert.assertNotNull(empList);
		int initialListSize = empList.size();
		EmpDao.deleteEmployeeById(empList.get(0).getEmpId());
		int listSizePostDelete = EmpDao.getAllEmployees().size();
		Assert.assertEquals(initialListSize, listSizePostDelete + 1);
		Employee emp = EmpDao.getEmpById(4);
		Assert.assertNull(emp);
	}

	@Test
	public void testUpdateEmployee() {
		List<Employee> empList = EmpDao.getAllEmployees();
		Employee emp = empList.get(0);
		Assert.assertNotNull(emp);
		String initialEmpName = emp.getEmpName();
		emp.setEmpName("NewName");
		EmpDao.updateEmployee(emp);
		Employee updatedEmp = EmpDao.getEmpById(emp.getEmpId());
		Assert.assertNotSame(initialEmpName, updatedEmp.getEmpName());
	}

	@Test
	public void testListEmployees() {
		List<Employee> empList = EmpDao.getAllEmployees();
		Assert.assertNotNull(empList);
		Assert.assertTrue(empList.size() > 0);
	}

}
