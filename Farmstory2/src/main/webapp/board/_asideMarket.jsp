
            
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!-- _asideMarket.jsp 페이지는 장보기에 해당하는 페이지의 측면 페이지로서 
	 장보기페이지에서는 세부카테고리가 하나뿐이라서 상관은 크게 없지만 세부카테고리가 많다면
	 해당되는 카테고리 분류 페이지를 모두 만들어야하므로 이 측면 페이지를 하나만 생성해놓음으로써
	 생산성과 코드의 비약적인 축약이 가능합니다. -->
<%
	/* 서버페이지에서 처리하듯이 jsp페이지 어디서든 request객체에 담긴 매개변수 값을 받아서 처리 할 수 있습니다.*/
	/* 세부카테고리 별로 다르게 랜더링하게 하기 위해서 cate 변수를 사용하였습니다. */
	String cate = request.getParameter("cate");
	
	String title = "";
	/* 전달받은 데이터가 market이라면 다시 장보기라는 한글로 바꾸려고 합니다. */
	if(cate.equals("market")){
		title = "장보기";
	}
%>
<div id="sub" class="cate2">
    <div><img src="/Farmstory2/img/sub_top_tit2.png" alt="MARKET"/></div>
    <section>
        <aside>
            <img src="/Farmstory2/img/sub_aside_cate2_tit.png" alt="장보기"/>
            <ul>
            	<!-- HTML 태크들과 함께 어울릴 수 있는 표현식입니다. 여기서 장보기 카테고리는 market하나이므로 크게 상관은 없습니다만 
            	         여러개의 카테고리라면 각 카테고리에 해당하면 그에 맞는 css스타일이 적용되어서 on으로 이미지가 셋팅되게 할 것입니다.  
            	         여기서 a 태그에서도 항상 group과 cate 데이터를 같이 보내주어야 글리스트 페이지에서 받을 수 있습니다. 누락되면 널값 예외가 발생합니다. -->
                <li class="<%= cate.equals("market")  ? "on" : "off" %>">
                	<a href="/Farmstory2/board/list.jsp?group=Market&cate=market">장보기</a>
                </li>                        
            </ul>
        </aside>
        <article>
            <nav>
            	<!-- 이미지와 대체 텍스트도 cate와 title에 따른 값으로 정하도록 하였습니다. 이미지명도 그렇게 수정하였습니다.  -->
                <img src="/Farmstory2/img/sub_nav_tit_cate2_<%= cate %>.png" alt="<%= title %>"/>
                <p>
                    HOME > 장보기 > <strong><%= title %></strong>
                </p>
            </nav>