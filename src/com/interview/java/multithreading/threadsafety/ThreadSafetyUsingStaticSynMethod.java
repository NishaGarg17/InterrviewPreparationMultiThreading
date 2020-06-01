package com.interview.java.multithreading.threadsafety;

public class ThreadSafetyUsingStaticSynMethod {

	public static void main(String[] args) {
		Table table1 = new Table();
		Table table2 = new Table();
		

		Thread t1 = new Thread(new MyThread1(table1, 2), "Thread1");
		Thread t2 = new Thread(new MyThread1(table1, 3), "Thread2");
		Thread t3 = new Thread(new MyThread1(table2, 4), "Thread3");
		Thread t4 = new Thread(new MyThread1(table2, 5), "Thread4");
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

	}
	


}
class MyThread1 implements Runnable {
	Table table;
	int n;
	
	MyThread1(Table table, int n) {
		this.table = table;
		this.n = n;
	}
	@Override
	public void run() {
		table.printTable(n);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

class Table {
	synchronized static public void printTable(int n) {
		for(int i = 1; i <= 5; i++) {
			System.out.println("Thread: " + Thread.currentThread().getName() + " :: " + n*i);
		}
	}
}
