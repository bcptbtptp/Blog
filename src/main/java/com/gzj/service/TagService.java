package com.gzj.service;

import com.gzj.pojo.Tag;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @InterfaceName TagService
 * @Description TODO
 * @Author 42
 * @Date 2020/3/25 上午 11:28
 * @Version 1.0
 */
public interface TagService
{
	/**
	 * save a Tag into DB.
	 * @param tag
	 * @return
	 */
	Tag saveTag(Tag tag);

	/**
	 * get a Blog via id.
	 * @param id
	 * @return
	 */
	Tag getTag(Long id);

	/**
	 * get a Tag via tag name.
	 * @param name
	 * @return
	 */
	Tag getTagByName(String name);

	/**
	 * return a collection of tags with count limit.
	 * @param pageable
	 * @return
	 */
	Page<Tag> listTag(Pageable pageable);

	/**
	 * return a collection of all tags.
	 * @return
	 */
	List<Tag> listTag();

	/**
	 * return a list of tags which order by its blog's size desc.
	 * @param page
	 * @param size
	 * @return
	 */
	List<Tag> listTagTop(Integer page, Integer size);

	/**
	 * return a list of tags via a set of ids.
	 * @param ids
	 * @return
	 */
	List<Tag> listTag(String ids);

	/**
	 * First judge this tag whether exsit, then modify and save.
	 * @param id
	 * @param tag
	 * @return
	 */
	Tag updateTag(Long id, Tag tag);

	/**
	 * delete a tag via id.
	 * @param id
	 */
	void deleteTag(Long id);

}
