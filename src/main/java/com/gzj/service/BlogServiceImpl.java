package com.gzj.service;
import com.gzj.util.MarkdownUtils;
import com.gzj.util.MyBeanUtils;
import	java.util.Date;

import com.gzj.NotFoundException;
import com.gzj.dao.BlogDao;
import com.gzj.pojo.Blog;
import com.gzj.pojo.Type;
import com.gzj.vo.BlogQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName BlogServiceImpl
 * @Description TODO
 * @Author 42
 * @Date 2020/3/25 下午 2:11
 * @Version 1.0
 */
@Service
@Transactional
public class BlogServiceImpl implements BlogService
{
	@Autowired
	private BlogDao blogDao;

	@Override
	public Blog getBlog(Long id) {
		return blogDao.findById(id).get();
	}

	@Override
	public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
		return blogDao.findAll(new Specification<Blog>()
		{
			@Override
			public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				if (blog.getTitle() != null && !"".equals(blog.getTitle())) {
					predicateList.add(criteriaBuilder.like(root.get("title").as(String.class), "%" + blog.getTitle() + "%"));
				}
				if (blog.getTypeId() != null) {
					predicateList.add(criteriaBuilder.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
				}
				if (blog.isRecommend()) {
					predicateList.add(criteriaBuilder.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
				}
				return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		}, pageable);
	}

	@Override
	public Blog saveBlog(Blog blog) {
		if (blog.getId() == null) {
			blog.setCreateTime(new Date());
			blog.setUpdateTime(new Date());
			blog.setViewCounts(0);
		} else {
			blog.setUpdateTime(new Date());
		}
		return blogDao.save(blog);
	}

	@Override
	public Blog updateBlog(Long id, Blog blog) {
		Blog oldBlog = blogDao.findById(id).get();
		if (oldBlog == null) {
			throw new NotFoundException("该博客不存在");
		}
		BeanUtils.copyProperties(blog, oldBlog, MyBeanUtils.getNullPropertyNames(blog));
		oldBlog.setUpdateTime(new Date());
		return blogDao.save(oldBlog);
	}

	@Override
	public void deleteBlog(Long id) {
		blogDao.deleteById(id);
	}

	@Override
	public Blog getAndConvert(Long id) {
		Blog blog = blogDao.findById(id).get();
		if (blog == null) {
			throw new NotFoundException("该博客不存在");
		}
		Blog b = new Blog();
		BeanUtils.copyProperties(blog,b);
		String content = b.getContent();
		b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));

		blogDao.updateViews(id);
		return b;
	}

	@Override
	public Page<Blog> listBlog(Pageable pageable) {
		return blogDao.findAll(pageable);
	}

	@Override
	public Page<Blog> listBlog(Long tagId, Pageable pageable) {
		return blogDao.findAll(new Specification<Blog>() {
			@Override
			public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Join join = root.join("tags");
				return cb.equal(join.get("id"),tagId);
			}
		},pageable);
	}

	@Override
	public Page<Blog> listBlog(String query, Pageable pageable) {
		return blogDao.findByQuery(query, pageable);
	}

	@Override
	public List<Blog> listRecommendBlogTop(Integer size) {
		Sort.Order sort = new Sort.Order(Sort.Direction.DESC,"updateTime");
		Pageable pageable = PageRequest.of(0, size, Sort.by(sort));
		return blogDao.findTop(pageable);
	}

	@Override
	public Map<String, List<Blog>> archiveBlog() {
		List<String> years = blogDao.findGroupYear();
		Map<String, List<Blog>> map = new HashMap<>();
		for (String year : years) {
			map.put(year, blogDao.findByYear(year));
		}
		return map;
	}

	@Override
	public Long countBlog() {
		return blogDao.count();
	}
}
