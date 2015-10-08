package ost;

import java.util.Random;

/**
 * 
 * @author Giga
 *
 *         08.10.2015
 */
public class OSMethods {

	public static <T extends Comparable<T>> int size(OSNode<T> node) {

		if (node == null) {

			return 0;
		}

		return size(node.getChild()[0]) + size(node.getChild()[1]) + 1;
	}

	public static <T extends Comparable<T>> OSNode<T> rotate(OSNode<T> h,
			int direction) {
		int oppositeDir = Math.abs(direction - 1);

		if (h == null || h.getChild()[oppositeDir] == null) {
			return h;
		}

		OSNode<T> tmp = h.getChild()[oppositeDir];
		h.getChild()[oppositeDir] = tmp.getChild()[direction];
		tmp.getChild()[direction] = h;
		h.setSize(size(h.getChild()[0]) + size(h.getChild()[1]) + 1);
		tmp.setSize(size(tmp.getChild()[0]) + size(tmp.getChild()[1]) + 1);

		return tmp;
	}

	public static <T extends Comparable<T>> OSNode<T> select(OSNode<T> x, int i) {

		int r = size(x.getChild()[0]) + 1;

		if (r == i) {

			return x;
		}

		if (r < i) {

			return select(x.getChild()[0], i);
		} else {

			return select(x.getChild()[1], i - r);
		}
	}

	public static <T extends Comparable<T>> OSNode<T> insertTop(OSNode<T> h,
			OSNode<T> x) {

		if (h == null) {

			return x;
		}

		if (x.getKey().compareTo(h.getKey()) == -1) {
			h.getChild()[0] = insertTop(h.getChild()[0], x);
			h = rotate(h, 1);
		} else {
			h.getChild()[1] = insertTop(h.getChild()[1], x);
			h = rotate(h, 0);
		}

		return h;
	}

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
			h.getChild()[0] = insertRandom(h.getChild()[0], x);
		} else {
			h.getChild()[1] = insertRandom(h.getChild()[1], x);
		}

		h.setSize(size(h) + 1);

		return h;
	}

}
