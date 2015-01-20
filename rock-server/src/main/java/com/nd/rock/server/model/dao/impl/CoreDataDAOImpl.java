package com.nd.rock.server.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.nd.rock.server.model.dao.CoreDataDAO;
import com.nd.rock.server.model.dao.CoreDataDAOCallable;
import com.nd.rock.server.model.instance.CoreDataIn;
import com.nd.rock.server.model.instance.CoreDataIn.CoreDataBuilder;

public class CoreDataDAOImpl implements CoreDataDAO {

	private JdbcTemplate jdbcTemplate = null;
	
	@Override
	public int insert(CoreDataIn dataIn) {
		String sql = "insert into core_data (`id`, `group`, `data_id`, `version`, `summary`, `value`, `gmt_create`, `gmt_modified`) values (?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] args = new Object[8];
		args[0] = 0l;
		args[1] = dataIn.getGroup();
		args[2] = dataIn.getDataId();
		args[3] = dataIn.getVersion();
		args[4] = dataIn.getSummary();
		args[5] = dataIn.getValue();
		args[6] = new Date();
		args[7] = new Date();
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int update(String group, String dataId, long oriVersion,
			String newValue, String summary) {
		String sql = "update core_data set `version` = ?, `summary` = ?, `value` = ?, `gmt_modified` = ? where `group` = ? and `data_id` = ? and `version` = ?";
		Object[] args = new Object[7];
		args[0] = oriVersion + 1;
		args[1] = summary;
		args[2] = newValue;
		args[3] = new Date();
		args[4] = group;
		args[5] = dataId;
		args[6] = newValue;
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int delete(String group, String dataId) {
		String sql = "delete from core_data where `group` = ? and `data_id` = ?";
		Object[] args = new Object[2];
		args[0] = group;
		args[1] = dataId;
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public CoreDataIn query(String group, String dataId) {
		String sql = "select `id`, `group`, `data_id`, `version`, `summary`, `value`, `gmt_create`, `gmt_modified` from core_data where `group` = ? and `data_id` = ?";
		Object[] args = new Object[2];
		args[0] = group;
		args[1] = dataId;
		return jdbcTemplate.queryForObject(sql, args, new CoreDataInMapper());
	}

	@Override
	public List<CoreDataIn> queryAll(int batchGetNumn,
			CoreDataDAOCallable callable) {

		String sql = "select id, group, data_id, version, summary, value, gmt_create, gmt_modified from core_data";
		List<CoreDataIn> batchResult = new ArrayList<CoreDataIn>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = jdbcTemplate.getDataSource().getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			int rowNum = 0;
			while (rs.next()) {
				batchResult.add(buildCoreDataIn(rs));
				if (++rowNum % 10000 == 0) {
					callable.accept(batchResult);
					batchResult.clear();
				}
			}
			callable.accept(batchResult);
		} catch (SQLException e) {
			//TODO log and throw 
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(ps != null)
					ps.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				//TODO log and throw 
			}
		}

		return null;
	}
	
	@Override
	public List<CoreDataIn> fuzzyQuery(String group, String fuzzyDataId) {
		String sql = "select `id`, `group`, `data_id`, `version`, `summary`, `value`, `gmt_create`, `gmt_modified` from core_data where `group` = ? and `data_id` like ?";
		Object[] args = new Object[2];
		args[0] = group;
		args[1] = fuzzyDataId;
		return jdbcTemplate.query(sql, args, new CoreDataInMapper());
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
		resultBuilder.setValue(rs.getString("value"));
		resultBuilder.setGmtCreate(rs.getDate("gmt_create"));
		resultBuilder.setGmtModified(rs.getDate("gmt_modified"));
		return resultBuilder.build();
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
