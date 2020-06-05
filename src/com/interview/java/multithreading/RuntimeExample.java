package com.interview.java.multithreading;

import java.io.IOException;

public class RuntimeExample {

	public static void main(String[] args) {
		Runtime rtObj = Runtime.getRuntime();
		
		// to open a notepad using Runtime class
		try {
			rtObj.exec("notepad");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Total memory allocated to JVM
		System.out.println("Total Memory: " + rtObj.totalMemory());
		
		// Free Available memory
		System.out.println("Free Memory: " + rtObj.freeMemory());
	}

}
