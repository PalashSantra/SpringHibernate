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

import com.palash.SpringHibernate.model.User;

public class UserManageService {
	private Configuration con;
	private ServiceRegistry reg;
	private SessionFactory sf;
	private Session session; 
	private com.palash.SpringHibernate.util.PasswordEncoder passwordEncoder;
	public UserManageService() {
		con = new Configuration().configure().addAnnotatedClass(User.class);
		reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		sf = con.buildSessionFactory(reg);
		session = sf.openSession();
		passwordEncoder = new com.palash.SpringHibernate.util.PasswordEncoder();
	}
	public void registerUser(User user) {
		String encryptedPassword = this.passwordEncoder.encode(user.getPassword());
		user.setPassword(encryptedPassword);
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		}
		catch(Exception e) {
			if (tx!=null) 
				tx.rollback();
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public List<User> getAllUser(){
		String HQL= "from User";
		List<User> users = new ArrayList<User>();
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			Query q=session.createQuery(HQL);
			q.setCacheable(true);
			users= q.list();
			tx.commit();
		}
		catch(Exception e) {
			if (tx!=null) 
				tx.rollback();
			e.printStackTrace();
		}
		return users;
	}
	public boolean validateUser() {
		return true;
	}
}
