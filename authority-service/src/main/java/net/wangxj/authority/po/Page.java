package net.wangxj.authority.po;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.QueryParam;

import org.hibernate.validator.constraints.NotBlank;

import net.wangxj.util.constant.RegexConstant;

/**
 * @author huoshan
 * created by 2017年5月8日 上午8:41:10
 * 
 */
public class Page {
	
	@NotNull(message = "page_number非法")
	@Min(value = 1 , message = "page_number至少为1")
	@QueryParam("page_number")
	private Integer pageNum;
	@NotNull(message = "limit非法")
	@Min(value = 1 , message = "limit至少为1")
	@QueryParam("limit")
	private Integer limit;
	@Pattern(regexp = RegexConstant.ORDER_STR , message = "order非法")
	@NotBlank(message = "order为必填值")
	@QueryParam("order")
	private String order;
	@QueryParam("sort")
	@NotBlank(message = "sort为必填值")
	private String sort;
	
	public Page() {
		super();
	}

	public Page(Integer pageNum, Integer limit, String order, String sort) {
		super();
		this.pageNum = pageNum;
		this.limit = limit;
		this.order = order;
		this.sort = sort;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "Page [pageNum=" + pageNum + ", limit=" + limit + ", order=" + order + ", sort=" + sort + "]";
	}
	 
}
