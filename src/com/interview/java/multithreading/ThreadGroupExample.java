package com.interview.java.multithreading;

public class ThreadGroupExample {

	public static void main(String[] args) {
		ThreadGroup tg = new ThreadGroup("MyThreadGroup");
		
		MyThread runnable = new MyThread();
		
		Thread thread1 = new Thread(tg, runnable);
		Thread thread2 = new Thread(tg, runnable);
		Thread thread3 = new Thread(tg, runnable);
		
		thread1.start();
		thread2.start();
		thread3.start();
		
		System.out.println("Thread Group Name is: " + tg.getName());
		tg.list();
	}
	
	static class MyThread implements Runnable{

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName());
		}
		
	}

}


