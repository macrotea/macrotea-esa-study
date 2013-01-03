package com.mtea.esa.dao;

import java.util.List;

import com.mtea.esa.model.User;

/**
 * @author macrotea@qq.com
 * @date 2012-12-29 上午2:04:38
 * @version 1.0
 * @note
 */
public interface UserDao {

	/**
	 * 根据Id查找
	 * @author macrotea@qq.com
	 * @date 2012-12-29 上午2:04:47
	 * @param id
	 * @return
	 */
	User findById(long id);
	
	/**
	 * 查找所有
	 * @author macrotea@qq.com
	 * @date 2013-1-3 下午8:39:19
	 * @return
	 */
	List<User> findAll();
	
	/**
	 * 保存
	 * @author macrotea@qq.com
	 * @date 2012-12-29 上午2:20:12
	 * @param modelInstance
	 * @return
	 */
	User save(User modelInstance);
	
	/**
	 * 删除所有
	 * @author macrotea@qq.com
	 * @date 2013-1-3 下午8:39:19
	 * @return
	 */
	int deleteAll();

}
