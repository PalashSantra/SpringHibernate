package com.palash.SpringHibernate.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.palash.SpringHibernate.model.Department;
import com.palash.SpringHibernate.model.Laptop;
import com.palash.SpringHibernate.service.StudentManageService;

@Controller
public class StudentManagementController implements ServletContextAware {
	@Autowired
	private ServletContext servletContext;
	private StudentManageService sm_service;
	
	public StudentManagementController() {
		super();
		sm_service = new StudentManageService();
	}
	@RequestMapping("/student_insert")
	public ModelAndView addStudent() {
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	@RequestMapping("/save_student")
	public ModelAndView saveStudent() {
		ModelAndView mv = new ModelAndView("login_page");
		return mv;
	}
	@RequestMapping("/show_student")
	public ModelAndView listStudent() {
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	@RequestMapping("/remove_student")
	public ModelAndView delStudent() {
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	@RequestMapping("/department")
	public ModelAndView addDept() {
		String base_url=this.servletContext.getInitParameter("base_url");
		List<Department> depts = sm_service.getAllDepartments(); 
		ModelAndView mv = new ModelAndView("dept_info");
		mv.addObject("base_url", base_url);
		mv.addObject("depts", depts);
		return mv;
	}
	@RequestMapping("/department/service")
	public ModelAndView saveDept(@RequestParam("dept_name") String dept_name,@RequestParam("dept_no") int dept_no,@RequestParam("mode") String mode) {
		String base_url=this.servletContext.getInitParameter("base_url");
		if(mode.equals("save")){
			Department dept = new Department();
			dept.setDName(dept_name);
			sm_service.addDepartment(dept);
		}
		else {
			Department dept = sm_service.getDepartment(dept_no);
			dept.setDName(dept_name);
		}
		List<Department> depts = sm_service.getAllDepartments(); 
		ModelAndView mv = new ModelAndView("redirect:"+base_url+"/department");
		//mv.addObject("base_url", base_url);
		//mv.addObject("depts", depts);
		return mv;
	}
	@RequestMapping("/department/delete")
	public ModelAndView deleteDept(@RequestParam("dept_no") int dept_no) {
		String base_url=this.servletContext.getInitParameter("base_url");
		Department dept = sm_service.getDepartment(dept_no);
		sm_service.deleteDepatment(dept);
		ModelAndView mv = new ModelAndView("redirect:"+base_url+"/department");
		return mv;
	}
	@RequestMapping("/laptop/add")
	public ModelAndView addLap(@ModelAttribute("laptop") Laptop laptop) {
		String base_url=this.servletContext.getInitParameter("base_url");
		 
		ModelAndView mv = new ModelAndView("add_laptop");
		mv.addObject("base_url", base_url);
		
		return mv;
	}
	@RequestMapping("/laptop/save")
	public String saveLap(@ModelAttribute("laptop") Laptop laptop) {
		StudentManageService sm = new StudentManageService();
		sm.addLaptop(laptop);
		return "redirect:/laptop/show";
	}
	@RequestMapping("/laptop/show")
	public ModelAndView listLap() {
		String base_url=this.servletContext.getInitParameter("base_url");
		List<Laptop> laps = sm_service.getAllLaptops(); 
		ModelAndView mv = new ModelAndView("lap_info");
		mv.addObject("base_url", base_url);
		mv.addObject("laps", laps);
		return mv;
	}
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		
	}
	@RequestMapping("/remove_lap")
	public ModelAndView delLap() {
		ModelAndView mv = new ModelAndView();
		return mv;
	}


}
