package com.interview.java.multithreading;

public class DaemonThreadExample {
	static int i = 1;
	public static void main(String[] args) {
		DaemonThread dt = new DaemonThread();
		dt.setName("DaemonThread-0");
		
		// set thread as daemon thread
		dt.setDaemon(true);
		
		// to check daemon thread priority
		System.out.println("Daemon Thread priority: " + dt.getPriority());
		System.out.println("Going to start a daemon Thread");
		dt.start();
	
		System.out.println("Main is going to end");
	}
	
	public static class DaemonThread extends Thread {
		@Override
		public void run() {
			while(true) {
				doSomething();
			}
		}
		
		public static void doSomething() {
			System.out.println(Thread.currentThread().getName() + " " + i++);
		}
	}
	

}
