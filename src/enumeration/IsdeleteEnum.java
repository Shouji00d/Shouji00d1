package enumeration;

/**
 * 削除フラグの列挙型------------------------------2
 *
 * @author s.miyagi
 *
 */
public enum IsdeleteEnum {
	/** 削除フラグ */
	EXIST("存在", 0),
	DELETE("削除", 1)

	; // セミコロンで要素とクラス定義を区切る

	/** コード名 */
	private String codeName;
	/** コード値 */
	private int codeValue;

	/**
	 * 新規生成しないprivateコンストラクタ
	 *
	 * @param codename コード名
	 * @param codevalue コード値
	 */
	private IsdeleteEnum(String codeName, int codeValue) {
		this.codeName = codeName;
		this.codeValue = codeValue;
	}

	/**
	 * 列挙子の値(コード名)を返すメソッド
	 *
	 * @return コード名
	 */
	public String getName() {
		return this.codeName;
	}

	/**
	 * 列挙子の値(コード値)を返すメソッド
	 *
	 * @return コード値
	 */
	public int getValue() {
		return this.codeValue;
	}
}