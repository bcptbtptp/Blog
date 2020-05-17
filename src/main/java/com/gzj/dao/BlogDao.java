package com.gzj.dao;

import com.gzj.pojo.Blog;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @InterfaceName BlogDao
 * @Description TODO
 * @Author 42
 * @Date 2020/3/25 下午 2:11
 * @Version 1.0
 */
public interface BlogDao extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog>
{
	@Query("select b from Blog b where b.recommend = true")
	List<Blog> findTop(Pageable pageable);

	@Query("select b from Blog b where b.title like ?1 or b.content like ?1")
	Page<Blog> findByQuery(String query, Pageable pageable);


	@Transactional
	@Modifying
	@Query("update Blog b set b.viewCounts = b.viewCounts+1 where b.id = ?1")
	int updateViews(Long id);

	@Query("select function('date_format',b.updateTime,'%Y') as year from Blog b group by function('date_format',b.updateTime,'%Y') order by year desc ")
	List<String> findGroupYear();

	@Query("select b from Blog b where function('date_format',b.updateTime,'%Y') = ?1")
	List<Blog> findByYear(String year);
}
