package org.deloitte.devops.jira.model;

import java.io.Serializable;

public class IssueResponse implements Serializable {

	private static final long serialVersionUID = -5014942171688048848L;
	
	private String expand;
	private String id;
	private String self;
	private String key;
	private Fields fields;
	private int storyPoint;
	public int getStoryPoint() {
		return storyPoint;
	}
	public void setStoryPoint(int storyPoint) {
		this.storyPoint = storyPoint;
	}
	public String getExpand() {
		return expand;
	}
	public String getId() {
		return id;
	}
	public String getSelf() {
		return self;
	}
	public String getKey() {
		return key;
	}
	public Fields getFields() {
		return fields;
	}
	public void setExpand(String expand) {
		this.expand = expand;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void setFields(Fields fields) {
		this.fields = fields;
	}
	@Override
	public String toString() {
		return "IssueResponse [expand=" + expand + ", id=" + id + ", self=" + self + ", key=" + key + ", fields="
				+ fields + "]";
	}
	
	
}
