package com.interview.java.multithreading.threadsafety;

public class ThreadSafetyUsingStaticSynMethod1 {

	public static void main(String[] args) {
		Sum sum1 = new Sum();
		Sum sum2 = new Sum();
		

		Thread t1 = new Thread(new MyTestThread(sum1), "Thread1");
		Thread t2 = new Thread(new MyTestThread(sum1), "Thread2");
		Thread t3 = new Thread(new MyTestThread(sum2), "Thread3");
		Thread t4 = new Thread(new MyTestThread(sum2), "Thread4");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		// wait for t1, t2, t3 and t4 to finish up the complete processing
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			} catch (InterruptedException ex) {
			System.out.println(ex.getMessage());
		}

		// now print out the final value of shared variable count of MyThread class
		System.out.println("All the threads has completed their work");
		System.out.println("Sum1 is: " + sum1.getCount());
		System.out.println("Sum2 is: " + sum2.getCount());

	}
	


}
class MyTestThread implements Runnable {
	Sum sum;
	
	MyTestThread(Sum sum) {
		this.sum = sum;
	}
	@Override
	public void run() {
		sum.toIncrement();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

class Sum {
	static int count = 0;
	synchronized static public void toIncrement() {
		for(int i = 1; i <= 5; i++) {
			System.out.println("Thread: " + Thread.currentThread().getName() + " :: " + ++count);
		}
	}
	
	public int getCount() {
		return count;
	}
	
}
