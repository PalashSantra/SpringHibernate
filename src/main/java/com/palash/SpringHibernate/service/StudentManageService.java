package com.palash.SpringHibernate.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.palash.SpringHibernate.model.Department;
import com.palash.SpringHibernate.model.Laptop;
import com.palash.SpringHibernate.model.Student;


public class StudentManageService {
	private Configuration con;
	private ServiceRegistry reg;
	private SessionFactory sf;
	Session session;
	public StudentManageService() {
		con = new Configuration().configure().addAnnotatedClass(Student.class).addAnnotatedClass(Laptop.class).addAnnotatedClass(Department.class);
		reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		sf = con.buildSessionFactory(reg);
		session = sf.openSession();
	}
	public void addDepartment(Department dept) {
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			session.save(dept);
			tx.commit();
		}
		catch(Exception e) {
			if (tx!=null) 
				tx.rollback();
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Department> getAllDepartments(){
		String HQL= "from Department";
		List<Department> depts = new ArrayList<Department>();
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			Query q=session.createQuery(HQL);
			q.setCacheable(true);
			depts= q.list();
			tx.commit();
		}
		catch(Exception e) {
			if (tx!=null) 
				tx.rollback();
			e.printStackTrace();
		}
		return depts;
	}
	public Department getDepartment(int DNo) {
		Department dept=null;
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			dept=(Department)session.get(Department.class, DNo);
			tx.commit();
		}
		catch(Exception e) {
			if (tx!=null) 
				tx.rollback();
			e.printStackTrace();
		}
		return dept;
	}
	public void deleteDepatment(Department dept) {
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			session.delete(dept);
			tx.commit();
		}
		catch(Exception e) {
			if (tx!=null) 
				tx.rollback();
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Laptop> getAllLaptops(){
		String HQL= "from Laptop";
		List<Laptop> laps = new ArrayList<Laptop>();
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			Query q=session.createQuery(HQL);
			q.setCacheable(true);
			laps= q.list();
			tx.commit();
		}
		catch(Exception e) {
			if (tx!=null) 
				tx.rollback();
			e.printStackTrace();
		}
		return laps;
	}
	public void addLaptop(Laptop laptop) {
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			session.save(laptop);
			tx.commit();
		}
		catch(Exception e) {
			if (tx!=null) 
				tx.rollback();
			e.printStackTrace();
		}
	}
	public Laptop getLaptop(int LId) {
		Laptop laptop=null;
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			laptop=(Laptop)session.get(Laptop.class, LId);
			tx.commit();
		}
		catch(Exception e) {
			if (tx!=null) 
				tx.rollback();
			e.printStackTrace();
		}
		return laptop;
	}
	public void deleteLaptop(Laptop laptop) {
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			session.delete(laptop);
			tx.commit();
		}
		catch(Exception e) {
			if (tx!=null)
				tx.rollback();
			e.printStackTrace();
		}
	}
}
