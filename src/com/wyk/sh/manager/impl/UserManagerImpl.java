package com.wyk.sh.manager.impl;

import java.util.Date;

import org.hibernate.Session;

import com.wyk.sh.entity.Log;
import com.wyk.sh.entity.User;
import com.wyk.sh.manager.LogManager;
import com.wyk.sh.manager.UserManager;
import com.wyk.sh.util.HibernateUtils;

public class UserManagerImpl implements UserManager {

	@Override
	public void addUser(User user) {
		Session session = null;
		try{
			session = HibernateUtils.getSessionFactory().getCurrentSession();
			
			session.beginTransaction();
			session.save(user);
			
			Log log = new Log();  
            log.setType("操作日志");  
            log.setTime(new Date());  
            log.setDetail("xxx"); 
            
            LogManager logManager = new LogManagerImpl(); 
            logManager.addLog(log);
            
            session.getTransaction().commit();
            
		}catch(Exception e){
			session.getTransaction().rollback();
		}finally{
			HibernateUtils.closeSession(session); 
		}
	}
}
