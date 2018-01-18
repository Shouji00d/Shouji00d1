package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * SQLに接続するクラス
 *
 * @author s.miyagi
 *
 */
public class SqlConnection {

	// JDBCの定数
	/**	MySQLのドライバー */
	private final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	/**	DBのURL */
	private final String JDBC_URL = "jdbc:mysql://localhost/Employee_DB?useSSL=false";
	/**	SQLのユーザー */
	private final String DB_USER = "root";
	/**	SQLのパスワード */
	private final String DB_PASS = "1111";
	/**	接続変数 */
	Connection conn;

	/**
	 * SQLに接続するメソッド
	 *
	 * @return 接続変数conn
	 */
	public Connection getSqlConn() {
		try {
			// JDBCドライバを読み込み
			Class.forName(DRIVER_NAME);

			// データベース接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e1) {
			System.out.println("SQLException: " + e1.getMessage());
		} catch (Exception e2) {
			System.out.println("Exception: " + e2.getMessage());
			e2.printStackTrace();
		}
		return conn; // 何も引っかからなければ、ここで返す
	}
}
