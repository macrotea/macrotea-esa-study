package com.mtea.esa.service;

import java.util.Date;
import java.util.Random;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import com.mtea.esa.AbstractTestCase;
import com.mtea.esa.dao.UserDao;
import com.mtea.esa.model.User;
import com.mtea.esa.util.PrintUtil;
import com.mtea.esa.util.ThreadUtil;

/**
 * @author macrotea@qq.com
 * @date 2012-12-29 上午2:38:18
 * @version 1.0
 * @note
 */
public class UserCacheServiceTest extends AbstractTestCase {
	
	@Autowired
	private UserCacheService userCacheService;
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void getUserByIdFromCache() throws InterruptedException {

		User u = mockUser();
		userDao.save(u);

		// userFirstLoad
		StopWatch watch = new StopWatch();
		watch.start();
		User userFirstLoad = userCacheService.getUserByIdFromCache(u.getId());
		PrintUtil.formatPrint("userFirstLoad: " + userFirstLoad.toString());
		watch.stop();

		long t1 = watch.getLastTaskTimeMillis();
		PrintUtil.formatPrint("首次从数据库中加载User放入缓存中,而加载时间为: " + t1);

		System.out.println();

		// userFromCache
		watch.start();
		int count = 10;
		User userFromCache = null;
		for (int i = 0; i < count; i++) {
			userFromCache = userCacheService.getUserByIdFromCache(u.getId());
		}
		PrintUtil.formatPrint("userFromCache: " + userFromCache.toString());
		watch.stop();

		long t2 = watch.getLastTaskTimeMillis();
		PrintUtil.formatPrint("从缓存中加载User且执行" + count + "次,而加载时间为: " + t2);

		Assert.assertTrue(t1 > t2);

	}
	

	/**
	 * 模拟用户
	 * @return
	 * @author liangqiye
	 * @date 2012-12-12上午9:16:35
	 */
	private User mockUser() {
		int r = new Random().nextInt(10);
		User u = new User();
		u.setUsername("macrotea-" + r);
		u.setPassword("茶叶" + r);
		u.setEmail("macrotea@qq.com-" +  r);
		u.setAddTime(new Date());
		return u;
	}

}
