package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public class DBConnection {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://123.206.29.40:3306/BBBS";
	private static final String USER = "BBBS";
	private static final String PASS = "Ywj615ywj";

	public static Connection getConnection() throws Exception {
        Connection conn;
		Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		if (conn == null)
			return null;

		return conn;
	}

	public static void dbClose(Connection con, PreparedStatement ps, ResultSet rs) throws SQLException {
		rs.close();
		ps.close();
		con.close();
	}


}
