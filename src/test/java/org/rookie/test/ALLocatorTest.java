package org.rookie.test;

import org.junit.Test;
import org.rookie.test.parallel.Allocator;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class ALLocatorTest {
	//
	@Test
	public void allocatorTest() throws InterruptedException {
		TransferTask task1 = new TransferTask("A", "B");
		Thread t1 = new Thread(task1);
		t1.start();
		
		TransferTask task2 = new TransferTask("C", "D");
		Thread t2 = new Thread(task2);
		t2.start();
		
		TransferTask task3 = new TransferTask("A", "C");
		Thread t3 = new Thread(task3);
		t3.start();
		
		TransferTask task4 = new TransferTask("B", "D");
		Thread t4 = new Thread(task4);
		t4.start();
		
		TransferTask task5 = new TransferTask("A", "B");
		Thread t5 = new Thread(task5);
		t5.start();
		
		Thread.sleep(50000);
	}
	
	class TransferTask implements Runnable{

		private String from;
		
		private String to;
		
		public TransferTask(String fromAccount, String toAccount) {
			this.from = fromAccount;
			this.to = toAccount;
		}
		
		public void run() {
			Allocator.getInstance().apply(from, to);
			try {
				Thread.sleep(2000L);
			} catch (InterruptedException e) {
			}
			System.out.println("Account " + from + " transfer to " + to + " finished");
			Allocator.getInstance().free(from, to);
		}
		
	} 
}
