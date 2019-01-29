package com.palash.SpringHibernate.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.palash.SpringHibernate.model.User;
import com.palash.SpringHibernate.util.PasswordEncoder;
import com.palash.SpringHibernate.util.UserSession;

public class UserManageService {
	private Configuration con;
	private ServiceRegistry reg;
	private SessionFactory sf;
	private Session session; 
	private PasswordEncoder passwordEncoder;
	public UserManageService() {
		con = new Configuration().configure().addAnnotatedClass(User.class);
		reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		sf = con.buildSessionFactory(reg);
		session = sf.openSession();
		passwordEncoder = new PasswordEncoder();
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
	@SuppressWarnings("unchecked")
	public boolean IsAvailable(User user) {
		String HQL="from User us where us.Email = :e or us.UserName = :u";
		List<Object[]> users = new ArrayList<Object[]>();
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			Query q=session.createQuery(HQL);
			q.setParameter("e", user.getEmail());
			q.setParameter("u", user.getUserName());
			q.setCacheable(true);
			users= (List<Object[]>) q.list();
			tx.commit();
		}
		catch(Exception e) {
			if (tx!=null) 
				tx.rollback();
			e.printStackTrace();
		}
		if(users.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}
	@SuppressWarnings("unchecked")
	public UserSession Validate(User user) {
		String HQL="from User us where us.UserName = :u";
		List<User> users = new ArrayList<User>();
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			Query q=session.createQuery(HQL);
			q.setParameter("u", user.getUserName());
			users= (List<User>) q.list();
			tx.commit();
		}
		catch(Exception e) {
			if (tx!=null) 
				tx.rollback();
			e.printStackTrace();
		}
		if(users.isEmpty()) {
			UserSession us= new UserSession();
			return us;
		}
		else {
			if(passwordEncoder.matches(user.getPassword(), users.get(0).getPassword())) {
				UserSession us= new UserSession();
				us.setFullName(users.get(0).getFullName());
				us.setEmail(users.get(0).getEmail());
				us.setUserName(users.get(0).getUserName());
				us.setUserID(users.get(0).getUserID());
				us.setIsActive(users.get(0).isIsActive());
				us.setRoll(users.get(0).getRole());
				us.setLoginTime(new Date());
				us.setLogged(true);
				return us;
			}
			else {
				UserSession us= new UserSession();
				return us;
			}
			
		}
	}
}
