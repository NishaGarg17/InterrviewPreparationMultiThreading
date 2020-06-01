package com.interview.java.multithreading;


public class ThreadExample {
	
	public static void main(String[] args) {
		MyThread1 t1 = new MyThread1();
		System.out.println("Going to start a thread1: ");
		t1.start();
		
		MyThread2 t2 = new MyThread2();
		System.out.println("Going to start a thread2: ");
		Thread t = new Thread(t2);
		t.start();
		System.out.println("Finishing Main Thread");
		
		// let's start thread1 again, starting a thread again will throw IllegalThreadStateException
		// t1.start();
	}
	
	
	public static class MyThread1 extends Thread {
		
		@Override
		public void run() {
			System.out.println("Inside MyThread1 extending Thread class.");
		}
	}
	
	public static class MyThread2 implements Runnable {

		@Override
		public void run() {
			System.out.println("Inside MyThread2 implementing Runnable interface");
		}
		
	}
	
}