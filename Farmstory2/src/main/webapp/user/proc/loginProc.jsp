<%@page import="kr.co.farmstory2.bean.MemberBean"%>
<%@page import="kr.co.farmstory2.dao.MemberDao"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
	/*  요청주소에서 보낸 데이터를 utf-8인코딩으로 셋팅하기 위해서 request객체에 있는 인코더메서드를 사용하였습니다. */
	request.setCharacterEncoding("utf-8");
	/*  요청주소에서 보낸 데이터의 값을 가져오기 위해 request객체에서 키인 아이디와 비밀번호를 셋팅합니다. */
	String uid  = request.getParameter("uid");
	String pass = request.getParameter("pass");
	
	/* 그 정보가 데이터 베이스에 존재한다면 해당 회원의 정보 모두를 가져와서 멤버빈 객체에 저장합니다. */
	MemberBean mb = MemberDao.getInstance().selectMember(uid, pass);
	
	if(mb != null){ /* 위의 결과로 멤버빈 객체에 널값이 아니라면 해당하는 회원이 있다는 의미이므로 */
		/* 세션객체에 멤버빈 객체를 저장합니다. 이 세션객체를 가지고 글을 쓰거나 댓글을 작성할 수 있습니다. */
		session.setAttribute("sessMember", mb);
		/* 그리고 로그인이 성공하였다는 메시지를 전달하기 위해 돌아갈 페이지에 success=103이라고 임의의 데이터를 전송합니다. 
		      이것을 헤더페이지에서 판단해서 알람창을 띄웁니다*/
		response.sendRedirect("/Farmstory2?success=103");	
		
	}else{
		/* 해당하는 아이디나 비밀번호가 틀려서 멤버빈객체에 정보를 담을 수 없다면 로그인 페이지로 다시 가게 되며
		   success=100이라는 데이터를 전송하여 다시 아이디와 비밀번호를 입력하고 알람창을 띄우게 됩니다.*/
		response.sendRedirect("/Farmstory2/user/login.jsp?success=100");
	}
%>