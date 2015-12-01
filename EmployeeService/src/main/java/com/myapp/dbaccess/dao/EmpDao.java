package com.myapp.dbaccess.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.myapp.model.Employee;
import com.myapp.util.HibernateUtil;

public class EmpDao {
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@SuppressWarnings("unchecked")
	public static List<Employee> getAllEmployees() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Employee> result = new ArrayList<Employee>();
		Query query = session.createQuery("from " + "Employee");
		result = query.list();
		session.close();
		return result;
	}

	public static Employee getEmpById(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Employee emp = (Employee) session.get(Employee.class, id);
		session.close();
		return emp;
	}

	public static void deleteEmployeeById(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Employee emp = (Employee) session.get(Employee.class, id);
		session.delete(emp);
		session.getTransaction().commit();
		session.close();
	}

	public static void updateEmployee(Employee newEmp) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		int newEmpId = newEmp.getEmpId();
		Employee existingEmp = (Employee) session.get(Employee.class, newEmpId);
		newEmp = Employee.shallowMergeUpdate(existingEmp, newEmp);
		session.update(newEmp);
		session.getTransaction().commit();
		session.close();
	}

	public static void insertEmployee(Employee emp) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(emp);
		session.getTransaction().commit();
		session.close();
	}

}
