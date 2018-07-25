package org.deloitte.devops.jira.model;

import java.io.Serializable;

public class OutwardIssue implements Serializable {
	private final static long serialVersionUID = 2100793235808374L;
	private String id;
	private String key;
	private String self;

	private OutwardIssueFields fields;

	public String getId() {
		return id;
	}

	public String getKey() {
		return key;
	}

	public String getSelf() {
		return self;
	}

	public OutwardIssueFields getFields() {
		return fields;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	public void setFields(OutwardIssueFields fields) {
		this.fields = fields;
	}

}
