package main;

import ost.OSMethods;
import ost.OSNode;
import splay.SplayMethods;
import splay.STNode;

/**
 * 
 * @author Giga
 *
 *         04.10.2015
 */
@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) {

		OSNode<Integer> root = new OSNode<Integer>();
		root.setKey(10);
		
		OSNode<Integer> seven = new OSNode<Integer>();
		seven.setKey(7);
		
		OSNode<Integer> minusFive = new OSNode<Integer>();
		minusFive.setKey(-5);
		
		OSNode<Integer> nine = new OSNode<Integer>();
		nine.setKey(9);
		
		
		root = OSMethods.insertTop(root, seven);
		root = OSMethods.insertTop(root, minusFive);
		root = OSMethods.insertTop(root, nine);
		
		System.out.println(root.getKey()+" " + root.getSize());
	}

}
