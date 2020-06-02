package com.interview.java.multithreading;

public class EmployeeThread {
	String employeeName;
	String employeeId;
	EmployeeThread(String empName, String empId) {
		employeeName = empName;
		employeeId = empId;
	}
	
	public void printEmployeeDetails(String callingThreadName) {
		System.out.println(callingThreadName + " is printing employee details: " + this.toString());
	}

	@Override
	public String toString() {
		return "EmployeeThread [employeeName=" + employeeName + ", employeeId=" + employeeId + "]";
	}
	
	
}
