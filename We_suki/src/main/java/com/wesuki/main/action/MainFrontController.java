package com.wesuki.main.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wesuki.commons.Action;
import com.wesuki.commons.ActionForward;

// 프로젝트에서 사용되는 주요 개념(명사) => 디비 테이블 구분

public class MainFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 페이지 정보 전달방식에 상관없이 한번에 처리하는 메서드
		System.out.println("doProcess() 호출!");
		
		// URL : http://localhost:8088/We_suki/Main.ws
		// URI : /MVC7/itwill.bo
		
		/**********************1. 가상주소 계산****************************/
		System.out.println(" 1. 가상주소 계산 - 시작 ");
		
		String requestURI = request.getRequestURI();
		System.out.println(" requestURI : "+requestURI);
		String ctxPath = request.getContextPath();
		System.out.println(" ctxPath : "+ctxPath);
		String command = requestURI.substring(ctxPath.length());
		System.out.println(" command : "+command);
		
		System.out.println(" 1. 가상주소 계산 - 끝 ");
		/**********************1. 가상주소 계산****************************/
		
		/**********************2. 가상주소 매핑****************************/
		System.out.println("\n");
		System.out.println(" 2. 가상주소 매핑 - 시작 ");
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/Main.ws")) {
			System.out.println(" C : /Main.ws 호출");
			System.out.println(" C : 패턴1 - 디비 x, 연결된 뷰페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./main/main.jsp");
			forward.setRedirect(false);
		}// Main end
		
		// 로그인으로 이동.
		else if(command.equals("/Login.ws")) {
			System.out.println(" C : /Login.lo 호출");
			System.out.println(" C : 패턴1 - 디비 x, 연결된 뷰페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./login/login.jsp");
			forward.setRedirect(false);
		}// Login end
		
		// Login 동작 실행
		else if(command.equals("/LoginAction.ws")) {
			System.out.println(" C : /LoginAction.ws 호출");
			System.out.println(" C : 패턴2 - 디비 O, 뷰페이지 이동.");
			
			// LoginAction 객체 생성
			action = new LoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}// tc end
		}// LoginAction end
		
		
		
		
		
		
		
		System.out.println(" 2. 가상주소 매핑 - 끝 ");
		System.out.println("\n");
		/**********************2. 가상주소 매핑****************************/
		
		/**********************3. 가상주소 이동****************************/
		System.out.println(" 3. 가상주소 이동 - 시작 ");
		if(forward != null) { //이동정보가 있을때
			if(forward.isRedirect()) {
				// 페이지 이동방식 - true
				System.out.println(" C : sendRedirect방식 - "+forward.getPath()+"이동");
				response.sendRedirect(forward.getPath());
			}else {							
				// 페이지 이동방식 - false
				System.out.println(" C : forward방식 - "+forward.getPath()+"이동");
				RequestDispatcher dis = 
						request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}			
		}		
		System.out.println(" 3. 가상주소 이동 - 끝 ");
		/**********************3. 가상주소 이동****************************/
		
		System.out.println(" doProcess - 끝(컨트롤러 종료) ");
	}// doProcess
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" doGET_board 호출");
		doProcess(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" doPOST_board 호출");
		doProcess(request,response);
		
	}
}

