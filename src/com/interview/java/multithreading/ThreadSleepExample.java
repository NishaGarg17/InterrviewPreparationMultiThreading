package com.interview.java.multithreading;

public class ThreadSleepExample {
	
	public static void main(String[] args) {
		MyThread1 t1 = new MyThread1();
		System.out.println("Going to start a thread1: ");
		t1.start();
		
		MyThread2 t2 = new MyThread2();
		System.out.println("Going to start a thread2: ");
		Thread t = new Thread(t2);
		t.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Finishing Main Thread");
	}
	
	
	public static class MyThread1 extends Thread {
		
		@Override
		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("Inside MyThread1 extending Thread class.");
		}
	}
	
	public static class MyThread2 implements Runnable {

		@Override
		public void run() {
			long start = System.currentTimeMillis();
			try {
				
				System.out.println("Thread2 is going to sleep and system time is now: " + start);
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("Thread2 sleep time is over and total sleep time was: " + (System.currentTimeMillis() - start));
			System.out.println("Inside MyThread2 implementing Runnable interface");
		}
		
	}
	
}