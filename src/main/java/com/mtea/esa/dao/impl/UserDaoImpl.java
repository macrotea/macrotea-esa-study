package com.mtea.esa.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;
import com.mtea.esa.dao.UserDao;
import com.mtea.esa.model.User;
import com.mtea.esa.util.PrintUtil;
import com.mtea.esa.util.ThreadUtil;

@Repository
public class UserDaoImpl implements UserDao{
	
	private JdbcTemplate jdbcTemplate;
	
	private ParameterizedBeanPropertyRowMapper<User> mapper;
	
	private SimpleJdbcInsert insertActor;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
		this.mapper = ParameterizedBeanPropertyRowMapper.newInstance(User.class);
		this.insertActor = new SimpleJdbcInsert(jdbcTemplate.getDataSource()).withTableName("tb_user").usingColumns("username","password","email","addTime").usingGeneratedKeyColumns("id");
	}
	
	/* (non-Javadoc)
	 * @see com.mtea.ssm.dao.UserDao#findById(long)
	 */
	@Cacheable(cacheName = "userCache")
	public User findById(long id) {
		//由于内存数据库太快故而延时,放大测试的效果
		ThreadUtil.sleep(500);
		PrintUtil.formatPrint("正在调用: findById()");
		return jdbcTemplate.queryForObject("SELECT * FROM tb_user WHERE id = ?", new Object[] { id }, mapper);
	}


	/* (non-Javadoc)
	 * @see com.mtea.ssm.dao.UserDao#save(com.mtea.ssm.model.User)
	 */
	@TriggersRemove(cacheName = "userCache", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
	public User save(User modelInstance) {
		PrintUtil.formatPrint("正在调用: save()");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(modelInstance);
        Number newId = insertActor.executeAndReturnKey(parameters);
        modelInstance.setId(newId.longValue());
        return modelInstance;
	}

	/* (non-Javadoc)
	 * @see com.mtea.esa.dao.UserDao#findAll()
	 */
	@Cacheable(cacheName = "userCache")
	public List<User> findAll() {
		PrintUtil.formatPrint("正在调用: findAll()");
		return jdbcTemplate.query("SELECT * FROM tb_user",mapper);
	}

}