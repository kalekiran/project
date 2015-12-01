package com.myapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Eid")
	private int empId;

	@Column(name = "Ename")
	private String empName;

	@Column(name = "Salary")
	private String salary;

	@Column(name = "Department")
	private String department;

	@Column(name = "Address")
	private String address;

	public Employee() {
	}

	Employee(String empName) {
		this.empName = empName;

	}

	public Employee(String empName, String salary, String address, String department) {
		this.empName = empName;
		this.salary = salary;
		this.department = department;
		this.address = address;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public static Employee shallowMergeUpdate(Employee destination, Employee source) {

		if (source.getEmpName() != null) {
			destination.setEmpName(source.getEmpName());
		}
		if (source.getSalary() != null) {
			destination.setSalary(source.getSalary());
		}
		if (source.getAddress() != null) {
			destination.setAddress(source.getAddress());
		}

		if (source.getDepartment() != null) {
			destination.setDepartment(source.getDepartment());
		}
		return destination;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", salary=" + salary + ", department="
				+ department + ", address=" + address + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((empName == null) ? 0 : empName.hashCode());
		result = prime * result + ((salary == null) ? 0 : salary.hashCode());
		return result;
	}

	public boolean equals(Employee obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (empName == null) {
			if (other.empName != null)
				return false;
		} else if (!empName.equals(other.empName))
			return false;
		if (salary == null) {
			if (other.salary != null)
				return false;
		} else if (!salary.equals(other.salary))
			return false;
		return true;
	}

}
