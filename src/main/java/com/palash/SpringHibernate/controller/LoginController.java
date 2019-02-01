package com.palash.SpringHibernate.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.palash.SpringHibernate.model.User;
import com.palash.SpringHibernate.service.UserManageService;
import com.palash.SpringHibernate.util.AuthenticationManager;
import com.palash.SpringHibernate.util.UserSession;

@Controller
public class LoginController implements ServletContextAware {
	@Autowired
	private ServletContext servletContext;
	private UserManageService user_service;
	private AuthenticationManager auth;
	public LoginController() {
		super();
		user_service = new UserManageService();
		auth= new AuthenticationManager();
	}
	@RequestMapping("/register")
	public ModelAndView registerUser(@ModelAttribute("user") User user) {
		String base_url= this.servletContext.getInitParameter("base_url");
		ModelAndView mv = new ModelAndView("registration");
		mv.addObject("base_url", base_url);
		return mv;
	}
	@RequestMapping(value="/user/saveUser",method=RequestMethod.POST)
	public ModelAndView createUser(@ModelAttribute("user") @Valid User user,BindingResult result, RedirectAttributes redirectAttrs) {
		String base_url= this.servletContext.getInitParameter("base_url");
		ModelAndView mv = new ModelAndView("registration");
		mv.addObject("base_url",base_url);
		if(!result.hasErrors()) {
			if(!user_service.IsAvailable(user)) {
				result.rejectValue("Email", "email", "is not available.");
				result.rejectValue("UserName", "user_name", "is not available.");
				mv = new ModelAndView("registration");
				mv.addObject("base_url",base_url);
				return mv;
			}
			else {
				user_service.registerUser(user);
				redirectAttrs.addFlashAttribute("msg", "Your have been successfully registered");
				redirectAttrs.addFlashAttribute("msg_type", "success");
				return new ModelAndView("redirect:/login");
			}
		}
		else {
			return mv;
		}
	}
	@RequestMapping("/login")
	public ModelAndView login(@ModelAttribute("user") User user,RedirectAttributes redirectAttrs,HttpSession session) {
		String base_url= this.servletContext.getInitParameter("base_url");
		ModelAndView mv = new ModelAndView("login_page");
		mv.addObject("base_url",base_url);
		if(auth.isAuthenticated(session, "/login")) {
			return new ModelAndView("redirect:/homepage");
		}
		return mv;
	}
	@RequestMapping(value="/user/loginUser", method=RequestMethod.POST)
	public ModelAndView doLogin(@ModelAttribute("user") @Valid User user,BindingResult result, RedirectAttributes redirectAttrs,HttpServletRequest request) {
		if(result.hasFieldErrors("UserName") || result.hasFieldErrors("Password")) {
			String base_url= this.servletContext.getInitParameter("base_url");
			ModelAndView mv = new ModelAndView("login_page");
			mv.addObject("base_url",base_url);
			return mv;
		}
		UserSession us= user_service.Validate(user);
		if(us.isLogged()) {
			auth.Authenticate(request.getSession(false), us);
			return new ModelAndView("redirect:/homepage");
		}
		else {
			redirectAttrs.addFlashAttribute("msg", "User Name and Password have not matched");
			redirectAttrs.addFlashAttribute("msg_type", "danger");
			return new ModelAndView("redirect:/login");
		}
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session,RedirectAttributes redirectAttrs) {
		auth.deAuthenticate(session);
		redirectAttrs.addFlashAttribute("msg", "You have been successfully logged out.");
		redirectAttrs.addFlashAttribute("msg_type", "success");
		return "redirect:/login";
	}
	@RequestMapping("/homepage")
	public ModelAndView home(HttpSession session,RedirectAttributes redirectAttrs) {
		String base_url= this.servletContext.getInitParameter("base_url");
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("base_url",base_url);
		if(auth.isAuthenticated(session, "/homepage")) {
			return mv;
		}
		else {
			redirectAttrs.addFlashAttribute("msg", "You are not authorized.");
			redirectAttrs.addFlashAttribute("msg_type", "danger");
			return new ModelAndView("redirect:/login");
		}
	}
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
