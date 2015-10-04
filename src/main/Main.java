package main;

import splay.SplayMethods;
import splay.STNode;

/**
 * 
 * @author Giga
 *
 *         04.10.2015
 */
public class Main {

	public static void main(String[] args) {

		Integer a = 5;
		Integer b = 9;
		Integer c = -7;

		STNode<Integer> h = SplayMethods.splayInsert(null, a);
		h = SplayMethods.splayInsert(h, b);
		h = SplayMethods.splayInsert(h, c);

		System.out.println(h.getKey() + " ");

	}

}
