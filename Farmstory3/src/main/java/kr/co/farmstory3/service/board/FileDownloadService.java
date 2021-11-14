package kr.co.farmstory3.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory3.dao.ArticleDao;
import kr.co.farmstory3.vo.FileVo;

public class FileDownloadService implements CommonService{	
	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		
		String fseq = req.getParameter("fseq");
		
		FileVo fvo = ArticleDao.getInstance().selectFile(fseq);
		req.setAttribute("fvo", fvo); // 컨트롤러도 모델도 뷰도 다 공유 => 요청하는 순각 request객체는 공유한다 
		
		return "file:";
	}

}
