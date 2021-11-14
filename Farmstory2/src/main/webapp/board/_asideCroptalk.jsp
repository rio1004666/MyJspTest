<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String cate = request.getParameter("cate");
	
	String title = "";
	/* 앞서 설명드렸던 것처럼 카테고리 분류가 세분화된다면 
	     농작물 이야기 페이지가 3개의 페이지로 별도로 만들어야 합니다. 
	     하지만 cate라는 변수를 설정해주므로써 각 카테고리에 해당하는 내용만 바꾸어 주면 깔끔하게 코딩할 수 있습니다. */
	if(cate.equals("story")){
		title = "농작물이야기";
	}else if(cate.equals("grow")){
		title = "텃밭가꾸기";
	}else if(cate.equals("school")){
		title = "귀농학교";
	}
%>
<div id="sub" class="cate3">
    <div><img src="/Farmstory2/img/sub_top_tit3.png" alt="CROP TALK"/></div>
    <section>
        <aside>
        	<!-- 각 카테고리에 해당하는 이미지를 선택하려고 합니다. 
       			 cate라는 변수의 값이 변하므로 그에 해당되는 이미지가 선택될 것입니다. -->
            <img src="/Farmstory2/img/sub_aside_cate3_tit.png" alt="농작물이야기"/>
            <ul>
                <li class="<%= cate.equals("story")  ? "on" : "off" %>">
                	<a href="/Farmstory2/board/list.jsp?group=Croptalk&cate=story">농작물이야기</a></li>  
                <li class="<%= cate.equals("grow")   ? "on" : "off" %>">
                	<a href="/Farmstory2/board/list.jsp?group=Croptalk&cate=grow">텃밭가꾸기</a></li> 
                <li class="<%= cate.equals("school") ? "on" : "off" %>">
               		<a href="/Farmstory2/board/list.jsp?group=Croptalk&cate=school">귀농학교</a></li>                        
            </ul>
        </aside>
        <article>
            <nav>    <!-- 여기서도 다른 이미지와 다른 대체 텍스트를 선택하려고 합니다.  -->
                <img src="/Farmstory2/img/sub_nav_tit_cate3_<%= cate %>.png" alt="<%= title %>"/>
                <p>
                    HOME > 농작물이야기 > <strong><%= title %></strong>
                </p>
            </nav>
