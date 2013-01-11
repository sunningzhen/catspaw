package org.catspaw.cherubim.socketpool;

import org.junit.Before;

public class KeepingSocketPoolTest {

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) {
		SocketKeeper keeper = new TestSocketKeeper();
		KeepingSocketPool pool = new KeepingSocketPool("localhost", 8821,
				keeper, 5 * 1000);
		pool.fill(6);
	}
}
