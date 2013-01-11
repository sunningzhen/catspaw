package org.catspaw.cherubim.util;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * 把Iterator包装为Enumeration的适配器
 * @author 孙宁振 sunningzhen@gmail.com
 * @version 1.0 2011-4-17
 */
public class EnumerationAdapter<E> implements Enumeration<E> {

	private Iterator<E>	iterator;

	public EnumerationAdapter(Iterator<E> iterator) {
		this.iterator = iterator;
	}

	public boolean hasMoreElements() {
		return iterator.hasNext();
	}

	public E nextElement() {
		return iterator.next();
	}
}
