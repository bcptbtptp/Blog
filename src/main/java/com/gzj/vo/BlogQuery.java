package com.gzj.vo;

/**
 * @ClassName BlogQuery
 * @Description TODO
 * @Author 42
 * @Date 2020/3/25 下午 4:26
 * @Version 1.0
 */
public class BlogQuery
{
	private String title;
	private Long typeId;
	private boolean recommend;

	public BlogQuery() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public boolean isRecommend() {
		return recommend;
	}

	public void setRecommend(boolean recommend) {
		this.recommend = recommend;
	}
}
