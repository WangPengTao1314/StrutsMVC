package com.hoperun.commons.util.sort;
import java.util.Vector;
// TODO: Auto-generated Javadoc

/**
 * The Class SortVector.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */
public class SortVector extends Vector {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The compare. */
	private Compare compare; // To hold the callback

	/**
	 * Instantiates a new sort vector.
	 * 
	 * @param comp the comp
	 */
	public SortVector(Compare comp) {
		compare = comp;
	}

	/**
	 * Sort.
	 */
	public void sort() {
		quickSort(0, size() - 1);
	}

	/**
	 * <p>
	 * </p>.
	 * 
	 * @param left the left
	 * @param right the right
	 */
	private void quickSort(int left, int right) {
		if (right > left) {
			Object o1 = elementAt(right);
			int i = left - 1;
			int j = right;
			while (true) {
				while (compare.lessThan(elementAt(++i), o1))
					;
				while (j > 0)
					if (compare.lessThanOrEqual(elementAt(--j), o1))
						break; // out of while
				if (i >= j)
					break;
				swap(i, j);

			}
			swap(i, right);
			quickSort(left, i - 1);
			quickSort(i + 1, right);
		}
	}


	
	/**
	 * Swap.
	 * 
	 * @param loc1 the loc1
	 * @param loc2 the loc2
	 */
	private void swap(int loc1, int loc2) {
		Object tmp = elementAt(loc1);
		setElementAt(elementAt(loc2), loc1);
		setElementAt(tmp, loc2);
	}
}