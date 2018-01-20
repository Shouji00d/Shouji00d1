package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * エラーメッセージのロジッククラス
 *
 * @author s.miyagi
 *
 */
public class LogicErrMessage {

	// 入力制約の正規表現
	/** 半角英数字 */
	public static final String ALPHABET = "^[0-9A-Za-z.]+$";
	/** 全角文字 */
	public static final String ZENKAKU = "[^\\x01-\\x7E]*";
	/** ひらがな */
	public static final String HIRAGANA = "[ぁ-ん|　]+$";
	/** メールアドレス */
	public static final String MAILADDRESS = "[0-9 -/:-@\\[-\\`\\{-\\~\\w]+";
	// 入力制約の正規表現、少し複雑な処理
	/** 年月日 */
	public static final Pattern TIME = Pattern
			.compile("^[0-2][0-9]{3}/[0-3][0-9]/[0-3][0-9]$");
	/** 電話番号 */
	public static final Pattern TELEPHONE = Pattern
			.compile("^[0-9]{3}-[0-9]{4}-[0-9]{4}$");
	Matcher matcher;

	/**
	 * ログイン画面の入力値をチェックして、メッセージを返すメソッド
	 *
	 * @param user ユーザー情報
	 * @return エラーメッセージ
	 */
	public List<String> loginInputCheck(String inputLoginId, String inputPassword) {

		// 文字列を要素毎に格納する為のリスト
		List<String> errList = new ArrayList<String>();

		// 既定のエラーメッセージ
		errList.add("ログインに失敗しました");
		errList.add("ログインIDかパスワードが違います");
		// ログインID---------------------------------------1
		if (inputLoginId.equals("")) {
			errList.add("ログインIDが未入力です");
		} else if (!inputLoginId.matches(ALPHABET)) {
			errList.add("ログインIDの入力値が間違っています");
			errList.add("次の形式を使用してください：半角英数字");
		}

		// パスワード---------------------------------------2
		if (inputPassword.equals("")) {
			errList.add("パスワードが未入力です");
		} else if (!inputPassword.matches(ALPHABET)) {
			errList.add("パスワードの入力値が間違っています");
			errList.add("次の形式を使用してください：半角英数字");
		}
		return errList;
	}

	/**
	 * 詳細画面の入力値をチェックして、メッセージを返すメソッド
	 *
	 * @param employee 社員情報＆社員状況インスタンス
	 * @return エラーメッセージ
	 */
	public List<String> detailInputCheck(Employee employee) {

		// 文字列を要素毎に格納する為のリスト
		List<String> errList = new ArrayList<String>();

		// 氏名-------------------------------------------1
		if (employee.getName().equals("")) {
			errList.add("氏名が未入力です。");
		} else if (!employee.getName().matches(ZENKAKU)) {
			errList.add("氏名の入力値が間違っています。");
			errList.add("次の形式を使用してください：全角");
		}

		// 氏名(ひらがな)-----------------------------------2
		if (employee.getNameHiragana().equals("")) {
			errList.add("氏名(ひらがな)が未入力です。");
		} else if (!employee.getNameHiragana().matches(HIRAGANA)) {
			errList.add("氏名（ひらがな）の入力値が間違っています。");
			errList.add("次の形式を使用してください：全角（ひらがな）");
		}

		// 生年月日---------------------------------------3
		matcher = TIME.matcher(employee.getStrBirthday());
		if (employee.getStrBirthday().equals("")) {
			errList.add("生年月日が未入力です。");
		} else {
			DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			try {
				format.setLenient(false);
				format.parse(employee.getStrBirthday());
			} catch (ParseException e) {
				errList.add("生年月日の入力値が間違っています。");
				errList.add("次の形式を使用してください：YYYY/MM/DD");
			}
		}

		// 性別-------------------------------------------4
		if (employee.getSex() == null) {
			errList.add("性別が未入力です。");
		}

		// メールアドレス-------------------------------------5
		if (employee.getMailAddress().equals("")) {
			errList.add("メールアドレスが未入力です。");
		} else if (!employee.getMailAddress().matches(MAILADDRESS)) {
			errList.add("メールアドレスの入力値が間違っています。");
			errList.add("次の形式を使用してください：半角英数字記号");
		}

		// 電話番号----------------------------------------6
		matcher = TELEPHONE.matcher(employee.getTelephoneNumber());
		if (employee.getTelephoneNumber().equals("")) {
			errList.add("電話番号が未入力です。");
		} else if (!matcher.find()) {
			errList.add("電話番号の入力値が間違っています。");
			errList.add("次の形式を使用してください：半角数字記号、○○○-○○○○-○○○○");
		}

		// 担当管理営業-------------------------------------8
		if (employee.getBusinessManager().equals("")) {
			errList.add("担当管理営業が未入力です。");
		} else if (!employee.getBusinessManager().matches(ZENKAKU)) {
			errList.add("担当管理営業の入力値が間違っています。");
			errList.add("次の形式を使用してください：全角");
		}

		// 事業部-------------------------------------------9
		if (employee.getDepartment().equals("")) {
			errList.add("事業部が未入力です。");
		}

		// 稼働状況-----------------------------------------10
		if (employee.getCommissioningStatus() == null) {
			errList.add("稼働状況が未入力です。");
		}

		// 入社日-------------------------------------------11
		matcher = TIME.matcher(employee.getStrEnterDate());
		if (employee.getStrEnterDate().equals("")) {
			errList.add("入社日が未入力です。");
		} else {
			DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			try {
				format.setLenient(false);
				format.parse(employee.getStrEnterDate());
			} catch (ParseException e) {
				errList.add("入社日の入力値が間違っています。");
				errList.add("次の形式を使用してください：YYYY/MM/DD");
			}
		}

		// 退職日-------------------------------------------12
		matcher = TIME.matcher(employee.getStrRetireDate());
		if (employee.getStrRetireDate().equals("")) {
			// 必須ではない為スルーさせる
		} else {
			DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			try {
				format.setLenient(false);
				format.parse(employee.getStrRetireDate());
			} catch (ParseException e) {
				errList.add("退職日の入力値が間違っています。");
				errList.add("次の形式を使用してください：YYYY/MM/DD");
			}
		}

		// ステータス------------------------------------------13
		if (employee.getStatus().equals("")) {
			errList.add("ステータスが未入力です。");
		}
		return errList;
	}
}
