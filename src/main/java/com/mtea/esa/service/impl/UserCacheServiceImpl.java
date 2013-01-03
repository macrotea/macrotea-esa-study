package com.mtea.esa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtea.esa.dao.UserDao;
import com.mtea.esa.model.User;
import com.mtea.esa.service.UserCacheService;

/**
 * @author macrotea@qq.com
 * @date 2012-12-29 上午2:36:19
 * @version 1.0
 * @note
 */
@Service
public class UserCacheServiceImpl implements UserCacheService{
	
	@Autowired
	private UserDao userDao;
	
	/* (non-Javadoc)
	 * @see com.mtea.ssm.service.UserCacheService#getUserByIdFromCache(java.lang.Long)
	 */
	public User getUserByIdFromCache(Long id) {
		return userDao.findById(id);
	}

}
