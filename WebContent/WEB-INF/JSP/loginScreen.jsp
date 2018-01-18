<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員管理システム</title>
<link rel="stylesheet" type="text/css" href="./style.css">
</head>
<body>
	<div class="alignCenter">
		<h1>社員管理システム</h1>
			<!-- エラーメッセージ -->
			<p>
				<font class="errMessage">
					<c:forEach var="errMessage" items="${errMessage}">
						<c:out value="${errMessage}" />
						<br>
					</c:forEach>
				</font>
			</p>
	</div>
	<br>
	<form action="login" method="post">
		<table id="loginTable">
			<tr>
				<td>
					<table>
						<tr>
							<td>ログインID</td>
							<td>
								<div class="alignLeft">
									<input type="text" maxlength='20' name="loginId" size="23" value="${loginId}">
								</div>
							</td>
						</tr>
						<tr>
							<td>パスワード</td>
							<td>
								<div class="alignLeft">
									<input type="password" maxlength='20' name="password" size="24"  value="${password}">
								</div>
							</td>
						</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</table>
		<div class="alignCenter">
			<br> <input type="submit" value="ログイン">
		</div>
	</form>
</body>
</html>