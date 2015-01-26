package com.nd.rock.server.model.instance;

import java.util.Date;

import com.nd.rock.server.model.summary.MD5Summary;
import com.nd.rock.server.model.summary.SummaryBehavior;

public class CoreDataIn {

	private final long id;

	private final String group;

	private final String dataId;

	private final long version;

	private final String summary;

	private final String content;

	private final Date gmtCreate;

	private final Date gmtModified;

	public CoreDataIn(long id, String group, String dataId,
			long version, String summary, String content, Date gmt_create,
			Date gmt_modified) {
		this.id = id;
		this.group = group;
		this.dataId = dataId;
		this.version = version;
		this.content = content;
		this.summary = summary;
		this.gmtCreate = gmt_create;
		this.gmtModified = gmt_modified;
	}

	public long getId() {
		return id;
	}

	public String getGroup() {
		return group;
	}

	public String getDataId() {
		return dataId;
	}

	public long getVersion() {
		return version;
	}
	
	public String getSummary() {
		return summary;
	}

	public String getContent() {
		return content;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public static class CoreDataBuilder {
		private long id;
		private String group;
		private String dataId;
		private long version;
		private String summary;
		private String content;
		private Date gmtCreate;
		private Date gmtModified;
		
		public CoreDataIn build() {
			return new CoreDataIn(id, group, dataId, version, summary, 
					content, gmtCreate, gmtModified);
		}

		public CoreDataBuilder setId(long id) {
			this.id = id;
			return this;
		}

		public CoreDataBuilder setGroup(String group) {
			this.group = group;
			return this;
		}

		public CoreDataBuilder setDataId(String dataId) {
			this.dataId = dataId;
			return this;
		}

		public CoreDataBuilder setSummary(String summary) {
			this.summary = summary;
			return this;
		}

		public CoreDataBuilder setContent(String content) {
			this.content = content;
			return this;
		}

		public CoreDataBuilder setVersion(long version) {
			this.version = version;
			return this;
		}

		public CoreDataBuilder setGmtCreate(Date gmtCreate) {
			this.gmtCreate = gmtCreate;
			return this;
		}

		public CoreDataBuilder setGmtModified(Date gmtModified) {
			this.gmtModified = gmtModified;
			return this;
		}
		
	}

	private static final SummaryBehavior summaryBehavior = new MD5Summary();
	
	public static String calculateSummary(String content) {
		return summaryBehavior.calculate(content);
	}

}
