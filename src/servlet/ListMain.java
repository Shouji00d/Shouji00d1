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

import model.LogicEmployeeChanger;
import model.Employee;
import dao.EmployeeDAO;

/**
 * 一覧画面の処理をするサーブレットクラス
 *
 * @author s.miyagi
 *
 */
@WebServlet("/list")
public class ListMain extends HttpServlet {
	/** シリアライズ */
	private static final long serialVersionUID = 1L;

	/**
	 * 一覧画面の処理をするgetメソッド--------------------------①
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

			// 社員情報＆状況のリストを取得して、リクエストスコープに保存
			EmployeeDAO dao = new EmployeeDAO();
			List<Employee> employeeList = dao.findByEmployee();
			// リストの変換処理
			LogicEmployeeChanger logicEmployeeChanger = new LogicEmployeeChanger();
			employeeList = logicEmployeeChanger
					.listEmployeeChange(employeeList);
			// リクエストスコープに保存
			request.setAttribute("employee", employeeList);

			// フォワード
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/WEB-INF/JSP/listScreen.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * doGetに渡す以外、何もしていません
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// doGet()メソッドに渡す
		doGet(request, response);
	}

}
