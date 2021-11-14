1<%@page import="com.google.gson.JsonObject"%>
<%@page import="kr.co.farmstory2.dao.MemberDao"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String uid = request.getParameter("uid");
	
	
	// 1. 아이디 체크  2. 닉네임체크 3. 이메일 체크 4. 휴대폰 체크
	int result = MemberDao.getInstance().selectCountUserInfo(1,uid);

	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	out.print(json);

%>
