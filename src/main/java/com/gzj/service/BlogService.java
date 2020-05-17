package com.gzj.service;

import com.gzj.pojo.Blog;
import com.gzj.vo.BlogQuery;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @InterfaceName BlogService
 * @Description TODO
 * @Author 42
 * @Date 2020/3/25 下午 2:08
 * @Version 1.0
 */
public interface BlogService
{
	/**
	 * get a blog by its id.
	 * @param id
	 * @return Blog
	 */
	Blog getBlog(Long id);

	/**
	 * get a list of blog set.
	 * @param pageable
	 * @param blog
	 * @return
	 */
	Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

	/**
	 * save a Blog into DB.
	 * @param blog
	 * @return
	 */
	Blog saveBlog(Blog blog);

	/**
	 * First judge this blog whether exsit, then modify and save.
	 * @param id
	 * @param blog
	 * @return
	 */
	Blog updateBlog(Long id, Blog blog);

	/**
	 * delete specified blog via id.
	 * @param id
	 */
	void deleteBlog(Long id);

	Blog getAndConvert(Long id);

	Page<Blog> listBlog(Pageable pageable);

	Page<Blog> listBlog(Long tagId,Pageable pageable);

	Page<Blog> listBlog(String query,Pageable pageable);

	List<Blog> listRecommendBlogTop(Integer size);

	Map<String,List<Blog>> archiveBlog();

	Long countBlog();
}
