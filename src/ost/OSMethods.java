package ost;

import java.util.Random;

import splay.STNode;
import utils.Utils;

/**
 * 
 * @author G. Chalauri
 *
 *         08.10.2015
 */
public class OSMethods {

	/**
	 * Return size
	 * 
	 * @param node
	 * @return {@link Integer}
	 */
	public static <T extends Comparable<T>> int size(OSNode<T> node) {

		if (node == null) {

			return 0;
		}

		return size(node.getChild()[Utils.LEFT])
				+ size(node.getChild()[Utils.RIGHT]) + 1;
	}

	/**
	 * Rotate tree
	 * 
	 * @param h
	 * @param direction
	 * @return OSNode<T>
	 */
	public static <T extends Comparable<T>> OSNode<T> rotate(OSNode<T> h,
			int direction) {
		int oppositeDir = Math.abs(direction - 1);

		if (h == null || h.getChild()[oppositeDir] == null) {
			return h;
		}

		OSNode<T> tmp = h.getChild()[oppositeDir];
		h.getChild()[oppositeDir] = tmp.getChild()[direction];
		tmp.getChild()[direction] = h;
		h.setSize(size(h.getChild()[Utils.LEFT])
				+ size(h.getChild()[Utils.RIGHT]) + 1);
		tmp.setSize(size(tmp.getChild()[Utils.LEFT])
				+ size(tmp.getChild()[Utils.RIGHT]) + 1);

		return tmp;
	}

	/**
	 * Select i'th node
	 * 
	 * @param x
	 * @param i
	 * @return OSNode<T>
	 */
	public static <T extends Comparable<T>> OSNode<T> select(OSNode<T> x, int i) {

		int r = size(x.getChild()[Utils.LEFT]) + 1;

		if (r == i) {

			return x;
		}

		if (r < i) {

			return select(x.getChild()[Utils.LEFT], i);
		} else {

			return select(x.getChild()[Utils.RIGHT], i - r);
		}
	}

	/**
	 * Inserting into top
	 * 
	 * @param h
	 * @param x
	 * @return OSNode<T>
	 */
	public static <T extends Comparable<T>> OSNode<T> insertTop(OSNode<T> h,
			OSNode<T> x) {

		if (h == null) {

			return x;
		}

		if (x.getKey().compareTo(h.getKey()) == -1) {
			h.getChild()[Utils.LEFT] = insertTop(h.getChild()[Utils.LEFT], x);
			h = rotate(h, Utils.RIGHT);
		} else {
			h.getChild()[Utils.RIGHT] = insertTop(h.getChild()[Utils.RIGHT], x);
			h = rotate(h, Utils.LEFT);
		}

		return h;
	}

	/**
	 * Random insertion
	 * 
	 * @param h
	 * @param x
	 * @return OSNode<T>
	 */
	public static <T extends Comparable<T>> OSNode<T> insertRandom(OSNode<T> h,
			OSNode<T> x) {

		if (h == null) {

			return x;
		}

		Random rand = new Random();
		if (rand.nextInt() < Integer.MAX_VALUE / (size(h) + 1)) {

			return insertTop(h, x);
		}

		if (x.getKey().compareTo(h.getKey()) == -1) {
			h.getChild()[Utils.LEFT] = insertRandom(h.getChild()[Utils.LEFT], x);
		} else {
			h.getChild()[Utils.RIGHT] = insertRandom(h.getChild()[Utils.RIGHT],
					x);
		}

		h.setSize(size(h) + 1);

		return h;
	}
}
