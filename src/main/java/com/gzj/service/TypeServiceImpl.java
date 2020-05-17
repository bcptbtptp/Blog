package com.gzj.service;

import com.gzj.NotFoundException;
import com.gzj.dao.TypeDao;
import com.gzj.pojo.Type;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName TypeServiceImpl
 * @Description TODO
 * @Author 42
 * @Date 2020/3/24 上午 9:52
 * @Version 1.0
 */
@Service
@Transactional
public class TypeServiceImpl implements TypeService
{
	@Autowired
	private TypeDao typeDao;

	@Override
	public Type saveType(Type type) {
		return typeDao.save(type);
	}

	@Override
	public Type getType(Long id) {
		return typeDao.findById(id).get();
	}

	@Override
	public Page<Type> listType(Pageable pageable) {
		return typeDao.findAll(pageable);
	}

	@Override
	public Type updateType(Long id, Type type) {
		Type t = typeDao.findById(id).get();
		if (t == null) {
		    throw new NotFoundException("不存在该类型");
		}
		BeanUtils.copyProperties(type,t);
		return typeDao.save(t);
	}

	@Override
	public void deleteType(Long id) {
		typeDao.deleteById(id);
	}

	@Override
	public List<Type> listTypeTop(Integer size) {
		Sort.Order sort = new Sort.Order(Sort.Direction.DESC, "blogs.size");
		Pageable pageable = PageRequest.of(0, size, Sort.by(sort));
		return typeDao.findTop(pageable);
	}

	@Override
	public Type getTypeByName(String name) {
		return typeDao.findByName(name);
	}

	@Override
	public List<Type> listType() {
		return typeDao.findAll();
	}
}
