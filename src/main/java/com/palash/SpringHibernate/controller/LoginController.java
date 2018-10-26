package com.palash.SpringHibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login_page");
		return mv;
	}
	@RequestMapping("/logout")
	public ModelAndView logout() {
		ModelAndView mv = new ModelAndView("login_page");
		return mv;
	}
}
