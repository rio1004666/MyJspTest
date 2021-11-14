package kr.co.farmstory3.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory3.dao.MemberDao;
import kr.co.farmstory3.service.board.CommonService;
import kr.co.farmstory3.vo.MemberVo;

public class RegisterService implements CommonService{
	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		
		
		if(req.getMethod().equals("GET")) {
			
			return "/member/register.jsp";
		}
		else {
			String uid = req.getParameter("uid");
			String pass1 = req.getParameter("pass1");
			String name = req.getParameter("name");
			String nick = req.getParameter("nick");
			String email = req.getParameter("email");
			String hp = req.getParameter("hp");
			String zip = req.getParameter("zip");
			String add1 = req.getParameter("addr1");
			String add2 = req.getParameter("addr2");
			String regip = req.getRemoteAddr();
			MemberVo mv = new MemberVo();
			mv.setUid(uid);
			mv.setPass(pass1);
			mv.setName(name);
			mv.setNick(nick);
			mv.setEmail(email);
			mv.setHp(hp);
			mv.setZip(zip);
			mv.setAdd1(add1);
			mv.setAdd2(add2);
			mv.setRegip(regip);
			MemberDao.getInstance().insertMember(mv);
			
			return "redirect:/member/login.do";
		}
	}
}
