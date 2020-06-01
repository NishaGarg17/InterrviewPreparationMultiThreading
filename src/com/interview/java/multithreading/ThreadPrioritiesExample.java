package com.interview.java.multithreading;


public class ThreadPrioritiesExample {
	
	public static void main(String[] args) {
		MyThread1 t1 = new MyThread1();
		System.out.println("Going to start a thread1: ");
		t1.setPriority(Thread.MIN_PRIORITY);
		
		MyThread2 t2 = new MyThread2();
		System.out.println("Going to start a thread2: ");
		Thread t = new Thread(t2);
		t.setPriority(Thread.MAX_PRIORITY);
		
		t.start();
		t1.start();
		
		
		System.out.println("Finishing Main Thread");
		
		// let's start thread1 again, starting a thread again will throw IllegalThreadStateException
		// t1.start();
	}
	
	
	public static class MyThread1 extends Thread {
		
		@Override
		public void run() {
			for(int i = 1; i <= 5; i++) {
				System.out.println(Thread.currentThread().getName() + " " + i);
			}
		}
	}
	
	public static class MyThread2 implements Runnable {

		@Override
		public void run() {
			for(int i = 6; i <= 10; i++) {
				System.out.println(Thread.currentThread().getName() + " " + i);
			}
		}
		
	}
	
}