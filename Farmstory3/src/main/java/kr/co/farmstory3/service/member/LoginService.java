package kr.co.farmstory3.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.farmstory3.dao.MemberDao;
import kr.co.farmstory3.service.board.CommonService;
import kr.co.farmstory3.vo.MemberVo;

public class LoginService implements CommonService {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		
		if(req.getMethod().equals("GET")) {
			
			String success = req.getParameter("success");
			req.setAttribute("success", success);
			
			
			return "/member/login.jsp";	
		}else {
			
			String uid  = req.getParameter("uid");
			String pass = req.getParameter("pass");
			
			MemberVo vo = MemberDao.getInstance().selectMember(uid, pass);
			
			if(vo != null) {
				// �쉶�썝 留욎쓣 寃쎌슦 -> �궗�슜�옄 �젙蹂닿컼泥� �꽭�뀡���옣 �썑 由ъ뒪�듃 由щ떎�씠�젆�듃 
				HttpSession sess = req.getSession();
				sess.setAttribute("sessMember", vo);
				
				return "redirect:/index.do?success=104";
			}else {
				// �쉶�썝 �븘�땺 寃쎌슦 -> �떎�떆 濡쒓렇�씤�럹�씠吏� 由щ떎�씠�젆�듃
				return "redirect:/member/login.do?success=100";
			}
		}
	}
}
