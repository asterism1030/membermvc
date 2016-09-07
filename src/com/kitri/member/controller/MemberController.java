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
		String path = "/index.jsp"; //act�� null�� ��� �⺻ �������� �̵��Ѵ�
		boolean forward=false;	//sendRedirect(�ٸ� ������Ʈ ����)�� �޸� �ش� ������Ʈ �ȿ����� �̵�����
								//��Ʈ�� �ʿ�X
		
		//act�� null�� ��� ������ �߻��ϴµ� �Ʒ��� ���� �ڵ带 �ۼ��ϸ� nullpointE~ �߻�X
		if("mvjoin".equals(act)) {
			path="/join/member.jsp";
		} else if("mvlogin".equals(act)) {
			path="/login/login.jsp";
		} else if("mvidck".equals(act)) {
			path="/join/idcheck.jsp";
		} else if("mvzip".equals(act)) {
			path="/join/zipsearch.jsp";
		} else if("idsearch".equals(act)) {
			//idcheck.jsp 35�� ����
			String id = request.getParameter("id");
			int count = memberService.idCheck(id);
			path="/join/idcheck.jsp?id="+id+"&count="+count;
			
		} else if("zipsearch".equals(act)) {
			String dong = Encoder.isToEuc(request.getParameter("dong"));
//			System.out.println("�˻� �� : " + dong);
			//�ش��ϴ� ���� DB���� ã�� list�� �����Ѵ�
			List<ZipDto> list = memberService.zipSearch(dong);
//			System.out.println("�˻��� �� : "		response.sendRedirect(root + path);
			forward = true;
			request.setAttribute("ziplist", list); //setAttribute(����Ī��,Obejct(��ü));
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
			
			if(count == 0) { //������ ���
				path = "/join/fail.jsp";
				
			} else {
				//���������Ƿ� memberD~D�� �Բ� �Ѱ��־�� �Ѵ�
				//request.setAttribute(~)�� ���� ��Ī�� �ְ� �Ѱ��ִ� ������ �Ѵ�
				request.setAttribute("userInfo", memberDetailDto);
				forward = true;
				
				path = "/join/success.jsp";
			}
			
			path = count == 0 ? "/join/fail.jsp" : "/join/success.jsp";
		
		} else if ("login".equals(act)) {
			String id = request.getParameter("id");
			String pass = request.getParameter("pass");
			
			MemberDto memberDto = memberService.login(id, pass);
			
			if(memberDto != null) { //�α��� ������
//				request.setAttribute("userInfo", memberDto);
				HttpSession session = request.getSession();
				session.setAttribute("userInfo", memberDto);
				//session�� �ּҿ� �� ���/��~ ���� ���� �ʴ´�
				
				String idsv = request.getParameter("idsv");
				
				if("ok".equals(idsv)) {
					Cookie cookie = new Cookie("loginid", id);
					cookie.setMaxAge(60 * 60 * 24 * 3);
					cookie.setPath(root);
					response.addCookie(cookie); //���� ��Ű�� server�� ������ �����Ƿ� �Ѱ��ֱ� ���� �� �ڵ� �ۼ�
				} else {
					Cookie cookie[] = request.getCookies();
					if (cookie != null) {
						int len = cookie.length;
						for (int i = 0; i < len; i++) {
							if("loginid".equals(cookie[i].getName())) {
								System.out.println("�α��� ���̵� ��Ű!!!");
								cookie[i].setMaxAge(0); //�ִ� ���ӽð��� 0. ��, ������ ����
								cookie[i].setPath(root);//�������� �����Ѵ�(�ݵ��!!)
								response.addCookie(cookie[i]); //Ŭ���̾�Ʈ���� ��Ű�� �߰��Ѵ�
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
			//�α��� �����Ǵ��� memberDto�� null�ΰ��̴�.
			//���ǿ� ����� memberDto�� userInfo�� null�� �����Ѵ�
			HttpSession session = request.getSession();
//			session.setAttribute("userInfo", null); //���� ���� ���X userInfo�� �������� �ʰ� �����ϴ� �κи� �޶����Ƿ�!
			session.removeAttribute("userInfo"); //������ �����ֵ��� �Ѵ�
//			session.invalidate(); //���ǿ� �ִ� ��� ������ �����ش�
			
			//�α׾ƿ� �� path�� ����X�ص� �⺻ path�� index.jsp�̹Ƿ� ����� �̵�
			
		} else if ("maillist".equals(act)) {
			//���߿� ���ϼ������� ���� ������
			path = "/mail/list.jsp";
			
		}
		
		
	
		if(forward) {
			//�̹� ������Ʈ �ȿ��� �̷������ ���̱� �빮�� root��θ� ���ؼ� �ȵȴ�.
			RequestDispatcher disp = request.getRequestDispatcher(path);
	        disp.forward(request, response);
		} else {
			response.sendRedirect(root+path);
			 
		}
	}
}
