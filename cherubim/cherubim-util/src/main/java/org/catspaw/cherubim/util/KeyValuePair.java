package org.catspaw.cherubim.util;

import java.io.Serializable;
import java.util.Comparator;

/**
 * 键值对
 * @author 孙宁振 sunningzhen@gmail.com
 * @version 1.0 2011-4-17
 */
public class KeyValuePair implements Comparable<KeyValuePair>, Serializable {

	private static final long						serialVersionUID	= 5197993079616572277L;
	public static final Comparator<KeyValuePair>	COMPARATOR			= new Comparator<KeyValuePair>() {

																			public int compare(
																					KeyValuePair o1,
																					KeyValuePair o2) {
																				return o1
																						.compareTo(o2);
																			};
																		};
	private String									key;
	private Object									value;

	public KeyValuePair(String key, Object value) {
		this.key = key;
		this.value = value;
	}

	public String getkey() {
		return this.key;
	}

	public Object getValue() {
		return this.value;
	}

	public int compareTo(KeyValuePair other) {
		return this.getkey().compareTo(other.getkey());
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("KeyValuePair[");
		sb.append(this.key);
		sb.append(", ");
		sb.append(this.value);
		sb.append("]");
		return (sb.toString());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		KeyValuePair other = (KeyValuePair) obj;
		if (key == null) {
			if (other.key != null) {
				return false;
			}
		} else if (!key.equals(other.key)) {
			return false;
		}
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}
}
