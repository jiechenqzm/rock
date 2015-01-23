package com.nd.rock.server.model.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.nd.rock.server.model.dao.PageQueryProxy;
import com.nd.rock.server.view.page.impl.DefaultPageItem;
import com.nd.rock.server.view.page.impl.PageArgs;

public class DefaultPageQueryProxy<E> implements PageQueryProxy<E> {

	public DefaultPageItem<E> pageQuery(JdbcTemplate jdbcTemplate, String countSql,
			String querySql, Object[] args, PageArgs pageArgs,
			RowMapper<E> rowMapper) {

		int totalCount = jdbcTemplate.queryForInt(countSql, args);

		// 计算总页数
		int pageCount = totalCount / pageArgs.getPageSize();
		if (totalCount > pageArgs.getPageSize() * pageCount) {
			pageCount++;
		}

		int start = (pageArgs.getPageNo() - 1) * pageArgs.getPageSize();

		String realQuerSql = querySql + " limit " + start + ","
				+ pageArgs.getPageSize();

		List<E> items = jdbcTemplate.query(realQuerSql, args, rowMapper);

//		DefaultPageItem<E> result = new DefaultPageItem<E>();
//		result.setItems(items);
//		result.setTotalCount(totalCount);
//		result.setPageNo(pageArgs.getPageNo());
//		result.setPageSize(pageArgs.getPageSize());

		return null;
	}

}
