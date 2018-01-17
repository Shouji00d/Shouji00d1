package servlet;

import java.util.List;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CompanyInfoDAO;
import dao.EmployeeDAO;
import enumeration.SexEnum;
import enumeration.DepartmentEnum;
import enumeration.CommissioningStatusEnum;
import enumeration.StatusEnum;

import model.CompanyInfo;
import model.Employee;
import model.LogicEmployeeChanger;
import model.LogicErrMessage;
import model.LogicSetEmployee;

/**
 * 詳細画面のサーブレットクラス
 *
 * @author s.miyagi
 */
@WebServlet("/detail")
public class Detail extends HttpServlet {
	/** シリアライズ */
	private static final long serialVersionUID = 1L;

	/**
	 * 詳細画面の前処理をするgetメソッド--------------------------①
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ログインしているか確認するため
		// セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		String loginUser = (String) session.getAttribute("loginUser");

		if (loginUser == null) {// ログインしていない場合
			// Login.Javaへリダイレクト
			response.sendRedirect("/employeeManagementSystem/login");
		} else {

			// リクエストパラメータの取得
			request.setCharacterEncoding("UTF-8");
			String type = null;
			String empId = request.getParameter("empId");

			// empIdが何も設定されていない場合
			if (empId == null) {
				// typeに新規作成のフラグを立てる
				type = "add";
			} else {
				type = "update";
			}

			// リクエストスコープに保存
			request.setAttribute("type", type);

			// 社員情報＆社員状況テーブルから社員IDで抽出して、リクエストスコープに保存
			EmployeeDAO employeeDAO = new EmployeeDAO();
			Employee employee = employeeDAO.findByEmpId(empId);

			// 変換クラス
			LogicEmployeeChanger logicEmployeeChanger = new LogicEmployeeChanger();
			logicEmployeeChanger.getEmployeeChange(employee);
			request.setAttribute("employee", employee);

			// 会社名の略称を出す処理
			// 会社社員のリストを取得して、リクエストスコープに保存
			CompanyInfoDAO companyInfoDAO = new CompanyInfoDAO();
			List<CompanyInfo> companyList = companyInfoDAO.findByCompanyInfo();
			request.setAttribute("companyList", companyList);

			// Enum化したコードを呼び出して、リクエストスコープに保存
			request.setAttribute("SexEnum", SexEnum.values());
			request.setAttribute("DepartmentEnum", DepartmentEnum.values());
			request.setAttribute("CommissioningStatusEnum",
					CommissioningStatusEnum.values());
			request.setAttribute("StatusEnum", StatusEnum.values());

			// 詳細画面へのフォワード
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/WEB-INF/JSP/detailScreen.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * 詳細画面の後処理をするpostメソッド--------------------------②
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ログインしているか確認するため
		// セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		String loginUser = (String) session.getAttribute("loginUser");

		if (loginUser == null) {// ログインしていない場合
			// Login.Javaへリダイレクト
			response.sendRedirect("/employeeManagementSystem/login");
		} else {

			// リクエストパラメータの取得
			request.setCharacterEncoding("UTF-8");
			/* タイプ（addまたはupdate） */
			String type = request.getParameter("type");

			// 入力した値を入れるロジッククラス
			LogicSetEmployee logicSetEmployee = new LogicSetEmployee();
			Employee employee = logicSetEmployee.input(request, loginUser);

			// 未入力・誤入力があるかチェック
			LogicErrMessage logicErrMessage = new LogicErrMessage();
			List<String> errMessage = logicErrMessage
					.detailInputCheck(employee);

			// 未入力・誤入力がある場合の処理
			if (!errMessage.isEmpty()) {
				// エラーメッセージをスコープへ保存
				request.setAttribute("errMessage", errMessage);
				// ボタンを表示させるためスコープへ保存
				request.setAttribute("type", type);
				// 詳細画面に戻す為の変換クラス
				LogicEmployeeChanger logicEmployeeChanger = new LogicEmployeeChanger();
				logicEmployeeChanger.reEmployeeChange(employee);
				// DAOに送られるハズのデータを、詳細画面に戻す
				request.setAttribute("employee", employee);

				// 会社名の略称を出す処理
				// 会社社員のリストを取得して、リクエストスコープに保存
				CompanyInfoDAO companyInfoDAO = new CompanyInfoDAO();
				List<CompanyInfo> companyList = companyInfoDAO
						.findByCompanyInfo();
				request.setAttribute("companyList", companyList);

				// Enum化したコードを呼び出して、リクエストスコープに保存
				request.setAttribute("SexEnum", SexEnum.values());
				request.setAttribute("DepartmentEnum", DepartmentEnum.values());
				request.setAttribute("CommissioningStatusEnum",
						CommissioningStatusEnum.values());
				request.setAttribute("StatusEnum", StatusEnum.values());

				// 詳細画面にフォワード
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/WEB-INF/JSP/detailScreen.jsp");
				dispatcher.forward(request, response);
			}
			// 未入力・誤入力がない場合の処理
			else {
				// 変換クラス
				LogicEmployeeChanger logicEmployeeChanger = new LogicEmployeeChanger();
				logicEmployeeChanger.setEmployeeChange(employee);

				EmployeeDAO dao = new EmployeeDAO();

				if (employee.getInputEmployeeId().equals("")) { // 登録ボタンが押された場合
					// 値を受け渡してSQLのINSERT文を実行
					dao.insertByEmployee(employee);
				} else {// 更新ボタンが押された場合
					// 値を受け渡してSQLのUPDATE文を実行
					dao.updateByEmployee(employee);
				}
				// list.Javaへリダイレクト
				response.sendRedirect("/employeeManagementSystem/list");
			}
		}
	}
}