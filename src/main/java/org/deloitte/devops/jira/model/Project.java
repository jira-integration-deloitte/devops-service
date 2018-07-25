package org.deloitte.devops.jira.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Project implements Serializable {
	private final static long serialVersionUID = -1576399120659124408L;

	@JsonProperty("self")
	private String self;
	@JsonProperty("id")
	private String id;
	@JsonProperty("key")
	private String key;
	@JsonProperty("name")
	private String name;
	@JsonProperty("projectTypeKey")
	private String projectTypeKey;
	@JsonProperty("avatarUrls")
	private AvatarUrls avatarUrls;

	@JsonProperty("self")
	public String getSelf() {
		return self;
	}

	@JsonProperty("self")
	public void setSelf(String self) {
		this.self = self;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("key")
	public String getKey() {
		return key;
	}

	@JsonProperty("key")
	public void setKey(String key) {
		this.key = key;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("projectTypeKey")
	public String getProjectTypeKey() {
		return projectTypeKey;
	}

	@JsonProperty("projectTypeKey")
	public void setProjectTypeKey(String projectTypeKey) {
		this.projectTypeKey = projectTypeKey;
	}

	@JsonProperty("avatarUrls")
	public AvatarUrls getAvatarUrls() {
		return avatarUrls;
	}

	@JsonProperty("avatarUrls")
	public void setAvatarUrls(AvatarUrls avatarUrls) {
		this.avatarUrls = avatarUrls;
	}
}
