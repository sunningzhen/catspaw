package org.catspaw.cherubim.persistence.jdbc;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;

public final class ClobUtils {

	public static String read(Clob clob) throws SQLException {
		StringBuilder builder = new StringBuilder();
		char[] buffer = new char[2048];
		Reader reader = clob.getCharacterStream();
		try {
			for (int len = reader.read(buffer); len > 0; len = reader
					.read(buffer)) {
				builder.append(buffer, 0, len);
			}
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new IllegalStateException(e);
				}
			}
		}
		return builder.toString();
	}
}
