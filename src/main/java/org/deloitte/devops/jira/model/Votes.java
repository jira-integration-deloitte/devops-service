package org.deloitte.devops.jira.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Votes implements Serializable {

	@JsonProperty("self")
	private String self;
	@JsonProperty("votes")
	private Integer votes;
	@JsonProperty("hasVoted")
	private Boolean hasVoted;
	private final static long serialVersionUID = 5672496241783164261L;

	@JsonProperty("self")
	public String getSelf() {
		return self;
	}

	@JsonProperty("self")
	public void setSelf(String self) {
		this.self = self;
	}

	@JsonProperty("votes")
	public Integer getVotes() {
		return votes;
	}

	@JsonProperty("votes")
	public void setVotes(Integer votes) {
		this.votes = votes;
	}

	@JsonProperty("hasVoted")
	public Boolean getHasVoted() {
		return hasVoted;
	}

	@JsonProperty("hasVoted")
	public void setHasVoted(Boolean hasVoted) {
		this.hasVoted = hasVoted;
	}

}
