package com.interview.java.multithreading;

public class ShutDownHookExample {

	public static void main(String[] args) {
		Runtime runtime = Runtime.getRuntime();
		
		runtime.addShutdownHook(new MyThread());
		
		System.out.println("Now main sleeping... press ctrl+c to exit");
		try {
			Thread.sleep(5000);
			System.out.println("Main sleep completed...");
		} catch(InterruptedException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static class MyThread extends Thread{
		public void run () {
			System.out.println("shut down hook task completed..");
		}
	}

}
