package com.gzj.dao;

import com.gzj.pojo.Comment;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @InterfaceName CommentDao
 * @Description TODO
 * @Author 42
 * @Date 2020/3/30 下午 4:26
 * @Version 1.0
 */
public interface CommentDao extends JpaRepository<Comment, Long>
{
	List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}
