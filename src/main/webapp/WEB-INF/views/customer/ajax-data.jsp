<%-- <%@page import="com.newlecture.web.data.entity.NoticeView"%>
<%@page import="com.newlecture.web.dao.NoticeDao"%>
<%@page import="com.google.gson.Gson"%>

<%@page import="java.util.List"%>
<%@page import="com.newlecture.web.dao.mysql.MySQLNoticeDao"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

String _page = request.getParameter("p");
	String _field = request.getParameter("f");
	String _query = request.getParameter("q");

	int pg = 2;
	String field = "TITLE";
	String query = "";

	if (_page != null && !_page.equals("")) //값이 넘겨진 것이 있다면 && 
		pg = Integer.parseInt(_page);

	if (_field != null && !_field.equals("")) //값이 넘겨진 것이 있다면 && 
		field = _field;

	if (_query != null && !_query.equals("")) //값이 넘겨진 것이 있다면 && 
		query = _query;

	NoticeDao noticeDao = new MySQLNoticeDao();
	List<NoticeView> list = noticeDao.getList(pg, field, query);
	
	Gson gson = new Gson();
	String json = gson.toJson(list);
	out.println(json);
	%>
	
	
	
	 --%>