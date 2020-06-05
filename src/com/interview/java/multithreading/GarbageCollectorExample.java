package com.interview.java.multithreading;

public class GarbageCollectorExample {

	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("Garbage Collected");
	}

	public static void main(String[] args) {
		Object obj;
		GarbageCollectorExample obj1 = new GarbageCollectorExample();
		GarbageCollectorExample obj2 = new GarbageCollectorExample();
		
		System.out.println(obj1 + " and " + obj2 + " two objects have been created successfully");
		
		obj1 = obj2 = null;
		
		System.gc();
		
		/*
		 * System.out.println("Object1 is: " + obj1); System.out.println("Object2 is: "
		 * + obj2);
		 */
		
	}

}
