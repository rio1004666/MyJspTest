package kr.co.farmstory3.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory3.dao.ArticleDao;



public class ModifyService implements CommonService{
	
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		
		
		if(req.getMethod().equals("GET")) {
			String seq = req.getParameter("seq");
			req.setAttribute("seq", seq);
			req.setAttribute("group", group);
			req.setAttribute("cate", group);
			return "/board/modify.jsp";
		}else {
			String seq = req.getParameter("seq");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			ArticleDao.getInstance().updateArticle(seq, title, content);
			req.setAttribute("group", group);
			req.setAttribute("cate", group);
			return "redirect:/board/view.do?seq="+seq;
		}
		
	}
}
