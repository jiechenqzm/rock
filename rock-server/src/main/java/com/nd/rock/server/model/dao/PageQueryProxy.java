package com.nd.rock.server.model.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.nd.rock.server.view.page.impl.DefaultPageItem;
import com.nd.rock.server.view.page.impl.PageArgs;



public interface PageQueryProxy<E> {
	
	/**
	 * @param jdbcTemplate		JdbcTemplate
	 * @param countSql			查询总数量的sql
	 * @param querySql			查询具体记录的sql
	 * @param args				countSql和querySql共有的参数
	 * @param pageArgs			分页相关参数
	 * @param rowMapper			封装最终查询对象的mapper
	 * @return
	 */
	public DefaultPageItem<E> pageQuery(JdbcTemplate jdbcTemplate, String countSql, String querySql, Object[] args, PageArgs pageArgs, RowMapper<E> rowMapper);

}
