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

	public static class DataInBuilder {
		private long id;
		private String group;
		private String dataId;
		private long version;
		private String value;
		private Date gmt_create;
		private Date gmt_modified;

		public CoreDataIn build() {
			return new CoreDataIn(id, group, dataId, version, value,
					gmt_create, gmt_modified);
		}

		public DataInBuilder setId(long id) {
			this.id = id;
			return this;
		}

		public DataInBuilder setGroup(String group) {
			this.group = group;
			return this;
		}

		public DataInBuilder setDataId(String dataId) {
			this.dataId = dataId;
			return this;
		}

		public DataInBuilder setValue(String value) {
			this.value = value;
			return this;
		}

		public DataInBuilder setVersion(long version) {
			this.version = version;
			return this;
		}

		public DataInBuilder setGmt_create(Date gmt_create) {
			this.gmt_create = gmt_create;
			return this;
		}

		public DataInBuilder setGmt_modified(Date gmt_modified) {
			this.gmt_modified = gmt_modified;
			return this;
		}

	}

}
