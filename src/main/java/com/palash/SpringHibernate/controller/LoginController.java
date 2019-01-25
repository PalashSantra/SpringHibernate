package com.palash.SpringHibernate.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.palash.SpringHibernate.model.User;
import com.palash.SpringHibernate.service.UserManageService;

@Controller
public class LoginController implements ServletContextAware {
	@Autowired
	private ServletContext servletContext;
	private UserManageService user_service;
	public LoginController() {
		super();
		user_service = new UserManageService();
	}
	@RequestMapping("/register")
	public ModelAndView registerUser(@ModelAttribute("user") User user) {
		String base_url= this.servletContext.getInitParameter("base_url");
		ModelAndView mv = new ModelAndView("registration");
		mv.addObject("base_url", base_url);
		return mv;
	}
	@RequestMapping(value="/user/saveUser",method=RequestMethod.POST)
	public String createUser(@ModelAttribute("user") User user,RedirectAttributes redirectAttrs) {
		String base_url= this.servletContext.getInitParameter("base_url");
		user_service.registerUser(user);
		redirectAttrs.addFlashAttribute("msg", "Your have been successfully registered");
		redirectAttrs.addFlashAttribute("msg_type", "success");
		return "redirect:/login";
	}
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
	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
