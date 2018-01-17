package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CompanyInfo;

/**
 * 会社情報テーブルのDAO----------------
 *
 * @author s.miyagi
 *
 */
public class CompanyInfoDAO {

	/**
	 * CompanyInfoテーブルのデータを取得 ----------------①
	 *
	 * @return 略称リスト
	 */
	public List<CompanyInfo> findByCompanyInfo() {

		/**	SQL接続クラスの生成 */
		SqlConnection sqlConnection = new SqlConnection();
		/**	SQL接続変数を代入 */
		Connection conn = sqlConnection.getSqlConn();

		/**	略称リスト */
		List<CompanyInfo> companyList = new ArrayList<CompanyInfo>();

		try {
			// SELECT文を準備
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");            // SELECTは取得する文
			sb.append("company_id, ");       // 会社ID
			sb.append("abbreviation ");      // 略称
			sb.append("FROM company_info");  // 場所は会社情報表
			String sql = sb.toString(); // String型に変換
			PreparedStatement pStmt = conn.prepareStatement(sql); // PreparedStatementを用意しつつSQL文を代入

			// SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			// 結果表に格納されたレコードの内容を
			// CompanyInfoインスタンスに設定し、ArrayListインスタンスに追加
			while (rs.next()) {
				int company_id = rs.getInt("company_id");
				String abbreviation = rs.getString("abbreviation");
				CompanyInfo companyInfo = new CompanyInfo(company_id,
						abbreviation);
				companyList.add(companyInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null; // SQL文がエラーを起こしても動かす為に、nullを返す
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
		return companyList; // 何もなければリストを返す
	}

	/**
	 * CompanyInfoテーブルから所属会社IDを条件に会社略称を取得 ----------------②
	 *
	 * @return 略称
	 */
	public String findByAbbreviation(int company_info_id) {

		/**	SQL接続クラスの生成 */
		SqlConnection sqlConnection = new SqlConnection();
		/**	SQL接続変数を代入 */
		Connection conn = sqlConnection.getSqlConn();

		try {
			// SELECT文を準備
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");                                     // SELECTは取得する文
			sb.append("abbreviation ");                               // 略称
			sb.append("FROM company_info INNER JOIN employee_info "); // 会社情報と会社状況を結合
			sb.append("ON company_info.company_id = employee_info.company_info_id "); // （論理名が同じ）社員IDでの内部結合表
			sb.append("WHERE company_info.company_id=?;");            // 「?」に代入された社員IDを条件に
			String sql = sb.toString(); // String型に変換
			PreparedStatement pStmt = conn.prepareStatement(sql); // PreparedStatementを用意しつつSQL文を代入

			// SELECTを実行し、結果表を取得
			pStmt.setInt(1, company_info_id);
			ResultSet rs = pStmt.executeQuery();

			// 結果表に格納されたレコードを返す
			if (rs.next()) {
				String abbreviation = rs.getString("abbreviation");
				return abbreviation;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null; // SQL文がエラーを起こしても動かす為に、nullを返す
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