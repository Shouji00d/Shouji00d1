package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;

/**
 * 削除のサーブレットクラス
 *
 * @author s.miyagi
 */
@WebServlet("/delete")
public class Delete extends HttpServlet {
	/** シリアライズ */
	private static final long serialVersionUID = 1L;

	/**
	 * 社員情報＆社員状況テーブルから物理削除するサーブレットクラス--------------------------①
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// リクエストパラメータの取得
		String empId = request.getParameter("empId");

		// 削除する社員IDを受け渡して、削除メソッドを実行
		EmployeeDAO employeeDAO = new EmployeeDAO();
		employeeDAO.delete(empId);

		// リダイレクト
		response.sendRedirect("/employeeManagementSystem/list");
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
