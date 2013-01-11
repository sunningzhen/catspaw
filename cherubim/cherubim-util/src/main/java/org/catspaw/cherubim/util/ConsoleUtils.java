package org.catspaw.cherubim.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 控制台工具类
 * @author 孙宁振 sunningzhen@gmail.com
 */
public final class ConsoleUtils {

	private static final BufferedReader	READER	= new BufferedReader(new InputStreamReader(
														System.in));
	private static final BufferedWriter	WRITER	= new BufferedWriter(new OutputStreamWriter(
														System.out));

	private ConsoleUtils() {
	}

	public static void write(String text) {
		try {
			WRITER.write(text);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	public static void newLine() {
		try {
			WRITER.newLine();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	public static String readLine() {
		try {
			return READER.readLine();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
}
