package kr.co.farmstory3.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory3.dao.ArticleDao;

public class DeleteCommentService implements CommonService{
	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		String seq = req.getParameter("seq");
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		String parent = req.getParameter("parent");
		ArticleDao.getInstance().updateCommentCount(parent, -1);
		
		ArticleDao.getInstance().deleteComment(seq);
		return "redirect:/board/view.do?seq="+parent+"&group="+group+"&cate="+cate;
	}
}
