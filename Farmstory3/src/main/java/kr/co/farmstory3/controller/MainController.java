package kr.co.farmstory3.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory3.service.board.CommonService;
import kr.co.farmstory3.vo.FileVo;



public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> instances = new HashMap<>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		// 최초 요청시 실행되는 컨트롤러 초기화 메서드
		System.out.println("MainController init 실행!!!");
		
		// 액션주소 프로퍼티 파일 경로 구하기
		ServletContext ctx = config.getServletContext();
		String path = ctx.getRealPath("/WEB-INF") + "/urlMapping.properties";
		
		// 프로퍼티 파일 입력스트림 연결 후 프로퍼티 객체 생성
		Properties prop = new Properties();
		
		try {
			FileInputStream fis = new FileInputStream(path);
			prop.load(fis);
			fis.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		// 프로퍼티 객체로 서비스 객체 생성
		Iterator iter = prop.keySet().iterator();
		
		while(iter.hasNext()) {
			
			String k = iter.next().toString();
			String v = prop.getProperty(k);
			
			try {
				
				Class obj = Class.forName(v);
				Object instance = obj.newInstance();
				
				instances.put(k, instance);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	} // init end...
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		requestProc(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		requestProc(req, resp);
	}
	
	protected void requestProc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청주소에서 key 구하기
		String path = req.getContextPath();
		String uri  = req.getRequestURI();
		String key = uri.substring(path.length());
		
		// Map에서 Service 객체 꺼내기
		CommonService instance = (CommonService) instances.get(key);
		
		// Service 객체 실행 후 결과 정보 받기
		String result = instance.requestProc(req, resp); //    서비스 객체에서 리턴되는 값 
		
		if(result.startsWith("redirect:")) {
			// 리다이렉트
			String redirecUrl = result.substring(9);
			resp.sendRedirect(path+redirecUrl);
		}else if(result.startsWith("json:")){ // 실제 문자열로 이루어진 json이 실제 json데이터로 변환해주어야한다 
			// 서비스객체는 redirect 명령할 수 도 있고 view 명령을 할 수도 있다 또 json을 줄수도 있다
			// 임의로 서비스 객체에서 반환하는 문자열에서 타입을 정할 수 있다 
			// json출력
			PrintWriter out = resp.getWriter();
			out.print(result.substring(5)); // json을 뺀값 이후로 자른다
			// json데이터를 직접 출력한다면 api주소이다 
		}else if(result.startsWith("file:")) { //  클라이언트로 파일 전송 
			// response 헤더 수정
			
			//Service 에서 저장한 FileVo 객체 Controller에서 꺼내기 
			// request객체가 가지고 있는 정보는 컨트롤러 모델 뷰 전부 공유한다 
			// 여기서 원래이름과 새로운이름이 필요하다 헤더정보에다가 다시 인코딩해서 담고 
			// 새로운 파일이름으로 되어 있던 파일 입력스트림에다가 저장해서 보낸다 
			FileVo fvo = (FileVo) req.getAttribute("fvo");
			
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(fvo.getOriName(), "utf-8"));
			resp.setHeader("Content-Transfer-Encoding", "binary");
			resp.setHeader("Pragma", "no-cache");
			resp.setHeader("Cache-Control", "private");
		
		// response 객체 파일 스트림 작업
			String filepath = req.getServletContext().getRealPath("/file");
			File file = new File(path+"/"+fvo.getNewName());
			
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
			// 파일내용을 리스폰스 객체에 실어나르는 작업이다
			while(true){
				
				int data = bis.read();
				
				// 더이상 읽을 데이터가 없을경우
				if(data == -1){
					break;
				}
				
				bos.write(data);
			}
			
			bos.close();
			bis.close();
		}
		else {
			// 해당 View로 forward 하기
			RequestDispatcher dispatcher = req.getRequestDispatcher(result);
			dispatcher.forward(req, resp);
		}
	}
	
}
