package org.deloitte.devops.jira.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Watches implements Serializable {

	@JsonProperty("self")
	private String self;
	@JsonProperty("watchCount")
	private Integer watchCount;
	@JsonProperty("isWatching")
	private Boolean isWatching;
	private final static long serialVersionUID = -3114111636807181237L;

	@JsonProperty("self")
	public String getSelf() {
		return self;
	}

	@JsonProperty("self")
	public void setSelf(String self) {
		this.self = self;
	}

	@JsonProperty("watchCount")
	public Integer getWatchCount() {
		return watchCount;
	}

	@JsonProperty("watchCount")
	public void setWatchCount(Integer watchCount) {
		this.watchCount = watchCount;
	}

	@JsonProperty("isWatching")
	public Boolean getIsWatching() {
		return isWatching;
	}

	@JsonProperty("isWatching")
	public void setIsWatching(Boolean isWatching) {
		this.isWatching = isWatching;
	}

}
