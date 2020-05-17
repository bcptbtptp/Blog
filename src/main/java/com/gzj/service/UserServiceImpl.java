package com.gzj.service;

import com.gzj.dao.UserDao;
import com.gzj.pojo.User;
import com.gzj.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author 42
 * @Date 2020/3/20 下午 4:44
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService
{

	@Autowired
	private UserDao userDao;

	@Override
	public User checkUser(String username, String password) {
		User user = userDao.findByUsernameAndPassword(username, MD5Util.code(password));
		return user;
	}
}
