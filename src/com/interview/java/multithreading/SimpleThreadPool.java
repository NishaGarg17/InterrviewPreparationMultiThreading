package com.interview.java.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleThreadPool {

	public static void main(String[] args) {
		// to create threadpool of 5 threads
		ExecutorService executor = Executors.newFixedThreadPool(5);

		for(int i = 0; i < 10; i++) {
			Runnable jobThread = new JobThread(i);
			executor.execute(jobThread);
		}
		
		executor.shutdown();
		while(!executor.isTerminated()) {
			
		}
		System.out.println("Finished All Threads");
	}

}
