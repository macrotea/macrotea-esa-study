package com.mtea.esa.service;

import com.mtea.esa.model.User;

/**
 * @author macrotea@qq.com
 * @date 2012-12-29 上午2:35:41
 * @version 1.0
 * @note
 */
public interface UserCacheService {
	
	User getUserByIdFromCache(Long id);

}
