package com.interview.java.multithreading.threadsafety;

public class ThreadInconsistency {

	public static void main(String[] args) {
		MyThread thread = new MyThread();
		
		Thread t1 = new Thread(thread, "Thread1");
		Thread t2 = new Thread(thread, "Thread2");
		
		t1.start();
		t2.start();
		
		// wait for t1 and t2 to finish up the complete processing
		
		try {
			t1.join();
			t2.join();
		} catch(InterruptedException ex) {
			System.out.println(ex.getMessage());
		}
		
		// now print out the final value of shared variable count of MyThread class
		System.out.println("After complete processing of thread value of count is: " + thread.getCount());

	}

}

class MyThread implements Runnable {
	int count = 0;
	
	@Override
	public void run() {
		doSomething();
	}
	
	synchronized public void doSomething() {
		for(int i = 1; i <= 10; i++) {
			count ++;
			System.out.println("Executing thread: " + Thread.currentThread().getName() + " and now count is incremented to: " + count);
			try {
				Thread.sleep(i*100);
			} catch(InterruptedException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
	
	public int getCount() {
		return count;
	}
}