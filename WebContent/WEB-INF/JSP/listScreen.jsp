<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>一覧画面</title>
<link rel="stylesheet" type="text/css" href="./style.css">
</head>
<body>
	<div class="alignCenter">
		<div class="marginLeft50">
			<a href="/employeeManagementSystem/logout">ログアウト</a>
		</div>
		<h1>社員一覧</h1>
		<div class="marginLeft50">
			<form action="/employeeManagementSystem/detail" method="get">
				<input type="hidden" name="type" value="add">
				<input type="submit" value="新規登録">
			</form>
		</div>
		<br>
		<table id="listTable">
			<tr id="listTrColor">
				<td><label>No</label></td>
				<td><label>会社</label></td>
				<td><label>事業部</label></td>
				<td><label>氏名</label></td>
				<td><label>氏名(ひらがな)</label></td>
				<td><label>年齢</label></td>
				<td><label>担当管理営業</label></td>
				<td><label>入社日</label></td>
				<td><label>稼働状況</label></td>
				<td><label>詳細</label></td>
				<td><label>削除</label></td>
			</tr>
			<c:forEach var="emp" items="${employee}" varStatus="No">
				<tr class="alignCenter">
					<td><label><c:out value="${No.count}" /></label></td>
					<td><label><c:out value="${emp.abbreviation}" /></label></td>
					<td><label><c:out value="${emp.departmentConversion}" /></label></td>
					<td><label><c:out value="${emp.name}" /></label></td>
					<td><label><c:out value="${emp.nameHiragana}" /></label></td>
					<td><label><c:out value="${emp.age}" /></label></td>
					<td><label><c:out value="${emp.businessManager}" /></label></td>
					<td><label><c:out value="${emp.strEnterDate}" /></label></td>
					<td><label><c:out value="${emp.commissioningStatusConversion}" /></label></td>
					<td><a href="/employeeManagementSystem/detail?empId=${emp.employeeId}">詳細 </a></td>
					<td><a href="/employeeManagementSystem/delete?empId=${emp.employeeId}"
					onclick='var value = "<c:out value="${emp.abbreviation}" /><c:out value="${emp.name}" />を削除しますか？";return confirm(value);'>削除 </a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>