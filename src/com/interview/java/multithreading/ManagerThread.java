package com.interview.java.multithreading;

public class ManagerThread {
	String managerName;
	String managerId;
	
	ManagerThread(String name, String id) {
		managerName = name;
		managerId = id;
	}
	
	public void printManagerDetails(String callingThreadName) {
		System.out.println(callingThreadName + " is printing manager details: " + this.toString());
	}

	@Override
	public String toString() {
		return "ManagerThread [managerName=" + managerName + ", managerId=" + managerId + "]";
	}
}
