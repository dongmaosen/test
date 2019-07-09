package org.rookie.test.object;
/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class Child extends Father {

	private static int a = 0;
	
	public Child() {
		super();
	}
	
	public int getA() {
		return a;
	}
	
	@Override
	void init() {
		a = 5;
	}

	public static void main(String[] args) {
		Child f = new Child();
		System.out.println(f.getA());
	}
}
