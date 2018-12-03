package org.deloitte.devops.jira.model;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Issue {
	private int storyPoint;
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
	//Naman Kaushik code for new requirement
	private String capability;
	private String tShirtSize;
	private String groomingStatus;

	public String getCapability() {
		return capability;
	}

	public void setCapability(String capability) {
		System.err.println("Updating capability [" + this + "] by Thread - [" + Thread.currentThread().getId() + "-"
				+ Thread.currentThread().getName() + "]");
		this.capability = capability;
	}

	public String gettShirtSize() {
		return tShirtSize;
	}

	public void settShirtSize(String tShirtSize) {
		System.err.println("Updating tShirt size [" + this + "] by Thread - [" + Thread.currentThread().getId() + "-"
				+ Thread.currentThread().getName() + "]");
		this.tShirtSize = tShirtSize;
	}

	public String getGroomingStatus() {
		return groomingStatus;
	}

	public void setGroomingStatus(String groomingStatus) {
		System.err.println("Updating grooming status [" + this + "] by Thread - [" + Thread.currentThread().getId()
				+ "-" + Thread.currentThread().getName() + "]");
		this.groomingStatus = groomingStatus;
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

	private int totalStoryPoint;

	public int getTotalStoryPoint() {
		return totalStoryPoint;
	}

	public void setTotalStoryPoint(int totalStoryPoint) {
		this.totalStoryPoint = totalStoryPoint;
	}

	public int getStoryPoint() {
		return storyPoint;
	}

	public void setStoryPoint(int storyPoint) {
		System.err.println("Updating story point [" + this + "] by Thread - [" + Thread.currentThread().getId() + "-"
				+ Thread.currentThread().getName() + "]");
		this.storyPoint = storyPoint;
	}

	public boolean isStory() {
		boolean isStory = false;
		if (getFields().getIssueType() != null && getFields().getIssueType().getName() != null) {
			isStory = "Story".equalsIgnoreCase(getFields().getIssueType().getName());
		}
		IssueType type = null;
		if (!isStory) {
			if (CollectionUtils.isEmpty(getFields().getIssueLinks())) {
				return false;
			}
			for (IssueLink link : getFields().getIssueLinks()) {
				if (link.getOutwardIssue() == null || link.getOutwardIssue().getFields() == null
						|| link.getOutwardIssue().getFields().getIssueType() == null) {
					type = getFields().getIssueType();
				} else {
					type = link.getOutwardIssue().getFields().getIssueType();
				}
				isStory = type.getName() != null && "Story".equalsIgnoreCase(type.getName());

				if (isStory) {
					break;
				}
			}

		}
		return isStory;
	}

}
