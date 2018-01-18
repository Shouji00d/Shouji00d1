package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.Employee;

/**
 * 社員情報と社員状況を結合したテーブルを扱うDAO
 *
 * @author s.miyagi
 *
 */
public class EmployeeDAO {

	/**
	 * Employeeテーブルのデータを取得 ----------------③
	 *
	 * @return 社員情報＆社員状況リスト
	 */
	public List<Employee> findByEmployee() {

		/**	SQL接続クラスの生成 */
		SqlConnection sqlConnection = new SqlConnection();
		/**	SQL接続変数を代入 */
		Connection conn = sqlConnection.getSqlConn();

		/**	社員情報＆社員状況リスト */
		List<Employee> employeeList = new ArrayList<Employee>();

		try {

			// SELECT文を準備
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT");                 // SELECTは取得する句
			sb.append(" employee_id,");          // 社員ID
			sb.append(" name,");                 // 名前
			sb.append(" name_hiragana,");        // 名前（ひらがな）
			sb.append(" birthday,");             // 生年月日
			sb.append(" sex,");                  // 性別
			sb.append(" mail_address,");         // メールアドレス
			sb.append(" telephone_number,");     // 電話番号
			sb.append(" abbreviation,");         // 略称
			sb.append(" business_manager,");     // 管理営業
			sb.append(" department,");           // 事業部
			sb.append(" commissioning_status,"); // 稼働状況
			// 別テーブルの区切り、目印----------------------------
			sb.append(" enter_date,");           // 入社日
			sb.append(" retire_date,");          // 退社日
			sb.append(" status");                // ステータス
			sb.append(" FROM (employee_info INNER JOIN employee_state"); // 社員情報テーブルと社員状況テーブルを
			sb.append(" ON employee_info.employee_id = employee_state.employee_info_id)"); // （論理名が同じ）社員IDでの内部結合表
			sb.append(" INNER JOIN company_info ON employee_info.company_info_id = company_info.company_id"); // （論理名が同じ）会社IDでの内部結合表
			sb.append(" WHERE employee_info.is_deleted=0 AND employee_state.is_deleted=0;"); // 削除フラグ０を条件に

			// ステートメントの用意
			PreparedStatement pStmt = conn.prepareStatement(sb.toString());

			// SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			// 結果表に格納されたレコードの内容を
			// Employeeインスタンスに設定し、ArrayListインスタンスに追加
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setEmployeeId(rs.getInt("employee_id"));              // 社員ID
				employee.setName(rs.getString("name"));                        // 名前
				employee.setNameHiragana(rs.getString("name_hiragana"));       // 名前（ひらがな）
				employee.setBirthday(rs.getDate("birthday"));                  // 生年月日
				employee.setSex(rs.getString("sex"));                          // 性別
				employee.setMailAddress(rs.getString("mail_address"));         // メールアドレス
				employee.setTelephoneNumber(rs.getString("telephone_number")); // 電話番号
				employee.setAbbreviation(rs.getString("abbreviation"));        // 略称
				employee.setBusinessManager(rs.getString("business_manager")); // 管理営業
				employee.setDepartment(rs.getString("department"));            // 事業部
				employee.setCommissioningStatus(rs
						.getString("commissioning_status"));                   // 稼働状況
				// 別テーブルの区切り、目印----------------------------
				employee.setEnterDate(rs.getDate("enter_date"));               // 入社日
				employee.setRetireDate(rs.getDate("retire_date"));             // 退社日
				employee.setStatus(rs.getString("status"));                    // ステータス
				employeeList.add(employee); // employeeListのindexにemployeeを追加
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
		return employeeList; // 何もなければリストを返す
	}

	/**
	 * employee_idからEmployeeテーブルのデータを取得 ----------------② findByEmployeeの一部変更版
	 *
	 * @return 社員情報＆社員状況のインスタンス
	 */
	public Employee findByEmpId(String inputEmpId) {

		/**	SQL接続クラスの生成 */
		SqlConnection sqlConnection = new SqlConnection();
		/**	SQL接続変数を代入 */
		Connection conn = sqlConnection.getSqlConn();

		try {
			// SELECT文を準備
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT");                 // SELECTは取得する句
			sb.append(" employee_id,");          // 社員ID
			sb.append(" name,");                 // 名前
			sb.append(" name_hiragana,");        // 名前（ひらがな）
			sb.append(" birthday,");             // 生年月日
			sb.append(" sex,");                  // 性別
			sb.append(" mail_address,");         // メールアドレス
			sb.append(" telephone_number,");     // 電話番号
			sb.append(" company_info_id,");      // 所属会社ID
			sb.append(" business_manager,");     // 管理営業
			sb.append(" department,");           // 事業部
			sb.append(" commissioning_status,"); // 稼働状況
			// 別テーブルの区切り、目印----------------------------
			sb.append(" enter_date,");           // 入社日
			sb.append(" retire_date,");          // 退社日
			sb.append(" status");                // ステータス
			sb.append(" FROM employee_info INNER JOIN employee_state"); // 社員情報テーブルと社員状況テーブルを
			sb.append(" ON employee_info.employee_id = employee_state.employee_info_id"); // （論理名が同じ）社員IDでの内部結合表
			sb.append(" WHERE employee_info.employee_id=?;"); // 「?」に代入された社員IDを条件に
			// ステートメントを用意
			PreparedStatement pStmt = conn.prepareStatement(sb.toString());
			pStmt.setString(1, inputEmpId);

			// SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			// 結果表に格納されたレコードの内容を
			// Employeeインスタンスに設定し、ArrayListインスタンスに追加
			if (rs.next()) {
				Employee employee = new Employee();
				employee.setEmployeeId(rs.getInt("employee_id"));              // 社員ID
				employee.setName(rs.getString("name"));                        // 名前
				employee.setNameHiragana(rs.getString("name_hiragana"));       // 名前（ひらがな）
				employee.setBirthday(rs.getDate("birthday"));                  // 生年月日
				employee.setSex(rs.getString("sex"));                          // 性別
				employee.setMailAddress(rs.getString("mail_address"));         // メールアドレス
				employee.setTelephoneNumber(rs.getString("telephone_number")); // 電話番号
				employee.setCompanyInfoId(rs.getInt("company_info_id"));       // 所属会社ID
				employee.setBusinessManager(rs.getString("business_manager")); // 管理営業
				employee.setDepartment(rs.getString("department"));            // 事業部
				employee.setCommissioningStatus(rs
						.getString("commissioning_status"));                   // 稼働状況
				// 別テーブルの区切り、目印----------------------------
				employee.setEnterDate(rs.getDate("enter_date"));               // 入社日
				employee.setRetireDate(rs.getDate("retire_date"));             // 退社日
				employee.setStatus(rs.getString("status"));                    // ステータス
				return employee; // employeeインスタンスを返す
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

	/* ↓ここからINSERTとUPDATE文↓ */

	// 登録日時、更新日時用
	// Calendarクラスで、現在日時を取得
	Calendar calendar = Calendar.getInstance();
	// SimpleDateFormatクラス
	// 表示形式を指定"yyyy-MM-dd HH:mm:ss.SSS"
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss.SSS");
	String timeStamp = simpleDateFormat.format(calendar.getTime());

	/**
	 * 詳細画面からの新規登録 ----------------③ EmployeeInfoテーブルに登録する
	 *
	 * @param employee 社員情報＆社員状況インスタンス
	 *
	 */
	public void insertByEmployee(Employee employee) {

		/**	SQL接続クラスの生成 */
		SqlConnection sqlConnection = new SqlConnection();
		/**	SQL接続変数を代入 */
		Connection conn = sqlConnection.getSqlConn();

		try {

			// INSERT文を準備-----------------------------1

			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO ");   // INSERTは挿入する句
			sb.append("employee_info "); // 社員情報テーブルに
			sb.append("VALUES "); // 次の値を挿入する
			sb.append("(");       // ここから-----↓↓
			sb.append("0 ,");     // 社員ID、0で自動採番
			sb.append("? ,");     // 1番
			sb.append("? ,");     // 2番
			sb.append("? ,");     // 3番
			sb.append("? ,");     // 4番
			sb.append("? ,");     // 5番
			sb.append("? ,");     // 6番
			sb.append("? ,");     // 7番
			sb.append("? ,");     // 8番
			sb.append("? ,");     // 9番
			sb.append("? ,");     // 10番
			sb.append("0 ,");     // 削除フラグ、0でデフォルト
			sb.append("null ,");  // 登録日、nullで現在の日時を取得
			sb.append("null ,");  // 更新日、nullで現在の日時を取得
			sb.append("? ,");     // 11番
			sb.append("?");       // 12番
			sb.append(")");       // ここまで-----↑↑

			// ステートメントの用意-----------------------------1
			PreparedStatement pStmt = conn.prepareStatement(sb.toString());
			/* SQL文の?には左から自働連番で値が入る */
			pStmt.setString(1, employee.getName());                 // １番、氏名
			pStmt.setString(2, employee.getNameHiragana());         // ２番、氏名（ひらがな）
			pStmt.setString(3, employee.getStrBirthday());          // ３番、生年月日
			pStmt.setString(4, employee.getSex());                  // ４番、性別
			pStmt.setString(5, employee.getMailAddress());          // ５番、メールアドレス
			pStmt.setString(6, employee.getTelephoneNumber());      // ６番、電話番号
			pStmt.setString(7, employee.getInputCompanyInfoId());   // ７番、所属会社名ID
			pStmt.setString(8, employee.getBusinessManager());      // ８番、管理営業
			pStmt.setString(9, employee.getDepartment());           // ９番、事業部
			pStmt.setString(10, employee.getCommissioningStatus()); // １０番、稼働状況
			pStmt.setString(11, employee.getInputLoginId());        // １１番、登録ID
			pStmt.setString(12, employee.getInputLoginId());        // １２番、更新ID（登録ID）
			// UPDATE（INSERT）を実行-----------------------------1
			pStmt.executeUpdate();

			// INSERT文を準備-----------------------------2

			sb.setLength(0);              // StringBuilder型変数を初期化
			sb.append("INSERT INTO ");    // INSERTは挿入する句
			sb.append("employee_state "); // 社員状況テーブルに
			sb.append("VALUES "); // 次の値を挿入する
			sb.append("(");       // ここから-----↓↓
			sb.append("(SELECT MAX(employee_id) from employee_info), ");// 社員情報の社員IDの最大値を取得
			sb.append("?, ");     // 1番
			sb.append("?, ");     // 2番
			sb.append("?, ");     // 3番
			sb.append("0, ");     // 削除フラグ、デフォルトは0
			sb.append("null, ");  // 登録日、nullで現在の日時を取得
			sb.append("null, ");  // 更新日、nullで現在の日時を取得
			sb.append("?, ");     // 4番
			sb.append("?");       // 5番
			sb.append(")");       // ここまで-----↑↑

			// ステートメントの用意-----------------------------2
			pStmt = conn.prepareStatement(sb.toString());

			/* SQL文の?には左から自働連番で値が入る */
			pStmt.setString(1, employee.getStrEnterDate());  // １番、入社日
			pStmt.setString(2, employee.getStrRetireDate()); // ２番、退社日
			pStmt.setString(3, employee.getStatus());        // ３番、ステータス
			pStmt.setString(4, employee.getInputLoginId());  // ４番、登録ID
			pStmt.setString(5, employee.getInputLoginId());  // ５番、更新ID（登録ID）

			// UPDATE（INSERT）を実行-----------------------------2
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
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
	}

	/**
	 * 詳細画面からの更新 ----------------④
	 *
	 * @param delivery 社員情報＆社員状況インスタンス
	 *
	 */
	public void updateByEmployee(Employee employee) {

		/**	SQL接続クラスの生成 */
		SqlConnection sqlConnection = new SqlConnection();
		/**	SQL接続変数を代入 */
		Connection conn = sqlConnection.getSqlConn();

		try {
			// UPDATE文を準備-----------------------------1

			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE ");        // UPDATEは更新する句
			sb.append("employee_info "); // 社員情報テーブルに
			sb.append("SET ");           // 次の値をセットする↓↓
			sb.append("name = ?, ");                  // 1番
			sb.append("name_hiragana = ?, ");         // 2番
			sb.append("birthday = ?, ");              // 3番
			sb.append("sex = ?, ");                   // 4番
			sb.append("mail_address = ?, ");          // 5番
			sb.append("telephone_number = ?, ");      // 6番
			sb.append("company_info_id = ?, ");       // 7番
			sb.append("business_manager = ?, ");      // 8番
			sb.append("department = ?, ");            // 9番
			sb.append("commissioning_status = ?, ");  // 10番
			sb.append("modified = ?, ");              // 11番
			sb.append("modified_id = ? ");            // 12番
			sb.append("WHERE ");                      // 以下の条件
			sb.append("employee_id = ?");             // 13番

			// ステートメントの用意-----------------------------1
			PreparedStatement pStmt = conn.prepareStatement(sb.toString());
			/* SQL文の?には左から自働連番で値が入る */
			pStmt.setString(1, employee.getName());                 // １番、氏名
			pStmt.setString(2, employee.getNameHiragana());         // ２番、氏名（ひらがな）
			pStmt.setString(3, employee.getStrBirthday());          // ３番、生年月日
			pStmt.setString(4, employee.getSex());                  // ４番、性別
			pStmt.setString(5, employee.getMailAddress());          // ５番、メールアドレス
			pStmt.setString(6, employee.getTelephoneNumber());      // ６番、電話番号
			pStmt.setString(7, employee.getInputCompanyInfoId());   // ７番、所属会社ID
			pStmt.setString(8, employee.getBusinessManager());      // ８番、管理営業
			pStmt.setString(9, employee.getDepartment());           // ９番、事業部
			pStmt.setString(10, employee.getCommissioningStatus()); // １０番、稼働状況
			pStmt.setString(11, timeStamp);                         // １１番、更新日時
			pStmt.setString(12, employee.getInputLoginId());        // １２番、更新ID
			pStmt.setString(13, employee.getInputEmployeeId());     // １３番、社員ID

			// UPDATEを実行-----------------------------1
			pStmt.executeUpdate();

			// UPDATE文を準備-----------------------------2
			sb.setLength(0);                    // StringBuilder型変数を初期化
			sb.append("UPDATE ");               // UPDATEは更新する句
			sb.append("employee_state ");       // 社員状況テーブルに
			sb.append("SET ");                  // 次の値をセットする
			sb.append("enter_date = ?, ");      // 1番
			sb.append("retire_date = ?, ");     // 2番
			sb.append("status = ?, ");          // 3番
			sb.append("modified = ?, ");        // 4番
			sb.append("modified_id = ? ");      // 5番
			sb.append("WHERE ");                // 以下の条件
			sb.append("employee_info_id = ?");  // 6番

			// ステートメントの用意-----------------------------2
			pStmt = conn.prepareStatement(sb.toString());
			/* SQL文の?には左から自働連番で値が入る */
			pStmt.setString(1, employee.getStrEnterDate()); // １番、入社日
			pStmt.setString(2, employee.getStrRetireDate()); // ２番、退社日
			pStmt.setString(3, employee.getStatus()); // ３番、ステータス
			pStmt.setString(4, timeStamp); // ４番、更新日時
			pStmt.setString(5, employee.getInputLoginId()); // ５番、更新ID
			pStmt.setString(6, employee.getInputEmployeeId()); // ６番、社員ID

			// UPDATEを実行し、結果表を取得-----------------------------2
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
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
	}

	/**
	 * 一覧画面からの削除 ----------------⑤
	 *
	 * @param inputEmployeeId 社員ID
	 */
	public void delete(String inputEmployeeId) {

		/**	SQL接続クラスの生成 */
		SqlConnection sqlConnection = new SqlConnection();
		/**	SQL接続変数を代入 */
		Connection conn = sqlConnection.getSqlConn();

		try {
			// UPDATE文を準備-------------------------1
			String sql = "DELETE FROM employee_state WHERE employee_info_id = ?";

			// ステートメントの用意-------------------------1
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, inputEmployeeId); // 社員ID

			// UPDATEを実行-------------------------1
			pStmt.executeUpdate();

			// UPDATE文を準備-------------------------2
			sql = "DELETE FROM employee_info WHERE employee_id =?";

			// ステートメントの用意-------------------------2
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, inputEmployeeId); // 社員ID

			// UPDATEを実行-------------------------2
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
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
	}
}