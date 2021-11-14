<%@page import="kr.co.farmstory2.dao.ArticleDao"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	
	String seq     = request.getParameter("seq");
	String title   = request.getParameter("title");
	String content = request.getParameter("content");
	String uid = request.getParameter("uid");
	
	
	String group = request.getParameter("group");
	String cate  = request.getParameter("cate");
	
	ArticleDao.getInstance().updateArticle(title, content, seq);
	
	response.sendRedirect("/Farmstory2/board/view.jsp?group="+group+"&cate="+cate+"&seq="+seq+"&uid="+uid);
%>