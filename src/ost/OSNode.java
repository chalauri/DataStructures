package ost;

import java.lang.reflect.Array;

/**
 * 
 * @author G. Chalauri
 *
 *         08.10.2015
 */
public class OSNode<T extends Comparable<T>> {

	private T key;
	private Integer size;

	@SuppressWarnings("unchecked")
	private OSNode<T> child[] = (OSNode<T>[]) Array
			.newInstance(OSNode.class, 2);

	public T getKey() {
		return key;
	}

	public void setKey(T key) {
		this.key = key;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public OSNode<T>[] getChild() {
		return child;
	}

	public void setChild(OSNode<T>[] child) {
		this.child = child;
	};
	//
	// public OSNode(T key) {
	// this.key = key;
	// child[0] = null;
	// child[1] = null;
	// }

}
