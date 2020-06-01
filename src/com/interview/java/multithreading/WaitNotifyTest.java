package com.interview.java.multithreading;

public class WaitNotifyTest {

	public static void main(String[] args) {
		Message message = new Message("Hello World!");
		Waiter waiter1 = new Waiter(message);
		new Thread(waiter1, "Waiter1").start();

		Waiter waiter2 = new Waiter(message);

		new Thread(waiter2, "Waiter2").start();

		Notifier notifier = new Notifier(message);
		new Thread(notifier, "Notifier").start();
		System.out.println("All the threads has been started");
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

		public Waiter(Message msg) {
			message = msg;
		}

		@Override
		public void run() {
			System.out.println("Waiter: " + Thread.currentThread().getName() + " started at: "
					+ System.currentTimeMillis() + " and waiting to get notified");
			synchronized (message) {
				try {
					// waiting for the access on message object
					message.wait();
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
				// will print only if wait for Message thread would be over
				System.out.println("Waiter: " + Thread.currentThread().getName()
						+ " has been notified and wait for Message is over at: " + System.currentTimeMillis());
				System.out.println(Thread.currentThread().getName() + " processed messsage: " + message.getMessage());
			}

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
					+ System.currentTimeMillis() + " going to notifiy waiting threads");
			synchronized (message) {
				// if more than one threads waiting for access on message object than program
				// will not be terminated as notify will notify only one waiting thread and
				// that's depends upon OS implementation of thread management.
				// only one thread notified and remaining waiting threads remains in wait state
				// and programs will not be terminated.
				// message.notify();
				message.notifyAll();
				System.out.println("Notifier: " + Thread.currentThread().getName()
						+ " has notified the waiting threads: " + System.currentTimeMillis());
				System.out.println(Thread.currentThread().getName() + " processed messsage: " + message.getMessage());
			}

		}

	}
}