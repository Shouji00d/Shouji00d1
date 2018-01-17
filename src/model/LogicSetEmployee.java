package model;

import javax.servlet.http.HttpServletRequest;

public class LogicSetEmployee {

	public Employee input(HttpServletRequest request, String loginUser){
	// DAOへの受け渡し
	Employee empIdList = new Employee();
	/* 隠されて入力された社員ID */
	empIdList.setInputEmployeeId(request.getParameter("setEmployeeId"));
	/* 入力された名前 */
	empIdList.setName(request.getParameter("setName"));
	/* 入力された名前（ひらがな） */
	empIdList.setNameHiragana(request.getParameter("setNameHiragana"));
	/* 入力された生年月日 */
	empIdList.setStrBirthday(request.getParameter("setBirthday"));
	/* 入力された性別 */
	empIdList.setSex(request.getParameter("setSex"));
	/* 入力されたメールアドレス */
	empIdList.setMailAddress(request.getParameter("setMailAddress"));
	/* 入力された電話番号 */
	empIdList.setTelephoneNumber(request.getParameter("setTelephoneNumber"));
	/* 入力された所属会社ID */
	empIdList.setInputCompanyInfoId(request.getParameter("setCompanyInfoId"));
	/* 入力された担当管理営業 */
	empIdList.setBusinessManager(request.getParameter("setBusinessManager"));
	/* 入力された事業部 */
	empIdList.setDepartment(request.getParameter("setDepartment"));
	/* 入力された稼働状況 */
	empIdList.setCommissioningStatus(request.getParameter("setCommissioningStatus"));
	/* 入力された入社日 */
	empIdList.setStrEnterDate(request.getParameter("setEnterDate"));
	/* 入力された退職日 */
	empIdList.setStrRetireDate(request.getParameter("setRetireDate"));
	/* 入力されたステータス */
	empIdList.setStatus(request.getParameter("setStatus"));
	/* ログイン時のIDを取得 */
	empIdList.setInputLoginId(loginUser);
	return empIdList;
	}
}
