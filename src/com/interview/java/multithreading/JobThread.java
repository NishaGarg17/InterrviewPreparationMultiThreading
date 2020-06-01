package com.interview.java.multithreading;

public class JobThread implements Runnable{
	int job;
	public JobThread(int job) {
		this.job = job;
	}
	
	@Override
	public void run() {
		System.out.println("Worker Thread: " + Thread.currentThread().getName() + " executing the Runnable Thread: " + job);
		try {
			Thread.sleep(10000);
		} catch(InterruptedException ex) {
			System.out.println(ex.getMessage());
		}
		System.out.println(Thread.currentThread().getName() + " End.");
	}
	
	
}
