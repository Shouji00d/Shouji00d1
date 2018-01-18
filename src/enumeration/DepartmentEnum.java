package enumeration;

/**
 * 事業部の列挙型--------------------------------3
 *
 * @author s.miyagi
 *
 */
public enum DepartmentEnum {
	/** 事業部 */
	DEVELOPMENT("開発", 0),
	NETWORK("NW", 1),
	VERIFICATION("検証", 2),
	OFFICE("オフィス", 3),
	MANAGEMENT("管理", 4)

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
	private DepartmentEnum(String codeName, int codeValue) {
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