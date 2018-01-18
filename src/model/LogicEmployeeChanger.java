package model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import enumeration.DepartmentEnum;
import enumeration.CommissioningStatusEnum;

/**
 * 加工変換ロジッククラス
 *
 * @author s.miyagi
 *
 */
public class LogicEmployeeChanger {

	/**
	 * 生年月日を引数に入れると現在日時から年齢を算出する
	 * 社員一覧画面用
	 *
	 * @param birthday 生年月日
	 *
	 * @return 年齢の値
	 */
	public int calcAge(Date birthday) {
		Calendar calendar = Calendar.getInstance(); // ゲットインスタンスメソッドで現在の日時が取得できる
		java.util.Date now = calendar.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		return (Integer.parseInt(simpleDateFormat.format(now)) - Integer // 生年月日と現在日時から年齢を算出する計算式
				.parseInt(simpleDateFormat.format(birthday))) / 10000;
	}

	/**
	 * 数値から文字列の事業部(0:開発、1:NW、2:検証、3:オフィス、4:管理)を取得するメソッド
	 * 社員一覧画面用
	 *
	 * @param inputDepartment 事業部の数値
	 *
	 * @return 対応の文字列(0:開発、1:NW、2:検証、3:オフィス、4:管理)
	 */
	public String caseDepartment(String inputDepartment) {
		int insDepartment = Integer.parseInt(inputDepartment);
		switch (insDepartment) {
		case 0:
			inputDepartment = DepartmentEnum.DEVELOPMENT.getName();
			break;
		case 1:
			inputDepartment = DepartmentEnum.NETWORK.getName();
			break;
		case 2:
			inputDepartment = DepartmentEnum.VERIFICATION.getName();
			break;
		case 3:
			inputDepartment = DepartmentEnum.OFFICE.getName();
			break;
		case 4:
			inputDepartment = DepartmentEnum.MANAGEMENT.getName();
			break;
		default:
			inputDepartment = "適切な値が入力されていない為、文字列が返せません";
			break;
		}
		return inputDepartment;
	}

	/**
	 * 数値から文字列の稼働・未稼働を取得するメソッド
	 * 社員一覧画面用
	 *
	 * @param inputCommissioningStatus 稼働状況の数値
	 *
	 * @return 対応の文字列(0:未稼働、1:稼働)
	 */
	public String caseCommissioningStatus(String inputCommissioningStatus) {
		int insCommissioningStatus = Integer.parseInt(inputCommissioningStatus);
		switch (insCommissioningStatus) {
		case 0:
			inputCommissioningStatus = CommissioningStatusEnum.NOT_RUNNING.getName();
			break;
		case 1:
			inputCommissioningStatus = CommissioningStatusEnum.RUNNING.getName();
			break;
		default:
			inputCommissioningStatus = "適切な値が入力されていない為、文字列が返せません";
			break;
		}
		return inputCommissioningStatus;
	}

	/**
	 * DAO→インスタンス用の加工メソッド
	 *
	 * @param employee 社員情報＆社員状況インスタンス
	 *
	 */
	public void getEmployeeChange(Employee employee) {
		if (employee != null) { // employee が空だとエラーが出る
			// 日時のフォーマット データ型を指定のフォーマットに変えるクラス
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
			// まだ文字列になっていない場合、データ型からフォーマットパターンの文字列に変える
			employee.setStrBirthday(simpleDateFormat.format(employee
					.getBirthday()));
			// 電話番号の-（ハイフン）を除去して入れ直す
			employee.setTelephoneNumber(new StringBuilder(employee
					.getTelephoneNumber().replaceAll("-", "")).insert(7, '-')
					.insert(3, '-').toString());
			// 詳細画面からの入力変換時に文字列を数値にキャストする
			if (employee.getInputCompanyInfoId() != null) {
				employee.setCompanyInfoId(Integer.parseInt(employee
						.getInputCompanyInfoId()));
			}
			// (0:開発、1:NW、2:検証、3:オフィス、4:管理)にして保存
			employee.setDepartmentConversion(caseDepartment(employee
					.getDepartment()));
			// (0:未稼働、1:稼働)にして保存
			employee.setCommissioningStatusConversion(caseCommissioningStatus(employee
					.getCommissioningStatus()));
			// 別テーブルの区切り、目印-----------------------------------
			// まだ文字列になっていない場合、データ型からフォーマットパターンの文字列に変える
			employee.setStrEnterDate(simpleDateFormat.format(employee
					.getEnterDate()));
			// まだ文字列になっていない場合、データ型からフォーマットパターンの文字列に変える
			// nullだとフォーマットに引っかかる為、if分岐で処理しない様に
			if (employee.getRetireDate() != null) {
				employee.setStrRetireDate(simpleDateFormat.format(employee
						.getRetireDate()));
			}
		}
	}

	/**
	 * インスタンス→DAO用の加工メソッド
	 *
	 * @param employee 社員情報＆社員状況インスタンス
	 */
	public void setEmployeeChange(Employee employee) {
		// 電話番号の-（ハイフン）を除去して入れ直す
		employee.setTelephoneNumber(new StringBuilder(employee
				.getTelephoneNumber().replaceAll("-", "")).insert(7, '-')
				.insert(3, '-').toString());
		// 別テーブルの区切り、目印-----------------------------------
		// sqlの方で、エラーを出さない為に"0001/01/01"を入力
		if (employee.getStrRetireDate() == "") {
			employee.setStrRetireDate(null);
		}
	}

	/**
	 * インスタンス→DAOでエラー時に入力欄に返す時の加工メソッド
	 *
	 * @param employee 社員情報＆社員状況インスタンス
	 */
	public void reEmployeeChange(Employee employee) {
		// 詳細画面からの入力変換時に文字列を数値にキャストする
		employee.setCompanyInfoId(Integer.parseInt(employee
				.getInputCompanyInfoId()));
		// 文字列として入力された社員IDを、入力される前の数字に変える。誤入力の時に使用される
		if (employee.getInputEmployeeId() != "") {
			employee.setEmployeeId(Integer.parseInt(employee
					.getInputEmployeeId()));
		}
	}

	/**
	 * リストの要素（インスタンス）から取り出して、変換して置き換えるメソッド
	 * 社員一覧画面用
	 *
	 * @param employee 社員情報＆社員状況インスタンス
	 */
	public List<Employee> listEmployeeChange(List<Employee> employeeList) {
		for (int i = 0; i < employeeList.size(); i++) {
			// 生年月日から現在日時で産出された年齢
			employeeList.get(i).setAge(
					calcAge(employeeList.get(i).getBirthday()));
			// 番号を対応の文字列(0:開発、1:NW、2:検証、3:オフィス、4:管理)にして保存
			employeeList.get(i).setDepartmentConversion(
					caseDepartment(employeeList.get(i).getDepartment()));
			// 番号を対応の文字列(0:未稼働、1:稼働)にして保存
			employeeList.get(i).setCommissioningStatusConversion(
					caseCommissioningStatus(employeeList.get(i)
							.getCommissioningStatus()));
			// 別テーブルの区切り、目印-----------------------------------
			// 日時のフォーマット データ型を指定のフォーマットに変えるクラス
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
			// 文字列化書式設定後の入社日
			employeeList.get(i)
					.setStrEnterDate(
							simpleDateFormat.format(employeeList.get(i)
									.getEnterDate()));
		}
		return employeeList; // 加工後のリスト
	}
}
