package org.rookie.test.jdk;

import java.util.Timer;
import java.util.concurrent.locks.ReentrantLock;

public class HelloWorld {

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(null, 1l);
		System.out.println("hello world !");
		ReentrantLock lock = new ReentrantLock();
		lock.lock();

		lock.unlock();
	}

}
