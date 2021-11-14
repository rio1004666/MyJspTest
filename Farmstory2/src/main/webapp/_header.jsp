<%@page import="kr.co.farmstory2.bean.MemberBean"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%

	/* 세션객체에 있는 회원의 정보를 불러오는 코드입니다.    */
	MemberBean mb = (MemberBean) session.getAttribute("sessMember");

	/* 이 페이지는 공통으로 사용하는 헤더이므로 회원정보가 세션객체에 있는지여부(로그인여부), 로그인 성공여부 등을 확인하기 위한 success 변수를 요청객체로부터 받습니다.*/
	String success = request.getParameter("success");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>팜스토리</title>
    <!-- CSS 코드는 다른 파일에서 코딩해서 불러오도록하였습니다. -->
    <link rel="stylesheet" href="/Farmstory2/css/style.css"/>
    <!-- 주소를 저장하도록 하는 코드를 불러오도록 하였습니다. -->
    <script src="/Farmstory2/js/zipcode.js"></script>
    <!-- 공통적으로 제이쿼리를 사용할 수 있도록하는 주소를 불러오도록 하였습니다. -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- 주소검색을 할 수 있도록 해주는 api 자바스크립트 파일을 불러오도록 하였습니다. -->
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
    	var success = "<%= success %>";
    	/* 각 해당하는 success 유형에 대해 메시지를 알람창을 띄우게 하셨습니다. */
    	if(success == 100){
    		alert('일치하는 회원이 없습니다.\n아이디, 비밀번호를 다시 확인해 주세요.');
    	}else if(success == 101){
    		alert('정상적으로 로그아웃이 되었습니다.');
    	}else if(success == 102){
    		alert('먼저 로그인을 하셔야 합니다.');
    	}else if(success == 103){
    		alert('로그인에 성공하였습니다.');
    	}
    </script>
    
</head>
<body>
    <div id="wrapper">
        <header>            
            <a href="/Farmstory2" class="logo"><img src="/Farmstory2/img/logo.png" alt="로고"/></a>
            <p>
                <a href="/Farmstory2">HOME |</a>
                <!-- 세션객체에 저장된 로그인정보가 없다면 로그인과 회원가입을 하라는 의미로 a태그를 사용하였습니다. -->
                <!-- 세션객체에 로그인 정보가 있다면 로그아웃을 하라는 의미로 a태그를 사용하였습니다. -->
                <% if(mb == null){ %>
                	<a href="/Farmstory2/user/login.jsp">로그인 |</a>
                	<a href="/Farmstory2/user/terms.jsp">회원가입 |</a>
                <% }else{ %>
                	<a href="/Farmstory2/user/logout.jsp">로그아웃 |</a>
                <% } %>
                <a href="/Farmstory2/board/list.jsp?group=Community&cate=qna">고객센터</a>
            </p>
            <img src="/Farmstory2/img/head_txt_img.png" alt="3만원이상 무료배송, 팜카드 10%적립"/>
            <ul class="gnb">
            	 <!-- 각 메뉴에 해당하는 링크를 연결하였습니다 -->
            	 <!-- 각 그룹에 해당되는 카테고리의 글리스트를 불러오기위해 전송방식을 get방식으로 요청하는 동시에 데이터를 보내도록 하였습니다. -->
            	 
                <li>
                	<a href="/Farmstory2/introduction/hello.jsp">팜스토리 소개</a>
                </li>
                <li>
                	<a href="/Farmstory2/board/list.jsp?group=Market&cate=market&success=<%=success%>>">
                	<img src="/Farmstory2/img/head_menu_badge.png" class="badge" alt="30%"/>장보기</a>
                	
                </li>
                <li>
                	<a href="/Farmstory2/board/list.jsp?group=Croptalk&cate=story">농작물이야기</a>
                </li>
                <li>
                	<a href="/Farmstory2/board/list.jsp?group=Event&cate=event">이벤트</a>
                </li>
                <li>
                	<a href="/Farmstory2/board/list.jsp?group=Community&cate=notice">커뮤니티</a>
                </li>
            </ul>
        </header>