package com.wyk.sh.controller;

import java.io.Serializable;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.junit.Test;

import com.wyk.sh.entity.User;
import com.wyk.sh.manager.UserManager;
import com.wyk.sh.manager.impl.UserManagerImpl;
import com.wyk.sh.util.HibernateUtils;

public class Main {
	
	@Test
	public void test(){
		/*UserManager userManager = new UserManagerImpl();
		User user = new User();
		user.setName("林一"); 
		userManager.addUser(user);*/
		
		
		/*Session session = HibernateUtils.getSession();
		session.beginTransaction();
		User user = new User();
		user.setName("haha1");
		user.setId(1);
		Serializable id = session.save(user); 
		User user1 = (User)session.load(User.class, id);
		System.out.println("name: " + user1.getName());
		
		session.getTransaction().commit();*/
		
		
		Session session = HibernateUtils.getSession();
		session.beginTransaction();
		session.setCacheMode(CacheMode.IGNORE);			//禁止将一级缓存的数据放到二级缓存中，将缓存模式设置为忽略(CacheMode.IGNORE)
		User user = (User)session.load(User.class, 1);
		System.out.println("name1: " + user.getName()); 
		session.getTransaction().commit();
		
		//管理二级缓存，删除二级缓存所指定的缓存数据（那么下面的load将需要查询，因为不再有缓存）
		//HibernateUtils.getSessionFactory().evict(User.class,1);
		
		Session session2 = HibernateUtils.getSession();
		session2.beginTransaction();
		user = (User)session2.load(User.class, 1);
		System.out.println("name2: " + user.getName()); 
		session2.getTransaction().commit();
		
		
		/**
		 * 关闭二级缓存，开启查询缓存，采用query.list()查询普通属性   .同个session---->  一次查询，从queryCache中查出来
		 * 关闭二级缓存，开启查询缓存，采用query.list()查询普通属性    .不同session---->  一次查询，从queryCahce中查出来
		 * 关闭二级缓存，开启查询缓存，采用query.iterate()查询普通属性   .不同session---->两次查询，查询缓存只对query.list()起作用
		 * 关闭二级缓存，关闭查询缓存，采用query.list()查询实体	  .不同session---->	两次查询，不开启查询缓存，默认query.list每次执行都会发出查询语句
		 * 关闭二级缓存，开启查询缓存，采用query.list()查询实体	  .不同session---->	两次查询，跨session，所以每次都会发出查询语句
		 * 开启二级缓存，开启查询缓存，采用query.list()查询实体	  .不同session---->	一次查询,从二级缓存中查出
		 * 
		 * 
		 */
		
	}
	
}
