package org.deloitte.devops.jira.model;

import java.awt.Color;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Epic implements Serializable {
	private final static long serialVersionUID = -2519058529428422008L;
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("key")
	private String key;
	@JsonProperty("self")
	private String self;
	@JsonProperty("name")
	private String name;
	@JsonProperty("summary")
	private String summary;
	@JsonProperty("color")
	private Color color;
	@JsonProperty("done")
	private Boolean done;

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
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

	@JsonProperty("self")
	public String getSelf() {
		return self;
	}

	@JsonProperty("self")
	public void setSelf(String self) {
		this.self = self;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("summary")
	public String getSummary() {
		return summary;
	}

	@JsonProperty("summary")
	public void setSummary(String summary) {
		this.summary = summary;
	}

	@JsonProperty("color")
	public Color getColor() {
		return color;
	}

	@JsonProperty("color")
	public void setColor(Color color) {
		this.color = color;
	}

	@JsonProperty("done")
	public Boolean getDone() {
		return done;
	}

	@JsonProperty("done")
	public void setDone(Boolean done) {
		this.done = done;
	}
}
