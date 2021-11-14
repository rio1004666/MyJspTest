package kr.co.farmstory3.service.board;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory3.dao.ArticleDao;
import kr.co.farmstory3.vo.ArticleVo;

public class ViewService implements CommonService{

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		
			
			String seq = req.getParameter("seq");
			String group = req.getParameter("group");
			String cate = req.getParameter("cate");
			
			
			
			
			
			
			
			ArticleVo article = new ArticleVo();
			
			ArticleDao dao = ArticleDao.getInstance();
			
			
			/* �ۺ��⸦ �ϸ� ��ȸ�� ������Ʈ */
			dao.updateArticleHit(seq);
			
			article = dao.selectArticle(seq);
			req.setAttribute("article", article);
			
			
			
			List<ArticleVo> comments = new ArrayList<>();
			comments = dao.selectComments(seq);
		
			req.setAttribute("comments",comments);
			req.setAttribute("group", group);
			req.setAttribute("cate", cate);
			return "/board/view.jsp";
		
		
	}
}
