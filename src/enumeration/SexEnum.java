package enumeration;

/**
 * 性別の列挙型----------------------------------1
 *
 * @author s.miyagi
 *
 */
public enum SexEnum {
	/** 性別 */
	MAN("男", 0),
	FEMALE("女", 1)

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
	private SexEnum(String codeName, int codeValue) {
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