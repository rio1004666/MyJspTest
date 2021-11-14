package kr.co.farmstory3.service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.farmstory3.dao.ArticleDao;
import kr.co.farmstory3.vo.ArticleVo;
import kr.co.farmstory3.vo.MemberVo;

public class ListService implements CommonService{
	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
		HttpSession sess = req.getSession();
		
		MemberVo sessMember = (MemberVo) sess.getAttribute("sessMember");
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		if(sessMember == null) {
			
			
			return "redirect:/member/login.do?success=102";
			
		}else {
			// 페이지 처리
			
			String pg = req.getParameter("pg"); // 각 페이지 번호를 받아온다 리스트 뷰를 요청할 때마다 
			// pg에 아무것도 받아오지 않는다면 페이지를 1로 셋팅한다 
			
			//모듈화 시키자 
			
			int currentPage = getCurrentPage(pg); // 현재 페이지는 내가 요청한 페이지의 페이지 => 숫자로 변환 => Integer.parseInt(pg)
			int total = ArticleDao.getInstance().selectCountTotal(cate); // 총 글의 갯수를 구하기 위해 쿼리문을 데이터베이스로 날려서 결과값을 가져온다
			int start = getLimitStart(currentPage);  // 페이지 넘어가면 10 20 30......부터 시작 => 글을 10개씩 끊어서 가져오기 위해 변수를 설정한다 
			int pageStartNum = getPageStartNum(total,start); // 총갯수에서  start 번호를 빼면 각 페이지에서 시작하는 번호를 구할 수 있다.
			// 총 갯수가 100개면 100- 0 = 100번부터 시작번호가 셋팅되고 100-10=90번부터 시작번호가 셋팅된다 100-20=80번부터 시작번호가 셋팅된다.
			int lastPageNum = getLastPageNum(total); // 총 갯수에서 마지막 페이지를 구할 수 잇따 10개씩 나눠서 떨어지느냐 아니냐만 체크하면된다 
			
			List<ArticleVo> articles = ArticleDao.getInstance().selectArticles(start,cate);
			
			int[] groups = getPageGroup(currentPage,lastPageNum);
			
			/////////////////////////////////////////////////////////////////////////
			// 불러온 글들을 리케스트 객체에 저장한다 
			
			
			req.setAttribute("articles", articles);
			req.setAttribute("lastPageNum", lastPageNum); // 마지막 페이지의  글 번호를 리스트 뷰페이지로 전달한다 
			req.setAttribute("currentPage", currentPage);
			req.setAttribute("pageStartNum", pageStartNum);
			req.setAttribute("groups", groups);
			
			req.setAttribute("group", group);
			req.setAttribute("cate", cate);
			return"/board/list.jsp";
		} 
	}
	public int getCurrentPage(String pg) {    // 현재 페이지 번호를 가져온다 
		if(pg == null) {
			pg = "1";
		}
		return Integer.parseInt(pg);
	}
	public int getLastPageNum(int total) {     // 마지막 페이지 번호를 가져온다 
		int lastPageNum = 0;
		if(total % 10 == 0){ // 총갯수가 10개씩 나눠서  떨어지는 경우 마지막 페이지는 몫만 반환 
			lastPageNum = total / 10;
		}else{ // 총 갯수가 10씩 나눠서 안떨어지는 경우 마지막 페이지는 몫에다가 +1 반환 
			lastPageNum = total / 10 + 1;
		}	
		return lastPageNum;
	}
	public int getLimitStart(int currentPage) {  // 현재 페이지에서 출발하는 번호를 구하기 위해 start를 셋팅한다 0,10,20 이면 쿼리문에서 limit(0,10)   limit(10,10) limit(20,10).......
		// 처음부터 10개 그다음 10번째 정보 10개 그다음 20번째 10개  그다음 30번째 10개
		
		return (currentPage - 1) * 10;
		
	}
	public int getPageStartNum(int total,int start) {   // 각 페이지의 시작번호를 구하기 위한 메서드 
		return (total - start) + 1;  // 총 갯수에서 시작하는 각 번호를 빼고나서 +1을 해주어야 0번이 안만들어진다 
	}
	
	
	public int[]  getPageGroup(int currentPage, int lastPageNum) { // 반환값이 여러개일경우 배열에 담아서 리턴 
		
		int groupCurrent = (int)Math.ceil(currentPage / 10.0); // 1~10페이지까지 그룹 1번 // 11페이지부터 20페이지 그룹2번 // 21페이지부터 30페이지 그룹 3번 
		int groupStart = (groupCurrent - 1) * 10 + 1;
		int groupEnd = groupCurrent * 10;
		
		if(groupEnd > lastPageNum){
			groupEnd = lastPageNum;
		}
		
		int[] groups = {groupStart,groupEnd};
		
		return groups;
	}

}
