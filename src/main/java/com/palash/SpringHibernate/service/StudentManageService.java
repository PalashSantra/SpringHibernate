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
}
