<%@page import="com.google.gson.JsonObject"%>
<%@page import="kr.co.farmstory2.dao.ArticleDao"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	
	String seq     = request.getParameter("seq");
	String comment = request.getParameter("comment");
	
	// 댓글 수정
	int result = ArticleDao.getInstance().updateComment(comment, seq);
	
	// Json 생성
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	
	out.print(json);
%>