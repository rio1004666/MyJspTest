<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String cate = request.getParameter("cate");
	String title = "";
	/* 이와 같이 카테고리 분류가 많다면 5개의 페이지를 커뮤니티에 만들어야합니다.
	      이를 피하기 위해 간단하게 페이지 명을 전달하는 방식으로 코딩하였습니다.*/
	if(cate.equals("notice")){
		title = "공지사항";
	}else if(cate.equals("menu")){
		title = "오늘의식단";
	}else if(cate.equals("faq")){
		title = "고객문의";
	}else if(cate.equals("chef")){
		title = "나도요리사";
	}else if(cate.equals("qna")){
		title = "자주묻는질문";
	}
%>
<div id="sub" class="cate5">
    <div><img src="/Farmstory2/img/sub_top_tit3.png" alt="Community"/></div>
    <section>
        <aside>
            <img src="/Farmstory2/img/sub_aside_cate5_tit.png" alt="농작물이야기"/>
            <ul>
                <li class="<%= cate.equals("notice")  ? "on" : "off" %>">
                	<a href="/Farmstory2/board/list.jsp?group=Community&cate=notice">공지사항</a></li>                        
                <li class="<%= cate.equals("menu")   ? "on" : "off" %>">
                	<a href="/Farmstory2/board/list.jsp?group=Community&cate=menu">오늘의식단</a></li>                        
                <li class="<%= cate.equals("chef") ? "on" : "off" %>">
                	<a href="/Farmstory2/board/list.jsp?group=Community&cate=chef">나도요리사</a></li>                        
                <li class="<%= cate.equals("faq") ? "on" : "off" %>">
                	<a href="/Farmstory2/board/list.jsp?group=Community&cate=faq">고객문의</a></li>                        
                <li class="<%= cate.equals("qna") ? "on" : "off" %>">
                	<a href="/Farmstory2/board/list.jsp?group=Community&cate=qna">자주묻는질문</a></li>                        
            </ul>
        </aside>
        <article>
            <nav>
                <img src="/Farmstory2/img/sub_nav_tit_cate5_<%= cate %>.png" alt="<%= title %>"/>
                <p>
                    HOME > 커뮤니티 > <strong><%= title %></strong>
                </p>
            </nav>