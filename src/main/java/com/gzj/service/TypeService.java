package com.gzj.service;

import com.gzj.pojo.Type;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @InterfaceName TypeService
 * @Description TODO
 * @Author 42
 * @Date 2020/3/24 上午 9:49
 * @Version 1.0
 */
public interface TypeService
{
	/**
	 * save a Type into DB.
	 * @param type
	 * @return
	 */
	Type saveType(Type type);

	/**
	 * get a Type via id.
	 * @param id
	 * @return
	 */
	Type getType(Long id);

	/**
	 * get a Type via type name.
	 * @param name
	 * @return
	 */
	Type getTypeByName(String name);

	List<Type> listType();
	/**
	 * return a collection of types with count limit.
	 * @param pageable
	 * @return
	 */
	Page<Type> listType(Pageable pageable);

	/**
	 * First judge this type whether exsit, then modify and save.
	 * @param id
	 * @param type
	 * @return
	 */
	Type updateType(Long id, Type type);

	/**
	 * delete a Type via id.
	 * @param id
	 */
	void deleteType(Long id);

	List<Type> listTypeTop(Integer size);
}
