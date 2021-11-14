<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<section id="user" class="login">

	<!-- form태그로 데이터를 처리해달라는 전송방식인 post로 전송합니다. 요청주소는 loginproc.jsp이며 이 페이지에서 데이터베이스와 연동할 것입니다 -->
    <form action="/Farmstory2/user/proc/loginProc.jsp" method="post">
        <table border="0">
            <tr>
                <td><img src="../img/login_ico_id.png" alt="아이디"/></td>
                <td><input type="text" name="uid" placeholder="아이디를 입력" /></td>
            </tr>
            <tr>
                <td><img src="../img/login_ico_pw.png" alt="비밀번호"/></td>
                <td><input type="password" name="pass" placeholder="비밀번호 입력" /></td>
            </tr>
        </table>
        <input type="submit" class="btnLogin" value="로그인"/>
    </form>

    <div class="info">
        <h3>회원로그인 안내</h3>
        <p>
                     아직 회원이 아니시면 회원으로 가입하세요.
        </p>
        <a href="./terms.jsp">회원가입</a>
    </div>
</section>
<%@ include file="../_footer.jsp" %>





