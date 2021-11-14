package kr.co.farmstory3.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import kr.co.farmstory3.dao.ArticleDao;

public class UpdateCommentService implements CommonService{
	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		String comment = req.getParameter("comment");
		String seq = req.getParameter("seq");
		int result = ArticleDao.getInstance().updateComment(comment, seq);
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		return "json:"+json.toString();
	}
}
