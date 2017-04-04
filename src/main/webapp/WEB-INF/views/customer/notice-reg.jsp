<%@page import="com.newlecture.web.dao.mysql.MySQLNoticeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>으갹갹갹</title>
<link href="../css/customer/style.css" type="text/css" rel="stylesheet"/>
<script src="../js/customer/notice.js"> </script>

</head>
<body>
	<!-- ---------------------------------------------헤 더------------------------------------------------------------ -->
	<!-- 헤더부분 -->
	<!-- ---------------------------------------------비주얼------------------------------------------------------------ -->
	<div id="visual">
		<!-- 비ㅇ주얼 영역 -->
		<div class="content-container"></div>
	</div>


	<!-- -----------------------------------------------바디---------------------------------------------------------- -->


			<main id="main"> <!-- <section id="quick-menu">
						<h2>퀵메뉴</h2>
					</section> -->
				<h2 class="main-title">공지사항 등록</h2>
				<div class="breadcrumb">
					<h3 class="hidden">breadcrumb</h3>
					<ul>
						<li>home</li>
						<li>고객센터</li>
						<li>공지사항</li>
					</ul>
				</div>
				
				<form method="post" enctype="multipart/form-data">
					<table border="1">
						<tbody>
							<tr>
								<th>제목</th>
								<td><input type="text" name="title"/></td>
							</tr>
							<tr>
								<th>파일선택</th>
								<td><input type="file" name ="files"/></td>
							<tr>
								<th>파일선택</th>
								<td><input type="file" name ="files"/></td>
							</tr>
							
							<tr>
								<td colspan="2">
									<textarea rows="20" cols="80" name="content"></textarea>
								</td>
							</tr>
						</tbody>
					</table>
					<div>
						<input type="submit" value="등록" /> <a href="notice.jsp">취소</a>
					</div>
				</form>
			</main>

		</div>
	</div>

	<!-- -----------------------------------------------푸터---------------------------------------------------------- -->
	<!-- <footer id="footer">
		<div class="content-container">

			<h2>회사정보</h2>
			푸터당
			<div>주소서울특별시 마포구 토정로35길 11, 인우빌딩 5층
				266호관리자메일admin@newlecture.com사업자 등록번호132-18-46763 통신 판매업 신고제
				2013-서울강동-0969 호 상호뉴렉처대표박용우전화번호070-4206-4084 [죄송합니다. 당분간 문의내용은 고개센터
				메뉴에서 1:1 문의를 이용해주시기 바랍니다] Copyright ⓒ newlecture.com 2012-2014 All
				Right Reserved. Contact admin@newlecture.com for more information</div>
		</div>
	</footer> -->

</body>
</html>