package com.nd.rock.server.instance;

import java.util.Date;

public class DataIn {

	private final long id;

	private final String app;

	private final String group;

	private final String dataId;

	private final String value;

	private final String desc;

	private final long version;

	private final Date gmt_create;

	private final Date gmt_modified;

	public DataIn(long id, String app, String group, String dataId,
			String value, String desc, long version, Date gmt_create,
			Date gmt_modified) {
		this.id = id;
		this.app = app;
		this.group = group;
		this.dataId = dataId;
		this.value = value;
		this.desc = desc;
		this.version = version;
		this.gmt_create = gmt_create;
		this.gmt_modified = gmt_modified;
	}

	public long getId() {
		return id;
	}

	public String getApp() {
		return app;
	}

	public String getGroup() {
		return group;
	}

	public String getDataId() {
		return dataId;
	}

	public String getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}

	public long getVersion() {
		return version;
	}

	public Date getGmt_create() {
		return gmt_create;
	}

	public Date getGmt_modified() {
		return gmt_modified;
	}

	public static class DataInBuilder {
		private long id;
		private String app;
		private String group;
		private String dataId;
		private String value;
		private String desc;
		private long version;
		private Date gmt_create;
		private Date gmt_modified;

		public DataInBuilder() {
		}

		public DataIn build() {
			return new DataIn(id, app, group, dataId, value, desc, version,
					gmt_create, gmt_modified);
		}

		public DataInBuilder setId(long id) {
			this.id = id;
			return this;
		}

		public DataInBuilder setApp(String app) {
			this.app = app;
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

		public DataInBuilder setDesc(String desc) {
			this.desc = desc;
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
