package com.interview.java.multithreading.threadsafety;

public class DeadlockExample {
	public static void main(String args[]) {
		Message message = new Message();
		Add addObj = new Add();
		SyncThread1 sThread1 = new SyncThread1(message, addObj);
		SyncThread2 sThread2 = new SyncThread2(message, addObj);
		Thread thread1 = new Thread(sThread1);
		Thread thread2 = new Thread(sThread2);

		System.out.println(thread1.getName() + " is going to start");
		thread1.start();
		System.out.println(thread2.getName() + " is going to start");
		thread2.start();

		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Both threads: " + thread1.getName() + " and " + thread2.getName() + " has finished their tasks");
		System.out.println("Main ends");

	}
}

class SyncThread1 implements Runnable {
	Message message;
	Add addObj;

	SyncThread1(Message message, Add addObj) {
		this.message = message;
		this.addObj = addObj;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " started");
		System.out.println(Thread.currentThread().getName() + " is going to accquire lock on: " + message);
		synchronized (message) {
			message.message(Thread.currentThread().getName() + " accquired lock on me: ");
			work();
			System.out.println(Thread.currentThread().getName() + " is going to accquire lock on: " + addObj);
			synchronized (addObj) {
				System.out.println(Thread.currentThread().getName() + " accquired lock on me: " + addObj);
				addObj.printAddition();
				System.out.println(Thread.currentThread().getName() + " released lock on: " + addObj);
			}
			System.out.println(Thread.currentThread().getName() + " released lock on: " + message);
		}

	}

	public void work() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException ex) {
			System.out.println(ex.getMessage());
		}
	}

}

class SyncThread2 implements Runnable {
	Message message;
	Add addObj;

	SyncThread2(Message message, Add addObj) {
		this.message = message;
		this.addObj = addObj;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " started");
		System.out.println(Thread.currentThread().getName() + " is going to accquire lock on: " + addObj);
		synchronized (addObj) {
			System.out.println(Thread.currentThread().getName() + " accquired lock on me: " + addObj);
			addObj.printAddition();
			
			System.out.println(Thread.currentThread().getName() + " is going to accquire lock on: " + message);
			synchronized (message) {
				message.message(Thread.currentThread().getName() + " accquired lock on me: ");
				work();
				System.out.println(Thread.currentThread().getName() + " released lock on: " + message);
				
			}
			System.out.println(Thread.currentThread().getName() + " released lock on: " + addObj);
		}

	}

	public void work() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException ex) {
			System.out.println(ex.getMessage());
		}
	}

}
class Message {
	public void message(String message) {
		System.out.println(message + this);
	}
}

class Add {
	public void printAddition() {
		int sum = 0;
		for (int i = 1; i <= 5; i++) {
			sum = sum + i;
		}
		System.out.println("Sum is: " + sum);
	}
}