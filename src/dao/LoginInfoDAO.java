package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ログイン情報テーブルのDAO
 *
 * @author s.miyagi
 *
 */
public class LoginInfoDAO {

	/**
	 * ログインIDからLoginInfoテーブルのデータを取得 ----------------①
	 *
	 * @return 表の有無（真偽）
	 */
	public String findByLoginId(
			String inputLoginId,
			String inputPassword) {

		/**	SQL接続クラスの生成 */
		SqlConnection sqlConnection = new SqlConnection();
		/**	SQL接続変数を代入 */
		Connection conn = sqlConnection.getSqlConn();

		try {

			// SELECT文を準備
			String sql = "SELECT login_id, password FROM login_info WHERE login_id=? AND password=?";
			// ステートメントを用意
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文の?に代入する
			pStmt.setString(1, inputLoginId);
			pStmt.setString(2, inputPassword);

			// SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			// 結果表があればパスワードを返す
			if (rs.next()) {
				String loginId = rs.getString("login_id");  // ログインID
				return loginId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null; // SQL文がエラーを起こしても動かす為に、falseを返す
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null; // 結果表が無い場合
	}
}