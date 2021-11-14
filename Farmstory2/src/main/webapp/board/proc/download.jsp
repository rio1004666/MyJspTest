<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="kr.co.farmstory2.dao.ArticleDao"%>
<%@page import="kr.co.farmstory2.bean.FileBean"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%

	// 파일 번호를 가져와야한다 
	request.setCharacterEncoding("utf-8");
	String fseq = request.getParameter("fseq");
	// 파일정보를 가져와야한다
	ArticleDao dao = ArticleDao.getInstance();
	FileBean fb =  dao.selectFile(fseq);
	// 파일 다운로드 횟수 업데이트
	
	
	
	dao.updateFileDownload(fseq);
	
	// response 헤더 수정
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(fb.getOriName(), "utf-8"));
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "private");
		
		String path = request.getServletContext().getRealPath("/file");
		File file = new File(path+"/" + fb.getNewName());
		//버퍼를 장착한후 파일을 가져오기
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());

		while(true){
			int data = bis.read();
			if(data == -1){
				break;
			}
			bos.write(data);
		}
		bos.close();
		bis.close();

		
%>
