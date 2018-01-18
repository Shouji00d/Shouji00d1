package enumeration;

/**
 * ステータスの列挙型--------------------------------5
 *
 * @author s.miyagi
 *
 */
public enum StatusEnum {
	/** ステータス */
	ENROLLMENT("在籍", 0),
	RETIREMENT("退職", 1),
	JOINED_WAIT("入社待", 2),
	JOINED_CANCELLATION("入社取り消し", 3)

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
	private StatusEnum(String codeName, int codeValue) {
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
