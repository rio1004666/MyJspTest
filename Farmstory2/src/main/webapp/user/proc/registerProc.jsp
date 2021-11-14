<%@page import="kr.co.farmstory2.db.Sql"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.co.farmstory2.db.DBConfig"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.co.farmstory2.bean.MemberBean"%>
<%@page import="kr.co.farmstory2.dao.MemberDao"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%

			request.setCharacterEncoding("utf-8"); // 한글데이터로 복호화해준다
			String uid = request.getParameter("uid"); //1            아이디 키-값을 풀러오는 request객체를 호출하였습니다. 
			String name = request.getParameter("name"); //2        이름 키-값을 풀러오는 request객체를 호출하였습니다. 
			String pass1 = request.getParameter("pass1"); //3    비밀번호 키-값을 풀러오는 request객체를 호출하였습니다. 
			String nick = request.getParameter("nick"); //4        닉네임 키-값을 풀러오는 request객체를 호출하였습니다. 
			String email = request.getParameter("email");//5      이메일 키-값을 풀러오는 request객체를 호출하였습니다. 
			String hp = request.getParameter("hp");//6                  휴대폰번호 키-값을 풀러오는 request객체를 호출하였습니다. 
			String zip = request.getParameter("zip");//7              우편번호 키-값을 풀러오는 request객체를 호출하였습니다. 
			String addr1 = request.getParameter("addr1");//8      사는지역과 도로명 주소 등의 1차주소 키-값을 풀러오는 request객체를 호출하였습니다. 
			String addr2 = request.getParameter("addr2");//9      직접입력한 상세주소 2차주소 아이디 키-값을 풀러오는 request객체를 호출하였습니다. 
			String regip = request.getRemoteAddr(); //10              사용자 시스템 아이피주소
			try{
				Connection conn = DBConfig.getInstance().getConnection(); // 데이터베이스에 연결을 담당하는 객체인 Connection객체를 생성하였습니다.
				PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_MEMBER); // Connection객체에 있는 쿼리분 수행 메서드를 수행하고 생성하였습니다.
				// 데이터베이스에 있는 회원테이블의 컬럼명에 해당되는 데이터를 저장하기 위해 INSERT 쿼리문에 매칭시켜 주도록합니다. 
				psmt.setString(1, uid); 
				psmt.setString(2, pass1);
				psmt.setString(3, name);
				psmt.setString(4, nick);
				psmt.setString(5, email);
				psmt.setString(6, hp);
				psmt.setString(7, zip);
				psmt.setString(8, addr1);
				psmt.setString(9, addr2);
				psmt.setString(10, regip);
				psmt.executeLargeUpdate();
				// 데이터베이스에 저장하는 기능을 수행하고 메모리를 해체하여야 합니다. 
				psmt.close(); 
				conn.close();
			}catch(Exception e){ // 데이터베이스에 데이터를 저장하는 과정에서 중복되는 데이터가 있을 수도 있습니다. 예외처리를 해주고 데이터도 저장되지 않는 효과가 있습니다.
				e.printStackTrace();
			}
			// 데이터 저장을 수행한 후에 로그인페이지로 가게 됩니다. 
			response.sendRedirect("/Farmstory2/user/login.jsp");
%>