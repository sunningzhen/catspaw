package org.catspaw.cherubim.routing;

public enum AccessStrategy {
	/**
	 * 默认值（如果作用域是默认，需要专门的路由处理链来处理）
	 */
	DEFAULT,
	/**
	 * 只读，如果策略是只读，则根据路由查找数据源时会查找只读数据源
	 */
	READ,
	/**
	 * 只写，作用与读写相同
	 */
	WRITE,
	/**
	 * 读写，如果策略是读写，则根据路由查找数据源时会查找读写数据源
	 */
	READ_WRITE
}
