package org.deloitte.devops.jira.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Priority implements Serializable {
	private final static long serialVersionUID = -140175152024552176L;

	@JsonProperty("self")
	private String self;
	@JsonProperty("iconUrl")
	private String iconUrl;
	@JsonProperty("name")
	private String name;
	@JsonProperty("id")
	private String id;

	@JsonProperty("self")
	public String getSelf() {
		return self;
	}

	@JsonProperty("self")
	public void setSelf(String self) {
		this.self = self;
	}

	@JsonProperty("iconUrl")
	public String getIconUrl() {
		return iconUrl;
	}

	@JsonProperty("iconUrl")
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

}
