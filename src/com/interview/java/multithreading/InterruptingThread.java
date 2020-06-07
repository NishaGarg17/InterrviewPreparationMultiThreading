package com.interview.java.multithreading;

public class InterruptingThread {

	public static void main(String[] args) {
		MyThread thread = new MyThread();
		thread.start();
		
		System.out.println("Going to interrupt: " + thread.getName());
		thread.interrupt();
	}
	
	public static class MyThread extends Thread{
		public void run() {
			System.out.println(Thread.currentThread().getName() + " started");
			
			try {
				Thread.sleep(5000);
			} catch (Exception ex) {
				System.out.println(ex);
				System.out.println("Is Thread: " + Thread.currentThread().getName() + " interrupted?: " + Thread.currentThread().isInterrupted());
			}
			System.out.println(Thread.interrupted());
			System.out.println(Thread.currentThread().isInterrupted());
			if(Thread.currentThread().isInterrupted()) {
				System.out.println(Thread.currentThread() + " has been interrupted");
			}
		}
	}

}
