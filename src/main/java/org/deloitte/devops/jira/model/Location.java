package org.deloitte.devops.jira.model;

import java.io.Serializable;

public class Location implements Serializable {

	private static final long serialVersionUID = 1L;

	private String projectId;
	private String name;
	private String projectTypeKey;
	private String avatarURI;

	public String getProjectId() {
		return projectId;
	}

	public String getName() {
		return name;
	}

	public String getProjectTypeKey() {
		return projectTypeKey;
	}

	public String getAvatarURI() {
		return avatarURI;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProjectTypeKey(String projectTypeKey) {
		this.projectTypeKey = projectTypeKey;
	}

	public void setAvatarURI(String avatarURI) {
		this.avatarURI = avatarURI;
	}

}
