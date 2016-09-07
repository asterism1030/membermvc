package com.kitri.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kitri.member.model.*;
import com.kitri.util.Encoder;


@WebServlet("/user")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	MemberService memberService;
    
	public void init() {
		memberService = new MemberServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		doGet(request, response);
	}
	
	private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();
		
		String act = request.getParameter("act");
		System.out.println("act =====" + act);
		String path = "/index.jsp"; //act가 null일 경우 기본 페이지로 이동한다
		boolean forward=false;	//sendRedirect(다른 프로젝트 가능)완 달리 해당 프로젝트 안에서만 이동가능
								//루트가 필요X
		
		//act가 null인 경우 에러가 발생하는데 아래와 같이 코드를 작성하면 nullpointE~ 발생X
		if("mvjoin".equals(act)) {
			path="/join/member.jsp";
		} else if("mvlogin".equals(act)) {
			path="/login/login.jsp";
		} else if("mvidck".equals(act)) {
			path="/join/idcheck.jsp";
		} else if("mvzip".equals(act)) {
			path="/join/zipsearch.jsp";
		} else if("idsearch".equals(act)) {
			//idcheck.jsp 35줄 참고
			String id = request.getParameter("id");
			int count = memberService.idCheck(id);
			path="/join/idcheck.jsp?id="+id+"&count="+count;
			
		} else if("zipsearch".equals(act)) {
			String dong = Encoder.isToEuc(request.getParameter("dong"));
//			System.out.println("검색 동 : " + dong);
			//해당하는 동을 DB에서 찾아 list에 저장한다
			List<ZipDto> list = memberService.zipSearch(dong);
//			System.out.println("검색동 수 : "		response.sendRedirect(root + path);
			forward = true;
			request.setAttribute("ziplist", list); //setAttribute(“별칭”,Obejct(객체));
			request.setAttribute("searchdong", dong);
			path = "/join/zipsearch.jsp";
			
		} else if("register".equals(act)) {
			MemberDetailDto memberDetailDto = new MemberDetailDto();
			
			memberDetailDto.setName(request.getParameter("name"));
			memberDetailDto.setId(request.getParameter("id"));
			memberDetailDto.setPass(request.getParameter("pass"));
			memberDetailDto.setZip1(request.getParameter("zip1"));
			memberDetailDto.setZip2(request.getParameter("zip2"));
			memberDetailDto.setAddr1(request.getParameter("addr1"));
			memberDetailDto.setAddr2(request.getParameter("addr2"));
			memberDetailDto.setTel1(request.getParameter("tel1"));
			memberDetailDto.setTel2(request.getParameter("tel2"));
			memberDetailDto.setTel3(request.getParameter("tel3"));
			memberDetailDto.setEmail1(request.getParameter("email1"));
			memberDetailDto.setEmail2(request.getParameter("email2"));
			
			int count = memberService.register(memberDetailDto);
			
			if(count == 0) { //실패인 경우
				path = "/join/fail.jsp";
				
			} else {
				//성공했으므로 memberD~D도 함께 넘겨주어야 한다
				//request.setAttribute(~)는 값에 별칭을 주고 넘겨주는 역할을 한다
				request.setAttribute("userInfo", memberDetailDto);
				forward = true;
				
				path = "/join/success.jsp";
			}
			
			path = count == 0 ? "/join/fail.jsp" : "/join/success.jsp";
		
		} else if ("login".equals(act)) {
			String id = request.getParameter("id");
			String pass = request.getParameter("pass");
			
			MemberDto memberDto = memberService.login(id, pass);
			
			if(memberDto != null) { //로그인 성공시
//				request.setAttribute("userInfo", memberDto);
				HttpSession session = request.getSession();
				session.setAttribute("userInfo", memberDto);
				//session은 주소에 그 경로/값~ 등이 뜨지 않는다
				
				String idsv = request.getParameter("idsv");
				
				if("ok".equals(idsv)) {
					Cookie cookie = new Cookie("loginid", id);
					cookie.setMaxAge(60 * 60 * 24 * 3);
					cookie.setPath(root);
					response.addCookie(cookie); //현재 쿠키는 server가 가지고 있으므로 넘겨주기 위해 옆 코드 작성
				} else {
					Cookie cookie[] = request.getCookies();
					if (cookie != null) {
						int len = cookie.length;
						for (int i = 0; i < len; i++) {
							if("loginid".equals(cookie[i].getName())) {
								System.out.println("로그인 아이디 쿠키!!!");
								cookie[i].setMaxAge(0); //최대 지속시간을 0. 즉, 삭제와 같다
								cookie[i].setPath(root);//도메인을 지정한다(반드시!!)
								response.addCookie(cookie[i]); //클라이언트에게 쿠키를 추가한다
								break;
							}
						}
						
					}
				}
				path = "/login/success.jsp";
				
			} else {
				path = "/login/fail.jsp";
			}
			
		} else if ("logout".equals(act)){
			//로그인 유무판단은 memberDto가 null인가이다.
			//세션에 저장된 memberDto인 userInfo를 null로 변경한다
			HttpSession session = request.getSession();
//			session.setAttribute("userInfo", null); //별로 좋은 방법X userInfo는 없어지지 않고 참조하는 부분만 달라지므로!
			session.removeAttribute("userInfo"); //완전히 지워주도록 한다
//			session.invalidate(); //세션에 있는 모든 정보를 지워준다
			
			//로그아웃 후 path를 지정X해도 기본 path가 index.jsp이므로 여기로 이동
			
		} else if ("maillist".equals(act)) {
			//나중에 메일서버가서 메일 얻어오기
			path = "/mail/list.jsp";
			
		}
		
		
	
		if(forward) {
			//이미 프로젝트 안에서 이루어지는 것이기 대문에 root경로를 더해선 안된다.
			RequestDispatcher disp = request.getRequestDispatcher(path);
	        disp.forward(request, response);
		} else {
			response.sendRedirect(root+path);
			 
		}
	}
}
