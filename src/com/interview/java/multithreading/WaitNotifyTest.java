package com.interview.java.multithreading;

public class WaitNotifyTest {

	public static void main(String[] args) {
		Message message = new Message("Hello World!");
		Student stu1 = new Student("Nisha","10301150");
		Student stu2 = new Student("Komal", "11301144");
		Waiter waiter1 = new Waiter(message, stu1);
		new Thread(waiter1, "Waiter1").start();

		Waiter waiter2 = new Waiter(message, stu2);

		new Thread(waiter2, "Waiter2").start();

		Notifier notifier = new Notifier(message);
		new Thread(notifier, "Notifier").start();
		System.out.println("All the threads have been started");
	}

	public static class Message {
		private String msg;

		public Message(String msg) {
			this.msg = msg;
		}

		public String getMessage() {
			return msg;
		}

		public void setMessage(String msg) {
			this.msg = msg;
		}
	}

	public static class Waiter implements Runnable {
		private Message message;
		private Student student;
		public Waiter(Message msg, Student student) {
			message = msg;
			this.student = student;
		}

		@Override
		public void run() {
			System.out.println("Waiter: " + Thread.currentThread().getName() + " started at: "
					+ System.currentTimeMillis() + " and waiting to get notified");
			long waitStartTime = 0;
			long waitOverTime;
			synchronized (message) {
				try {
					// waiting for the access on message object
					waitStartTime = System.currentTimeMillis();
					message.wait(1000);
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
				// will print only if wait for Message thread would be over 
				waitOverTime = System.currentTimeMillis();
				System.out.println("Waiter: " + Thread.currentThread().getName()
						+ " has been notified and wait for Message is over at: " + waitOverTime);
				System.out.println("Duration is: " + (waitOverTime - waitStartTime));
				System.out.println(Thread.currentThread().getName() + " processed messsage: " + message.getMessage());
			}
			System.out.println(Thread.currentThread().getName() + " Going to accquire lock on: " + student);
			synchronized (student) {
				System.out.println(Thread.currentThread().getName() + " Accquired lock at: " + student);
				student.printStudentDetails(Thread.currentThread().getName());
			}
			System.out.println(Thread.currentThread().getName() + " End Statement");

		}
	}

	public static class Notifier implements Runnable {
		private Message message;

		public Notifier(Message msg) {
			message = msg;
		}

		@Override
		public void run() {

			System.out.println("Notifier: " + Thread.currentThread().getName() + " started at: "
					+ System.currentTimeMillis() + " going to notify waiting threads");

			
			synchronized (message) {
				// if more than one threads waiting for access on message object than program
				// will not be terminated as notify will notify only one waiting thread and
				// that's depends upon OS implementation of thread management.
				// only one thread notified and remaining waiting threads remains in wait state
				// and programs will not be terminated.
				// message.notify();
				try {
					Thread.sleep(5000);
				} catch(Exception ex) {
					System.out.println(ex.getMessage());
				}
				
				message.notifyAll();
				try {
					Thread.sleep(5000);
				} catch(Exception ex) {
					System.out.println(ex.getMessage());
				}
				System.out.println("Notifier: " + Thread.currentThread().getName()
						+ " has notified the waiting threads: " + System.currentTimeMillis());
				
				System.out.println(Thread.currentThread().getName() + " processed messsage: " + message.getMessage());
			}

		}

	}
	
	public static class Student {
		String name;
		String rollNo;
		Student(String name, String rollNo) {
			this.name = name;
			this.rollNo = rollNo;
		}
		public void printStudentDetails(String threadName) {
			System.out.println(threadName + " Student Name is: " + name + " and Roll No. is: " + rollNo);
		}
	}
}