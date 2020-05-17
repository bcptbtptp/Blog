package com.gzj.service;

import com.gzj.pojo.User;

/**
 * @InterfaceName UserService
 * @Description TODO
 * @Author 42
 * @Date 2020/3/20 下午 4:43
 * @Version 1.0
 */
public interface UserService
{
	/**
	 * check user whether exsit via username and password.
	 * @param username
	 * @param password
	 * @return
	 */
	User checkUser(String username,String password);
}
