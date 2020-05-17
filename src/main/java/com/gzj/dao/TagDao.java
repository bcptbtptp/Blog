package com.gzj.dao;

import com.gzj.pojo.Tag;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @InterfaceName TagDao
 * @Description TODO
 * @Author 42
 * @Date 2020/3/25 上午 11:33
 * @Version 1.0
 */
public interface TagDao extends JpaRepository<Tag, Long>
{
	Tag findByName(String name);

	@Query("select t from Tag t")
	List<Tag> findTop(Pageable pageable);
}
