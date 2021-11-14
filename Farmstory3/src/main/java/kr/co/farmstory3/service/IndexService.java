package kr.co.farmstory3.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory3.dao.ArticleDao;
import kr.co.farmstory3.service.board.CommonService;
import kr.co.farmstory3.vo.ArticleVo;

public class IndexService implements CommonService{
	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String success = req.getParameter("success");
		
		ArticleDao dao = ArticleDao.getInstance();
		List<ArticleVo> latest1 = dao.selectLatest("grow");
		List<ArticleVo> latest2 = dao.selectLatest("school");
		List<ArticleVo> latest3 = dao.selectLatest("story");
		
		req.setAttribute("success", success);
		req.setAttribute("grows", latest1);		
		req.setAttribute("schools", latest2);		
		req.setAttribute("storys", latest3);		
		return "/index.jsp";
	}
}
