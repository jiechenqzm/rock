package com.nd.rock.server.instance;

import java.util.Date;

public class CoreDataIn {

	private final long id;

	private final String group;

	private final String dataId;

	private final long version;

	private final String value;

	private final Date gmt_create;

	private final Date gmt_modified;

	public CoreDataIn(long id, String group, String dataId,
			long version, String value, Date gmt_create,
			Date gmt_modified) {
		this.id = id;
		this.group = group;
		this.dataId = dataId;
		this.version = version;
		this.value = value;
		this.gmt_create = gmt_create;
		this.gmt_modified = gmt_modified;
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

	public String getValue() {
		return value;
	}

	public Date getGmt_create() {
		return gmt_create;
	}

	public Date getGmt_modified() {
		return gmt_modified;
	}

	public static class CoreDataBuilder {
		private long id;
		private String group;
		private String dataId;
		private long version;
		private String value;
		private Date gmtCreate;
		private Date gmtModified;

		public CoreDataIn build() {
			return new CoreDataIn(id, group, dataId, version, value,
					gmtCreate, gmtModified);
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

		public CoreDataBuilder setValue(String value) {
			this.value = value;
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

}
