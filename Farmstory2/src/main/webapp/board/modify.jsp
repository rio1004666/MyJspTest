<%@page import="kr.co.farmstory2.bean.MemberBean"%>
<%@page import="kr.co.farmstory2.dao.ArticleDao"%>
<%@page import="kr.co.farmstory2.bean.ArticleBean"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%
	
	if(mb == null){
		// 로그인을 하지 않고 list 페이지를 요청했을 때
		response.sendRedirect("/Farmstory2/user/login.jsp?success=102");
		return;
	}
	String uri = request.getRequestURI();
	String uid = request.getParameter("uid");
	String seq = request.getParameter("seq");
	String group = request.getParameter("group");
	String cate  = request.getParameter("cate");
	String includeFile = "./_aside"+group+".jsp";
	// 수정 글 가져오기
	ArticleBean article = ArticleDao.getInstance().selectArticle(seq);
	
%>

<jsp:include page="<%= includeFile %>">
	<jsp:param name="cate" value="<%= cate %>"/>
</jsp:include>


<section id="board" class="modify">
    <h3>글수정</h3>
    <article>
        <form action="/Farmstory2/board/proc/modifyProc.jsp" method="post">
        	<input type="hidden" name="seq" value="<%= seq %>"/> <!-- 여기서 글번호가 넘어간다 그 글번호에 해당하는 글을 수정할 것이다  -->
            <input type="hidden" name="group" value="<%= group %>"/>
            <input type="hidden" name="cate" value="<%=cate %>"/>
            <input type="hidden" name="uid" value="<%= article.getUid()%>"/>
            <table>
                <tr>
                    <td>제목</td>
                    <td><input type="text" name="title" value="<%= article.getTitle() %>" placeholder="제목을 입력하세요."/></td>
                </tr>
                <tr>
                    <td>내용</td>
                    <td>
                        <textarea name="content"><%= article.getContent() %></textarea>                                
                    </td>
                </tr>
                <!-- 수정화면에서 첨부파일 추가는 생략하겠습니다.
                <tr>
                    <td>첨부</td>
                    <td><input type="file" name="file"/></td>
                </tr>
                 -->
            </table>
            <div>
                <a href="/Farmstory2/board/view.jsp?group=<%=group %>&cate=<%=cate %>&uid=<%=uid%>&seq=<%=seq %> class="btnCancel">취소</a>
                <input type="submit"  class="btnWrite" value="수정완료"/>
            </div>
        </form>
    </article>
</section>