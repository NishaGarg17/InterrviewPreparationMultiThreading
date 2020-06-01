package com.interview.java.multithreading;

public class ThreadJoinExample {

	public static void main(String[] args) {
		MyThread1 t1 = new MyThread1();
		MyThread2 t2 = new MyThread2();
		
		System.out.println("Thread1 is going to start");
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("Main is going to start Thread2 only after the complete execution of Thread1");
		t2.start();
		
		System.out.println("Main Thread ends");
	}
	
	public static class MyThread1 extends Thread {
		public void run() {
			for(int i = 0; i < 10; i++) {
				if(i % 2 != 0) {
					System.out.println(Thread.currentThread().getName() + " " + i);
				}
			}
		}
	}
	
	public static class MyThread2 extends Thread{
		public void run() {
			for(int i = 0; i < 10; i++) {
				if(i % 2 == 0) {
					System.out.println(Thread.currentThread().getName() + " " + i);
				}
			}
		}
	}

}

