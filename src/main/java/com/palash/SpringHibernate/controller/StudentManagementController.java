package com.palash.SpringHibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentManagementController {

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
		ModelAndView mv = new ModelAndView("dept_info");
		return mv;
	}
	@RequestMapping("/department/save")
	public ModelAndView saveDept(@RequestParam("dept_name") String dept_name) {
		ModelAndView mv = new ModelAndView("dept_info");
		mv.addObject("dept_name", dept_name);
		return mv;
	}
	@RequestMapping("/show_dept")
	public ModelAndView listDept() {
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	@RequestMapping("/remove_dept")
	public ModelAndView delDept() {
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	@RequestMapping("/lap_insert")
	public ModelAndView addLap() {
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	@RequestMapping("/save_lap")
	public ModelAndView saveLap() {
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	@RequestMapping("/show_lap")
	public ModelAndView listLap() {
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	@RequestMapping("/remove_lap")
	public ModelAndView delLap() {
		ModelAndView mv = new ModelAndView();
		return mv;
	}
}
