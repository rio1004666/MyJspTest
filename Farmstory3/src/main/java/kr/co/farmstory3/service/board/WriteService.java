package kr.co.farmstory3.service.board;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.farmstory3.dao.ArticleDao;
import kr.co.farmstory3.vo.ArticleVo;

public class WriteService implements CommonService{
	 private String path = null;
	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
		ArticleVo av = null;
		if(req.getMethod().equals("GET")) {
			String group = req.getParameter("group");
			String cate = req.getParameter("cate");
			req.setAttribute("group", group);
			req.setAttribute("cate", cate);
			
			return "/board/write.jsp";
		}else {
			MultipartRequest mReq = null;
			try {
				path = req.getServletContext().getRealPath("/file"); // 컨텍스트 경로는 시스템에서 컨텍스트 경로이다 
				int maxSize = 1024 * 1024 * 10;// 최대 파일 허용 크기 10MB
				mReq = new MultipartRequest(req, path, maxSize, "UTF-8", new DefaultFileRenamePolicy()); // 파일 이름 변경 객체까지 넣어준다
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			String mgroup = mReq.getParameter("group");
			String mcate = mReq.getParameter("cate");
			String uid = mReq.getParameter("uid"); // 세션에 있는 uid가 글작성자이다 
			
			String title = mReq.getParameter("title");
			String content = mReq.getParameter("content");
			String fname = mReq.getFilesystemName("fname"); // 파일은 파일시스템 이름으로 받아온다 
			
			
		
			
			String regip = req.getRemoteAddr();
			av = new ArticleVo();
			av.setTitle(title);
			av.setContent(content);
			av.setFile(fname == null ? 0: 1);
			av.setCate(mcate);
			av.setUid(uid);
			av.setRegip(regip);
			int seq = ArticleDao.getInstance().insertArticle(av);// 글번호를 받아서 파일 테이블에 저장할것이다 그 글번호가 파일테이블의 부모필드값이다 
			
			
			if(fname != null) {
				String newName = renameFile(fname,uid); // 새로운 이름을 리턴받고 
				ArticleDao.getInstance().insertFile(seq, fname,newName); // 파일테이블에 같이 저장한다 원래이름과 새로운이름으로 
			}
			
			req.setAttribute("group",mgroup);
			req.setAttribute("cate", mcate);
			
			return "redirect:/board/list.do?group="+mgroup+"&cate="+mcate;
		}
	}//requestProc end
	// 적절하게 모듈화해준다 멀티파트 리케스트객체를 생성하는 과정을 
	
	public String renameFile(String fname, String uid) { // 파일 이름을 그대로 사용하지않고 변경해서 저장 
		
			int i = fname.lastIndexOf("."); // 뒤에서 .을 찾아서 인덱스를 반환 => Sample.txt에서는 6이다 
			String ext = fname.substring(i); //  확장자를 자름 인덱스 6부터 끝까지 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss_"); // _뒤에는 아이디와 확장가 차례로 붙는다!!!!!
			String now = sdf.format(new Date());
			String newName = now+uid+ext; // 새로운 파일이름을 만든다 
			File oriFile = new File(path+"/"+fname);
			File newFile = new File(path+"/"+ newName); // 이 파일이름은 가상의 파일이름이고 원래 파일이름을 새로운 파일이름으로 변경해야한다
			oriFile.renameTo(newFile);
			// 하나의 메서드는 하나의 기능만 정의하는 것이 좋다 그래서 파일 테이블에 입력은 따로 정의한다 
			return newName;
			
	}
}
