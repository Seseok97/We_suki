package com.wesuki.main.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wesuki.commons.Action;
import com.wesuki.commons.ActionForward;

public class LoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String user_id = request.getParameter("id");
		String user_pw = request.getParameter("pw");
		
		System.out.println("id: "+user_id + "pw: "+user_pw);
		
		// 로그인동작 DAO 통해서 완성해야함.
		
		ActionForward forward = new ActionForward();
		forward.setPath("./Main.ws");
		forward.setRedirect(true);
		return forward;
	}// execute() method end

}// public class end
