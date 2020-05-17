package com.gzj.dao;
import com.gzj.pojo.User;
import	java.nio.file.attribute.UserPrincipal;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @InterfaceName UserDao
 * @Description TODO
 * @Author 42
 * @Date 2020/3/20 下午 4:45
 * @Version 1.0
 */
public interface UserDao extends JpaRepository<User, Long>
{
	User findByUsernameAndPassword(String username,String password);
}
