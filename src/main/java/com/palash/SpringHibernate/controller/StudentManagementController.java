package com.palash.SpringHibernate.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.palash.SpringHibernate.model.Department;
import com.palash.SpringHibernate.model.Laptop;
import com.palash.SpringHibernate.service.StudentManageService;
import com.palash.SpringHibernate.util.EncryptionUtil;

@Controller
public class StudentManagementController implements ServletContextAware {
	@Autowired
	private ServletContext servletContext;
	private StudentManageService sm_service;
	
	public StudentManagementController() {
		super();
		sm_service = new StudentManageService();
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
		mv.addObject("mode","save");
		return mv;
	}
	@RequestMapping(value="/laptop/save", method=RequestMethod.POST)
	public String  saveLap(@ModelAttribute("laptop") Laptop laptop,RedirectAttributes redirectAttrs) {
		StudentManageService sm = new StudentManageService();
		sm.addLaptop(laptop);
		redirectAttrs.addFlashAttribute("msg", "Your data has been successfully saved.");
		redirectAttrs.addFlashAttribute("msg_type", "success");
		return "redirect:/laptop/show";
	}
	@RequestMapping(value="/laptop/show")
	public ModelAndView listLap() {
		String base_url=this.servletContext.getInitParameter("base_url");
		List<Laptop> laps = sm_service.getAllLaptops();
		ModelAndView mv = new ModelAndView("lap_info");
		mv.addObject("base_url", base_url);
		mv.addObject("laps", laps);
		return mv;
	}	
	@RequestMapping("/laptop/delete/{token}")
	public String  delLap(@PathVariable("token") String token,RedirectAttributes redirectAttrs,HttpServletResponse hr) {
		try {
			int id= Integer.parseInt(EncryptionUtil.decode(token));
			Laptop laptop=sm_service.getLaptop(id);
			sm_service.deleteLaptop(laptop);
			redirectAttrs.addFlashAttribute("msg", "Your data has been successfully removed.");
			redirectAttrs.addFlashAttribute("msg_type", "danger");
			return "redirect:/laptop/show";
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			hr.setStatus(403);
			return "/laptop/show";
		}
	}
	@RequestMapping("/laptop/edit/{token}")
	public ModelAndView editLap(@PathVariable("token") String token,HttpServletResponse hr) {
		String base_url=this.servletContext.getInitParameter("base_url");
		try {
			int id= Integer.parseInt(EncryptionUtil.decode(token));
			Laptop laptop=sm_service.getLaptop(id);
			ModelAndView mv = new ModelAndView("add_laptop","laptop",laptop);
			mv.addObject("base_url", base_url);
			mv.addObject("mode","update");
			return mv;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			hr.setStatus(403);
			return new ModelAndView("add_laptop");
		}
	}
	@RequestMapping(value="/laptop/update", method=RequestMethod.POST)
	public String  updateLap(@ModelAttribute("laptop") Laptop laptop,RedirectAttributes redirectAttrs) {
		Laptop my_laptop=sm_service.getLaptop(laptop.getLId());
		my_laptop.setName(laptop.getName());
		my_laptop.setPrice(laptop.getPrice());
		redirectAttrs.addFlashAttribute("msg", "Your data has been successfully updated.");
		redirectAttrs.addFlashAttribute("msg_type", "success");
		return "redirect:/laptop/show";
	}
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		
	}
	


}
