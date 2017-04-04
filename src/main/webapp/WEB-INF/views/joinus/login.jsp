<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<a href="../index.html">메인 페이지</a>
		<a href="">로그인</a>
		<a href="join.html">회원가입</a>
		<a href="../customer/notice.html">고객센터</a>
	</header>
	<h1>로그인</h1>
	<form method="post">
		<fieldset>
		
			<table>
				<c:if test="${not empty validate && not validate}">
				<tr>
					<td colspan="2" style="color: red;">아이디 또는 비번이 유효하지않습니다.</td>
				</tr>
				</c:if>
				<tr>
					<th>아이디: </th>
					<td><input name ="id"type="text"/></td>
				</tr>
				<tr>
					<th>비밀번호: </th>
					<td><input name ="pwd"type="password"/></td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="로그인"/>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>