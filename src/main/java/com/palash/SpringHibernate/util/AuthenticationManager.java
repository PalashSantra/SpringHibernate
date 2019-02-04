package com.palash.SpringHibernate.util;

import javax.servlet.http.HttpSession;

public class AuthenticationManager {
	public AuthenticationManager() {
		
	}
	public void Authenticate(HttpSession session,Object obj) {
		session.setAttribute("jsession", obj);
	}
	public void deAuthenticate(HttpSession session) {
		session.removeAttribute("jsession");
	}
	public boolean isAuthenticated(HttpSession session,String url_path) {
		UserSession us;
		try {
			us = (UserSession) session.getAttribute("jsession");
			if(us.getRoll()=="admin" || us.getRoll()=="student")
				return true;
			else
				return false;
//			switch(us.getRoll()) {
//				case "admin":{
//					return true;
//				}
//				case "user":{
//					return true;
//				}
//				default:{
//					return false;
//				}
//			}
		}
		catch(Exception ex) {
			return false;
		}
	}
}
