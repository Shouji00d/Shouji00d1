package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginInfoDAO;

import model.LogicErrMessage;

/**
 * ログイン画面の処理をするサーブレットクラス
 *
 * @author s.miyagi
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	/** シリアライズ */
	private static final long serialVersionUID = 1L;

	/**
	 * ログイン画面の前処理をするgetメソッド--------------------------①
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ログイン画面へ
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/WEB-INF/JSP/loginScreen.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * ログイン画面の後処理をするpostメソッド--------------------------①
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");

		// ログイン処理
		// DAOの方でログインID＆パスワードを条件に表が取得できるか否かで判定する
		LoginInfoDAO loginInfoDAO = new LoginInfoDAO();
		String daoLoginId = loginInfoDAO.findByLoginId(loginId, password);

		// ログイン成功時の処理
		if (daoLoginId != null) {
			// ユーザー情報をセッションスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", daoLoginId);
			// ログイン成功時はlistへ
			response.sendRedirect("/employeeManagementSystem/list");

		} else {
			// 未入力・誤入力があるかチェック
			LogicErrMessage LogicErrMessage = new LogicErrMessage();
			List<String> errMessage = LogicErrMessage.loginInputCheck(loginId, password);
			// エラーメッセージをスコープへ保存
			request.setAttribute("errMessage", errMessage);
			// DAOに送られるハズのデータを、詳細画面に戻す
			request.setAttribute("loginId", loginId);
			request.setAttribute("password", password);
			// ログイン失敗結果画面にフォワード
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/WEB-INF/JSP/loginScreen.jsp");
			dispatcher.forward(request, response);
		}
	}

}
