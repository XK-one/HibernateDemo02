package com.wyk.sh.manager.impl;

import com.wyk.sh.entity.Log;
import com.wyk.sh.manager.LogManager;
import com.wyk.sh.util.HibernateUtils;

public class LogManagerImpl implements LogManager {

	@Override
	public void addLog(Log log) {
		HibernateUtils.getSessionFactory().getCurrentSession().save(log);
	}

}
