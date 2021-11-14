<%@page import="kr.co.farmstory2.bean.MemberBean"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="kr.co.farmstory2.bean.ArticleBean"%>
<%@page import="kr.co.farmstory2.dao.ArticleDao"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
	
	MemberBean mb = (MemberBean) session.getAttribute("sessMember");
	request.setCharacterEncoding("utf-8");
	String path =  request.getServletContext().getRealPath("/file");
	int maxSize = 1024 * 1024 * 10; // 10MB
	
	// 파일 업로드는 이 한줄로 끝난다 => request객체 , 경로 , 파일최대 크기 , 인코딩방식, 파일이름을 변경하는 객체 생성 
	
	MultipartRequest mRequest = new MultipartRequest(request,path,maxSize,"utf-8",new DefaultFileRenamePolicy());
	
	//   이 라이브러리를 사용하면 파일쓰기 읽기 형식의 스트림전송방식을 써야합니다.
	//   이제 파일이름을 바꾸어야한다 
	
	
	String title = mRequest.getParameter("title");
	String content = mRequest.getParameter("content");
	String fname = mRequest.getFilesystemName("fname"); // 첨부 파일 이름 

	String group = mRequest.getParameter("group");
	String cate  = mRequest.getParameter("cate");
	
	String regip = request.getRemoteAddr();	

	
	ArticleBean article = new ArticleBean();
	article.setCate(cate);
	article.setTitle(title);
	article.setContent(content);
	article.setFile(fname == null ? 0 : 1);
	article.setUid(mb.getUid());
	article.setRegip(regip);
	
	int seq = ArticleDao.getInstance().insertArticle(article);
	if( fname !=null ){
		// 파일명 수정작업
		// 파일 테이블 INSERT작업 (원래 파일이름 저장하기 위해서 => 새로운 파일이름 수정해야만 구분가능)
		int i = fname.lastIndexOf("."); // 뒤에서 .을 찾아서 인덱스를 반환 => Sample.txt에서는 6이다 
		String ext = fname.substring(i); //  확장자를 자름 인덱스 6부터 끝까지 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss_"); // _뒤에는 아이디와 확장가 차례로 붙는다!!!!!
		String now = sdf.format(new Date());
		String newName = now+mb.getUid()+ext; // 새로운 파일이름을 만든다 
		File oriFile = new File(path+"/"+fname);
		File newFile = new File(path+"/"+ newName); // 이 파일이름은 가상의 파일이름이고 원래 파일이름을 새로운 파일이름으로 변경해야한다
		oriFile.renameTo(newFile);
		
		ArticleDao.getInstance().insertFile(seq, fname, newName);
	}

	response.sendRedirect("/Farmstory2/board/list.jsp?group="+group+"&cate="+cate+"&uid="+ mb.getUid());
%>

