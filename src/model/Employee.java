package model;

import java.sql.Date;

/**
 * データを保持するクラス
 *
 * @author s.miyagi
 *
 */
public class Employee {

	// EmployeeInfoテーブルのカラム名と同様の変数名
	/** 社員ID */
	private int employeeId;
	/** 名前 */
	private String name;
	/** 名前（ひらがな） */
	private String nameHiragana;
	/** 生年月日 */
	private Date birthday;
	/** 性別 */
	private String sex;
	/** メールアドレス */
	private String mailAddress;
	/** 電話番号 */
	private String telephoneNumber;
	/** 所属会社ID */
	private int companyInfoId;
	/** 担当管理営業 */
	private String businessManager;
	/** 事業部 */
	private String department;
	/** 稼働状況 */
	private String commissioningStatus;
	// EmployeeStateテーブルのカラム名
	/** 入社日 */
	private Date enterDate;
	/** 退職日 */
	private Date retireDate;
	/** ステータス */
	private String status;

	// 変換後に保存される
	/** 年齢 */
	private int age;
	/** 所属会社IDから対応の文字列に変換された会社略称 */
	private String abbreviation;
	/** 数値から対応の文字列に変換された事業部 */
	private String departmentConversion;
	/** 数値から対応の文字列に変換された稼働状況 */
	private String commissioningStatusConversion;
	/** 書式設定で文字列化された生年月日 */
	private String strBirthday;
	/** 書式設定で文字列化された入社日 */
	private String strEnterDate;
	/** 書式設定で文字列化された退職日 */
	private String strRetireDate;

	// 詳細画面から入力される変数
	/** 入力された社員ID */
	private String inputEmployeeId;
	/** 入力された所属会社ID */
	private String inputCompanyInfoId;
	/** 入力されたログインID */
	private String inputLoginId;


	// 以下ゲッターセッターメソッド群---------------------------------------------------------------

	/**
	 * @return employeeId 社員ID
	 */
	public int getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId 社員ID
	 */
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return name 名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name 名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return nameHiragana 名前（ひらがな）
	 */
	public String getNameHiragana() {
		return nameHiragana;
	}

	/**
	 * @param nameHiragana 名前（ひらがな）
	 */
	public void setNameHiragana(String nameHiragana) {
		this.nameHiragana = nameHiragana;
	}

	/**
	 * @return birthday 生年月日
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday 生年月日
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return sex 性別
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex 性別
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return mailAddress メールアドレス
	 */
	public String getMailAddress() {
		return mailAddress;
	}

	/**
	 * @param mailAddress メールアドレス
	 */
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	/**
	 * @return telephoneNumber 電話番号
	 */
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	/**
	 * @param telephoneNumber 電話番号
	 */
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	/**
	 * @return companyInfoId 所属会社ID
	 */
	public int getCompanyInfoId() {
		return companyInfoId;
	}

	/**
	 * @param companyInfoId 所属会社ID
	 */
	public void setCompanyInfoId(int companyInfoId) {
		this.companyInfoId = companyInfoId;
	}

	/**
	 * @return businessManager 担当管理営業
	 */
	public String getBusinessManager() {
		return businessManager;
	}

	/**
	 * @param businessManager 担当管理営業
	 */
	public void setBusinessManager(String businessManager) {
		this.businessManager = businessManager;
	}

	/**
	 * @return department 事業部
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department 事業部
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return commissioningStatus 稼働状況
	 */
	public String getCommissioningStatus() {
		return commissioningStatus;
	}

	/**
	 * @param commissioningStatus 稼働状況
	 */
	public void setCommissioningStatus(String commissioningStatus) {
		this.commissioningStatus = commissioningStatus;
	}

	/**
	 * @return enterDate 入社日
	 */
	public Date getEnterDate() {
		return enterDate;
	}

	/**
	 * @param enterDate 入社日
	 */
	public void setEnterDate(Date enterDate) {
		this.enterDate = enterDate;
	}

	/**
	 * @return retireDate 退職日
	 */
	public Date getRetireDate() {
		return retireDate;
	}

	/**
	 * @param retireDate 退職日
	 */
	public void setRetireDate(Date retireDate) {
		this.retireDate = retireDate;
	}

	/**
	 * @return status ステータス
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status ステータス
	 */
	public void setStatus(String status) {
		this.status = status;
	}

// 変換された後に保存される変数のゲッターセッターメソッド群-------------------------

	/**
	 * @return age 生年月日から算出された年齢
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age 生年月日から算出された年齢
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return abbreviation 会社所属IDの会社略称
	 */
	public String getAbbreviation() {
		return abbreviation;
	}

	/**
	 * @param abbreviation 会社所属IDの会社略称
	 */
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	/**
	 * @return departmentConversion 変換された事業部
	 */
	public String getDepartmentConversion() {
		return departmentConversion;
	}

	/**
	 * @param departmentConversion 変換された事業部
	 */
	public void setDepartmentConversion(String departmentConversion) {
		this.departmentConversion = departmentConversion;
	}

	/**
	 * @return commissioningStatusConversion 変換された稼働状況
	 */
	public String getCommissioningStatusConversion() {
		return commissioningStatusConversion;
	}

	/**
	 * @param commissioningStatusConversion 変換された稼働状況
	 */
	public void setCommissioningStatusConversion(
			String commissioningStatusConversion) {
		this.commissioningStatusConversion = commissioningStatusConversion;
	}

	/**
	 * @return strBirthday 文字列化された生年月日
	 */
	public String getStrBirthday() {
		return strBirthday;
	}

	/**
	 * @param strBirthday 文字列化された生年月日
	 */
	public void setStrBirthday(String strBirthday) {
		this.strBirthday = strBirthday;
	}

	/**
	 * @return strEnterDate 文字列化された入社日
	 */
	public String getStrEnterDate() {
		return strEnterDate;
	}

	/**
	 * @param strEnterDate 文字列化された入社日
	 */
	public void setStrEnterDate(String strEnterDate) {
		this.strEnterDate = strEnterDate;
	}

	/**
	 * @return strRetireDate 文字列化された退職日
	 */
	public String getStrRetireDate() {
		return strRetireDate;
	}

	/**
	 * @param strRetireDate 文字列化された退職日
	 */
	public void setStrRetireDate(String strRetireDate) {
		this.strRetireDate = strRetireDate;
	}

	/**
	 * @return inputEmployeeId 入力された社員ID
	 */
	public String getInputEmployeeId() {
		return inputEmployeeId;
	}

	/**
	 * @param inputEmployeeId 入力された社員ID
	 */
	public void setInputEmployeeId(String inputEmployeeId) {
		this.inputEmployeeId = inputEmployeeId;
	}

	/**
	 * @return inputCompanyInfoId 入力された所属会社ID
	 */
	public String getInputCompanyInfoId() {
		return inputCompanyInfoId;
	}

	/**
	 * @param inputCompanyInfoId 入力された所属会社ID
	 */
	public void setInputCompanyInfoId(String inputCompanyInfoId) {
		this.inputCompanyInfoId = inputCompanyInfoId;
	}

	/**
	 * @return inputLoginId 入力されたログインID
	 */
	public String getInputLoginId() {
		return inputLoginId;
	}

	/**
	 * @param inputLoginId 入力されたログインID
	 */
	public void setInputLoginId(String inputLoginId) {
		this.inputLoginId = inputLoginId;
	}

}
