package model;

/**
 * 会社情報のデータを保持するクラス
 *
 * @author s.miyagi
 *
 */
public class CompanyInfo {

	// ローワーキャメルでテーブルのカラム名と同一の変数名
	/** 会社ID */
	private int companyId;
	/** 会社略称 */
	private String abbreviation;

	/**
	 * SQLのデータを格納する
	 *
	 * @param companyId 会社ID
	 *
	 * @param abbreviation 会社略称
	 *
	 */
	public CompanyInfo(int companyId, String abbreviation) {
		this.companyId = companyId;
		this.abbreviation = abbreviation;
	}

	/**
	 * ゲッターメソッド
	 *
	 * @return 会社ID
	 */
	public int getCompanyId() {
		return companyId;
	}

	/**
	 * ゲッターメソッド
	 *
	 * @return 会社略称
	 */
	public String getAbbreviation() {
		return abbreviation;
	}

}
