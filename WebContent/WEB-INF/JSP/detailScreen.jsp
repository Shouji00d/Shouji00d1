<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員詳細</title>
<link rel="stylesheet" type="text/css" href="./style.css">
</head>
<body>
	<table>
		<tr>
			<td>
				<h2 class="alignCenter">社員詳細</h2>
					<div class="alignCenter">
						<font class="errMessage">
							<c:forEach var="errMessage" items="${errMessage}">
								<c:out value="${errMessage}" />
								<br>
							</c:forEach>
						</font>
					</div>
				<form action="detail" method="post">
					<input type="hidden" name="setEmployeeId" value="${employee.employeeId}">
					<table id="detailTable">
						<col span="1" id="detailTrColor">
						<tr>
							<td>氏名</td>
							<td class="alignLeft"><input type="text" name="setName" maxlength='20' size="14" value="${employee.name}"></td>
						</tr>
						<tr>
							<td>氏名(ひらがな)</td>
							<td class="alignLeft"><input type="text" name="setNameHiragana" maxlength='20' size="14" value="${employee.nameHiragana}"></td>
						</tr>
						<tr>
							<td>生年月日</td>
							<td class="alignLeft"><input type="text" name="setBirthday" maxlength='10' size="14" value="${employee.strBirthday}">
							</td>
						</tr>
						<tr>
							<td>性別</td>
							<td class="alignLeft"><c:forEach var="SexEnum" items="${SexEnum}">
									<input type="radio" name="setSex" value="${SexEnum.value}" <c:if test="${employee.sex == SexEnum.value}"> checked</c:if>>
									${SexEnum.name}</c:forEach></td>
						</tr>
						<tr>
							<td>メールアドレス</td>
							<td class="alignLeft"><input type="text" name="setMailAddress" maxlength='50' size="20" value="${employee.mailAddress}"></td>
						</tr>
						<tr>
							<td>電話番号</td>
							<td class="alignLeft"><input type="text" name="setTelephoneNumber" maxlength='13' size="14" value="${employee.telephoneNumber}"></td>
						</tr>
						<tr>
							<td>所属会社</td>
							<td class="alignLeft"><select size="1" name="setCompanyInfoId">
									<c:forEach var="comp" items="${companyList}">
										<option <c:if test="${comp.companyId == employee.companyInfoId}"> selected</c:if> value="${comp.companyId}">
										<c:out value="${comp.abbreviation}" /></option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td>担当管理営業</td>
							<td class="alignLeft"><input type="text" name="setBusinessManager" maxlength='20' size="14" value="${employee.businessManager}"></td>
						</tr>
						<tr>
							<td>事業部</td>
							<td class="alignLeft"><select size="1" name="setDepartment">
									<c:forEach var="DepartmentList" items="${DepartmentEnum}">
										<option <c:if test="${employee.department == DepartmentList.value}"> selected</c:if> value="${DepartmentList.value}">
										${DepartmentList.name}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td>稼働状況</td>
							<td class="alignLeft"><c:forEach var="CommissioningStatusList" items="${CommissioningStatusEnum}">
									<input type="radio" name="setCommissioningStatus" value="${CommissioningStatusList.value}"
										<c:if test="${employee.commissioningStatus == CommissioningStatusList.value}"> checked</c:if>>${CommissioningStatusList.name}
									</c:forEach></td>
						</tr>
						<tr>
							<td>入社日</td>
							<td class="alignLeft"><input type="text" name="setEnterDate" maxlength='10' size="14" value="${employee.strEnterDate}">
							</td>
						</tr>
						<tr>
							<td>退職日</td>
							<td class="alignLeft"><input type="text" name="setRetireDate" maxlength='10' size="14" value="<c:out value="${employee.strRetireDate}" />"></td>
						</tr>
						<tr>
							<td>ステータス</td>
							<td class="alignLeft"><select size="1" name="setStatus">
									<c:forEach var="StatusList" items="${StatusEnum}">
										<option
											<c:if test="${employee.status == StatusList.value}"> selected</c:if> value="${StatusList.value}">${StatusList.name}
										</option>
									</c:forEach>
							</select></td>
						</tr>
					</table>
					<br>
					<div class="alignCenter">
						<!-- 一覧画面からのリンクで条件分岐 -->
						<c:if test="${type == 'add'}">
							<input type="hidden" name="type" value="add">
							<input type="submit" class="detailButton" value="登録" onclick='return confirm("本当に登録しますか");'>
						</c:if>
						<c:if test="${type == 'update'}">
							<input type="hidden" name="type" value="update">
							<input type="submit" class="detailButton" value="更新" onclick='return confirm("本当に更新しますか");'>
						</c:if>
						<input type="submit" class="detailButton" value="戻る" onClick="form.action='list'">
					</div>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>