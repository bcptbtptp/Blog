package com.gzj.service;

import com.gzj.pojo.Comment;
import java.util.List;

/**
 * @InterfaceName CommentService
 * @Description TODO
 * @Author 42
 * @Date 2020/3/30 下午 4:25
 * @Version 1.0
 */
public interface CommentService
{
	List<Comment> listCommentByBlogId(Long blogId);

	Comment saveComment(Comment comment);
}
