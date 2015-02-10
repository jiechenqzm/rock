package com.nd.rock.server.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.nd.rock.server.model.dao.CoreDataDAO;
import com.nd.rock.server.model.dao.CoreDataDAOCallable;
import com.nd.rock.server.model.dao.PageQueryProxy;
import com.nd.rock.server.model.instance.CoreDataIn;
import com.nd.rock.server.model.instance.CoreDataIn.CoreDataBuilder;
import com.nd.rock.server.view.page.PageItems;
import com.nd.rock.server.view.page.impl.PageArgs;

public class CoreDataDAOImpl implements CoreDataDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(CoreDataDAOImpl.class);

	private JdbcTemplate jdbcTemplate = null;
	
	@Override
	public int logicInsert(CoreDataIn dataIn) {
		CoreDataIn oriIn = this.query(dataIn.getGroup(), dataIn.getDataId());
		if(oriIn == null)
			return insert(dataIn);
		else if(oriIn.isDeleted())
			return update(oriIn.getGroup(), oriIn.getDataId(), oriIn.getVersion(), oriIn.isDeleted(), dataIn.getContent(), dataIn.getSummary(), false);
		else 
			return 0;			
	}
	
	@Override
	public int logicUpdate(String group, String dataId, long version,
			String newContent, String summary) {
		return update(group, dataId, version, false, newContent, summary, false);
	}
	
	@Override
	public int logicDelete(String group, String dataId, long version) {
		String sql = "update core_data set `version` = ?, `deleted` = ?, `gmt_modified` = ? where `group` = ? and `data_id` = ? and `version` = ? and `deleted` = ?";

		Object[] args = new Object[7];
		args[0] = version + 1;
		args[1] = true;
		args[2] = new Date();
		
		args[3] = group;
		args[4] = dataId;
		args[5] = version;
		args[6] = false;
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int insert(CoreDataIn dataIn) {
		String sql = "insert into core_data (`id`, `group`, `data_id`, `version`, `summary`, `deleted`, `content`, `gmt_create`, `gmt_modified`) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] args = new Object[9];
		args[0] = 0l;
		args[1] = dataIn.getGroup();
		args[2] = dataIn.getDataId();
		args[3] = dataIn.getVersion();
		args[4] = dataIn.getSummary();
		args[5] = dataIn.isDeleted();
		args[6] = dataIn.getContent();
		args[7] = new Date();
		args[8] = new Date();
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int update(String group, String dataId, long version, boolean deleted, 
			String newContent, String newSummary, boolean newDeleted) {
		String sql = "update core_data set `version` = ?, `summary` = ?, `deleted` = ?, `content` = ?, `gmt_modified` = ? where `group` = ? and `data_id` = ? and `version` = ? and `deleted` = ?";

		Object[] args = new Object[9];
		args[0] = version + 1;
		args[1] = newSummary;
		args[2] = newDeleted;
		
		args[3] = newContent;
		args[4] = new Date();
		args[5] = group;
		args[6] = dataId;
		args[7] = version;
		args[8] = deleted;
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int delete(String group, String dataId, long version) {
		String sql = "delete from core_data where `group` = ? and `data_id` = ? and `version` = ?";
		Object[] args = new Object[3];
		args[0] = group;
		args[1] = dataId;
		args[2] = version;
		return jdbcTemplate.update(sql, args);
	}
	
	@Override
	public CoreDataIn logicQuery(String group, String dataId) {
		return query(group, dataId, false);
	}

	@Override
	public CoreDataIn query(String group, String dataId, boolean deleted) {
		String sql = "select `id`, `group`, `data_id`, `version`, `summary`, `deleted`, `content`, `gmt_create`, `gmt_modified` from core_data where `group` = ? and `data_id` = ? and `deleted` = ?";
		Object[] args = new Object[3];
		args[0] = group;
		args[1] = dataId;
		args[2] = deleted;
		CoreDataIn result = null;
		try {
			result = jdbcTemplate.queryForObject(sql, args,
					new CoreDataInMapper());
		} catch (EmptyResultDataAccessException e) {
			// 没有结果则返回null由上层处理
		}
		return result;
	}
	
	@Override
	public CoreDataIn query(String group, String dataId) {
		String sql = "select `id`, `group`, `data_id`, `version`, `summary`, `deleted`, `content`, `gmt_create`, `gmt_modified` from core_data where `group` = ? and `data_id` = ?";
		Object[] args = new Object[2];
		args[0] = group;
		args[1] = dataId;
		CoreDataIn result = null;
		try {
			result = jdbcTemplate.queryForObject(sql, args,
					new CoreDataInMapper());
		} catch (EmptyResultDataAccessException e) {
			// 没有结果则返回null由上层处理
		}
		return result;
	}
	
	

	@Override
	public void logicQueryAll(CoreDataDAOCallable callable) throws SQLException {
		String sql = "select `id`, `group`, `data_id`, `version`, `summary`, `deleted`, `content`, `gmt_create`, `gmt_modified` from core_data where `deleted` = ?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = jdbcTemplate.getDataSource().getConnection();
			ps = con.prepareStatement(sql);
			ps.setFetchSize(10000);
			ps.setBoolean(1, false);
			rs = ps.executeQuery();
			while (rs.next()) {
				callable.accept(buildCoreDataIn(rs));
			}
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				logger.error("Execute Close Method Error In ResultSet,PreparedStatement,Connection.");
			}
		}
	}

	private static final String COUNT_SQL = "select count(`id`) from core_data where `group` = ? and `data_id` like ? and `deleted` = ?";

	private static final String QUERY_SQL = "select `id`, `group`, `data_id`, `version`, `summary`, `deleted`, `content`, `gmt_create`, `gmt_modified` from core_data where `group` = ? and `data_id` like ? and `deleted` = ?";

	private PageQueryProxy<CoreDataIn> pageQueryProxy = new DefaultPageQueryProxy<>();
	
	@Override
	public PageItems<CoreDataIn> pageFuzzyQueryData(String group, String dataId, boolean deleted, int pageNo, int pageSize) {
		Object[] args = new Object[3];
		args[0] = group;
		args[1] = getFuzzyDataId(dataId);
		args[2] = deleted;
		
		PageArgs pageArgs = buildPageArgs(pageNo, pageSize);
		
		PageItems<CoreDataIn> result = this.pageQueryProxy.pageQuery(this.jdbcTemplate, COUNT_SQL, QUERY_SQL, args, pageArgs, new CoreDataInMapper());
		return result;
	}

	private String getFuzzyDataId(String dataId) {
		return dataId.startsWith("%") || dataId.endsWith("%") ? dataId : "%"
				+ dataId + "%";
	}

	private PageArgs buildPageArgs(int pageNo, int pageSize) {
		return new PageArgs(pageNo, pageSize);
	}

	protected class CoreDataInMapper implements RowMapper<CoreDataIn> {
		public CoreDataIn mapRow(ResultSet rs, int rowNum) throws SQLException {
			return buildCoreDataIn(rs);
		}
	}

	private CoreDataIn buildCoreDataIn(ResultSet rs) throws SQLException {
		CoreDataBuilder resultBuilder = new CoreDataBuilder();
		resultBuilder.setId(rs.getLong("id"));
		resultBuilder.setGroup(rs.getString("group"));
		resultBuilder.setDataId(rs.getString("data_id"));
		resultBuilder.setVersion(rs.getLong("version"));
		resultBuilder.setSummary(rs.getString("summary"));
		resultBuilder.setDeleted(rs.getBoolean("deleted"));
		resultBuilder.setContent(rs.getString("content"));
		resultBuilder.setGmtCreate(rs.getTimestamp("gmt_create"));
		resultBuilder.setGmtModified(rs.getTimestamp("gmt_modified"));
		return resultBuilder.build();
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
