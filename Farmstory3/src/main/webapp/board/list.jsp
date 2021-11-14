<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../_header.jsp" %>
<jsp:include page="./_aside${group}.jsp"/>
        <section id="board" class="list">
            <article>
            	<c:if test="${sessMember != null}">
            		<div>
                    	${sessMember.nick}님 반갑습니다.
                    	<a href="/Farmstory3/member/logout.do" class="logout">[로그아웃]</a>
                	</div>
            	</c:if>
                <table border="0">
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>글쓴이</th>
                        <th>날짜</th>
                        <th>조회</th>
                    </tr>
                    <c:forEach var="article" items="${articles}">
	                    <tr>
	                    	<!--  감소연산자가 안되면 그냥 감소시킨다  -->
	                        <td>${pageStartNum = pageStartNum - 1}</td>
	                        <td><a href="/Farmstory3/board/view.do?group=${group}&cate=${cate}&seq=${article.seq}">${article.getTitle()}</a>&nbsp;[${article.comment}]</td>
	                        <td>${article.nick}</td>
	                        <td>${article.rdate}</td>
	                        <td>${article.hit}</td>
	                    </tr>
                    </c:forEach>
                    
                </table>
            </article>
            <!-- 페이지 네비게이션 -->
            <div class="paging">
            		<c:if test="${groups[0]  >  1}">
            				<a href="/Farmstory3/board/list.do?group=${group}&cate=${cate}&pg=${groups[0]-1}" class="prev">이전</a>
					</c:if>        			
            		<c:forEach var="i" begin="${groups[0]}"  end="${groups[1]}">
            		<!-- currentPage값을 가져와야만 하이라이팅가능하다  -->
            			<a href="/Farmstory3/board/list.do?group=${group}&cate=${cate}&pg=${i}" class="num ${currentPage == i ? 'current' : 'off' }">${i}</a>
	                </c:forEach>
	                <c:if test="${groups[1] < lastPageNum }">
	                	<a href="/Farmstory3/board/list.do?group=${group}&cate=${cate}&pg=${groups[1] + 1}" class="next">다음</a>
	                </c:if>
            </div>
            <!-- 글쓰기 버튼 -->
            <a href="/Farmstory3/board/write.do?group=${group}&cate=${cate}" class="btnWrite">글쓰기</a>

        </section> 
<%@ include file="../_footer.jsp"%>