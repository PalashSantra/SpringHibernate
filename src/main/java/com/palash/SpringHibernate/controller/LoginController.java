package com.palash.SpringHibernate.controller;

import javax.servlet.ServletContext;
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
import com.palash.SpringHibernate.util.UserSession;

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
	public ModelAndView login(@ModelAttribute("user") User user) {
		String base_url= this.servletContext.getInitParameter("base_url");
		ModelAndView mv = new ModelAndView("login_page");
		mv.addObject("base_url",base_url);
		return mv;
	}
	@RequestMapping("/user/loginUser")
	public ModelAndView doLogin(@ModelAttribute("user") @Valid User user,BindingResult result, RedirectAttributes redirectAttrs) {
		if(result.hasFieldErrors("UserName") || result.hasFieldErrors("Password")) {
			String base_url= this.servletContext.getInitParameter("base_url");
			ModelAndView mv = new ModelAndView("login_page");
			mv.addObject("base_url",base_url);
			return mv;
		}
		UserSession us= user_service.Validate(user);
		if(us.isLogged()) {
			System.out.println("success");
			return new ModelAndView("login_page");
		}
		else {
			System.out.println("failed");
			redirectAttrs.addFlashAttribute("msg", "User Name and Password have not matched");
			redirectAttrs.addFlashAttribute("msg_type", "danger");
			return new ModelAndView("redirect:/login");
		}

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
