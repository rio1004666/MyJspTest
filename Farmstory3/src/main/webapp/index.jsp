<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="./_header.jsp" %>
<script>
	var success = ${requestScope.success}
	if(success == 104){
		alert('로그인 성공하셨습니다!!!')
	}
	
</script>
<style>
	#help{
		posisition: relative;
		width: 340px;
		
	}
	#help .btns{
		
		position: absolute;
		left: 320px;
		bottom: -160px;
	
	
	}

</style>
<main>

    <div class="slider">

        <ul>
            <li><img src="/Farmstory3/img/main_slide_img1.jpg" alt="슬라이더1"></li>
            <li><img src="/Farmstory3/img/main_slide_img2.jpg" alt="슬라이더2"></li>
            <li><img src="/Farmstory3/img/main_slide_img3.jpg" alt="슬라이더3"></li>
        </ul>

        <img src="/Farmstory3/img/main_slide_img_tit.png" alt="사람과 자연을 사랑하는 팜스토리"/>

        <div class="banner">
            <img src="/Farmstory3/img/main_banner_txt.png" alt="GRAND OPEN"/>
            <img src="/Farmstory3/img/main_banner_tit.png" alt="팜스토리 오픈기념 30% 할인 이벤트"/>
            <img src="/Farmstory3/img/main_banner_img.png" alt="과일"/>
        </div>
    </div>

    <div class="quick">
        <a href="#"><img src="/Farmstory3/img/main_banner_sub1_tit.png" alt="오늘의 식단"></a>
        <a href="#"><img src="/Farmstory3/img/main_banner_sub2_tit.png" alt="나도 요리사"></a>                
    </div>

    <div class="latest">
        <div>
            <a href="#"><img src="/Farmstory3/img/main_latest1_tit.png" alt="텃밭 가꾸기"/></a>
            <img src="/Farmstory3/img/main_latest1_img.jpg" alt="이미지"/>
            <table border="0">
            <c:forEach var="grow" items="${grows}">
                <tr>
                    <td>></td>
                    <td><a href="/Farmstory3/board/view.do?group=Croptalk&cate=grow&seq=${grow.seq}">${grow.title}</a></td>
                    <td>${grow.rdate.substring(2,10)}</td>
                </tr>
            </c:forEach>
            </table>
        </div>
        <div>
            <a href="#"><img src="/Farmstory3/img/main_latest2_tit.png" alt="귀농학교"/></a>
            <img src="/Farmstory3/img/main_latest2_img.jpg" alt="이미지"/>
            <table border="0">
                <c:forEach var="school" items="${schools}">
                <tr>
                    <td>></td>
                    <td><a href="/Farmstory3/board/view.do?group=Croptalk&cate=school&seq=${school.seq}">${school.title}</a></td>
                    <td>${school.rdate.substring(2,10)}</td>
                </tr>
            </c:forEach>
            </table>
        </div>
        <div>
            <a href="#"><img src="/Farmstory3/img/main_latest3_tit.png" alt="농작물 이야기"/></a>
            <img src="/Farmstory3/img/main_latest3_img.jpg" alt="이미지"/>
            <table border="0">
                <c:forEach var="story" items="${storys}">
                <tr>
                    <td>></td>
                    <td><a href="/Farmstory3/board/view.do?group=Croptalk&cate=story&seq=${story.seq}">${story.title}</a></td>
                    <td>${story.rdate.substring(2,10)}</td>
                </tr>
            </c:forEach>
            </table>
        </div>
        
    </div>

    <div class="info">
        <div id="help">
        	<div> <img src="./img/main_sub2_cs_tit.png" class="tit" alt="고객센터 안내"/></div>
            <div class="cs">
            	
            	<div>
            		<img src="./img/main_sub2_cs_img.png" alt="이미지"/>
	                <img src="./img/main_sub2_cs_txt.png" alt="1666-777"/>
	                
	                <ul>
	                    <li>평일: AM 09:00 ~ PM 06:00</li>
	                    <li>점심: PM 12:00 ~ PM 01:00</li>
	                    <li>토, 일요일, 공휴일 휴무</li>
	                </ul>
            	</div>
                
            </div>
			<div class="btns">
				<ul>
					<li><a href="/Farmstory3/board/list.do?group=Community&cate=qna"><img src="./img/main_sub2_cs_bt1.png" alt="고객문의"></a></li>
					<li><a href="/Farmstory3/board/list.do?group=Community&cate=faq"><img src="./img/main_sub2_cs_bt2.png" alt="자주묻는질문"></a></li>
					<li><a href="#"><img src="./img/main_sub2_cs_bt3.png" alt="배송조회"></a></li>
				</ul>
			</div>
        </div>
        
        <div>
            <img src="/Farmstory3/img/main_sub2_account_tit.png" class="tit" alt="계좌안내"/>
            <p class="account">
                기업은행 123-456789-01-01-012<br />
                국민은행 01-1234-56789<br />
                우리은행 123-456789-01-01-012<br />
                하나은행 123-456789-01-01<br />
                예 금 주 (주)팜스토리
            </p>
        </div>
        <div>
            <div id="tabs">
                <ul>
                    <li><a href="#tabs-1">공지사항</a></li>
                    <li><a href="#tabs-2">1:1 고객문의</a></li>
                    <li><a href="#tabs-3">자주묻는 질문</a></li>
                </ul>
                <div id="tabs-1">
                    <ul>
                    	<c:forEach var="notice" items="${notice_articles}">	
                    		<li><a href="/Farmstory3/board/view.do?group=Community&cate=notice&seq=${notice.seq}">· ${notice.title}</li>
                    	</c:forEach>
                    </ul>
                </div>
                <div id="tabs-2">
                    <ul>
                        <c:forEach var="qna" items="${qna_articles}">	
                    		<li><a href="/Farmstory3/board/view.do?group=Community&cate=qna&seq=${qna.seq}">· ${qna.title}</li>
                    	</c:forEach>
                    </ul>
                </div>
                <div id="tabs-3">
                    <ul>
                        <c:forEach var="faq" items="${faq_articles}">	
                    		<li><a href="/Farmstory3/board/view.do?group=Community&cate=faq&seq=${faq.seq}">· ${faq.title}</li>
                    	</c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</main>
<%@ include file="./_footer.jsp" %>