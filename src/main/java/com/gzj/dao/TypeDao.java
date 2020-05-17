package com.gzj.dao;

import com.gzj.pojo.Type;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @InterfaceName TypeDao
 * @Description TODO
 * @Author 42
 * @Date 2020/3/24 上午 9:53
 * @Version 1.0
 */
public interface TypeDao extends JpaRepository<Type, Long>
{
	Type findByName(String name);

	@Query("select t from Type t")
	List<Type> findTop(Pageable pageable);
}
