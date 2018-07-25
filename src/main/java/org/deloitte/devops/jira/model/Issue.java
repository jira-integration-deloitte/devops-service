package org.deloitte.devops.jira.model;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Issue {
	@JsonProperty("expand")
	private String expand;
	@JsonProperty("id")
	private String id;
	@JsonProperty("self")
	private String self;
	@JsonProperty("key")
	private String key;
	@JsonProperty("fields")
	private Fields fields;

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

	private int totalStoryPoint;

	public int getTotalStoryPoint() {
		return totalStoryPoint;
	}

	public void setTotalStoryPoint(int totalStoryPoint) {
		this.totalStoryPoint = totalStoryPoint;
	}

	public boolean isStory() {
		if (CollectionUtils.isEmpty(getFields().getIssueLinks())) {
			return false;
		}
		IssueType type = null;
		IssueLink link = getFields().getIssueLinks().get(0);
		if (link.getOutwardIssue() == null || link.getOutwardIssue().getFields() == null
				|| link.getOutwardIssue().getFields().getIssueType() == null) {
			type = getFields().getIssueType();
		} else {
			type = link.getOutwardIssue().getFields().getIssueType();
		}

		return type.getName() != null && "Story".equalsIgnoreCase(type.getName());
	}

}
