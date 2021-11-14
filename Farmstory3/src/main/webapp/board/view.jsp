<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../_header.jsp" %> 
<jsp:include page="./_aside${group}.jsp">
	<jsp:param name="cate" value="${cate}"/>
</jsp:include>
<script>

	$(function(){
		
		$('.btnDelete').click(function(){
			var result = confirm('정말 글을 삭제 하시겠습니까?');
			return result;
		});
		
		$('.btnCommentDel').click(function(){
			var result = confirm('정말 댓글을 삭제 하시겠습니까?');
			return result;
		});
		
		
        
    
		
	});
</script>
        <section id="board" class="view">
            <table>
                <tr>
                    <td>제목</td>
                    <td><input type="text" name="title"  value=" ${article.title}"readonly/></td>
                </tr>            
                <c:if test="${article.file eq 1}">
                	<tr>
		                <td>첨부파일</td>	                    
			            <td>
			            	<!-- 파일번호는 글객체에 있다!!!!!! -->
			                   <a href="/Farmstory3/board/fileDownload.do?fseq=${article.fb.fseq}">${article.fb.oriName}</a>
			                   <span>${article.fb.download} 회 다운로드</span>
			            </td>	 
               		</tr>
                </c:if>    
                
                <tr>
                    <td>내용</td>
                    <td>
                        <textarea name="content" readonly>${article.content}</textarea>
                    </td>
                </tr>
            </table>
            
	            <div>
	            	<c:if test="${sessMember.uid eq article.uid}">
	                	<a href="/Farmstory3/board/delete.do?group=${group}&cate=${cate}&seq=${article.seq}" class="btnDelete">삭제</a>
	                	<a href="/Farmstory3/board/modify.do?group=${group}&cate=${cate}&seq=${article.seq}&title=${article.title}&content=${article.content}" class="btnModify">수정</a>
	                </c:if>
	                <a href="/Farmstory3/board/list.do?group=${group}&cate=${cate}" class="btnList">목록</a>
	            </div>  
            
            
            <!-- 댓글리스트 -->
            <section class="commentList">
                <h3>댓글목록</h3>
                
                <c:forEach var="comm"  items="${comments}">
	                <article class="comment">
	                	<span>
	                        <span>${comm.nick}</span>
	                        <span>${comm.rdate}</span>
	                    </span>
	                    <textarea name="comment" data-seq="${comm.seq}" readonly>${comm.content}</textarea>
	                    	<div>
	                    	 	<c:if test="${sessMember.uid eq comm.uid}">
		                        	<a href="/Farmstory3/board/deleteComment.do?group=${group}&cate=${cate}&seq=${comm.seq}&parent=${comm.parent}" class="btnCommentDel">삭제</a>
		                        	<a href="#" class="btnCommentModify">수정</a>
	                 				<a href="#" class="btnCommentCancel" style="display:none">취소</a>
                 				</c:if>
	                    	</div>
	                </article>
                </c:forEach>
                <c:if test="${article.comment eq 0}"> <!-- 글의 코멘트 갯수가 저장되어있는데 0개면 -->
                <!-- 혹은 comments.size == 0  갯수가 0개면 -->
                	<p class="empty">
                		등록된 댓글이 없습니다.
                	</p>
                </c:if>
            </section>

            <!-- 댓글입력폼 -->
            <section class="commentForm">
                <h3>댓글쓰기</h3>
                <form action="/Farmstory3/board/comment.do" method="post">
                	<input type="hidden"  name="parent" value="${article.seq}">
                	<input type="hidden"  name="uid" value="${sessMember.uid}">
                	<input type="hidden"  name="group" value="${group}">
                	<input type="hidden"  name="cate" value="${cate}">
                    <textarea name="content"></textarea>
                    <div>
                        <a href="#" class="btnCancel">취소</a>
                        <input type="submit" class="btnWrite" value="작성완료"/>
                    </div>
                </form>
            </section>

        </section>
<%@ include file="../_footer.jsp"%>