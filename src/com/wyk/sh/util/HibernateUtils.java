package com.wyk.sh.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * @author Administrator
 * 封装Hibernate框架，用于提供全局的SessionFactory和Session
 */
public class HibernateUtils {

	private static SessionFactory factory;
	
	static {
		try{
			Configuration configure = new Configuration().configure();
			factory = configure.buildSessionFactory();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static Session getSession(){
		return factory.openSession();
	}
	
	public static void closeSession(Session session) {  
	     if (session != null) {  
	            if (session.isOpen()) {  
	                session.close();  
	            }  
	        }  
	}  
	      
	public static SessionFactory getSessionFactory() {  
	     return factory;  
	}  
}
