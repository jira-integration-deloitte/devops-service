package org.deloitte.devops.jira.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AllSprints implements Serializable {
	private static final long serialVersionUID = 9109342170873117886L;

	private int maxResults;
	private int startAt;
	private boolean isLast;
	private List<String> errorMessages;

	@JsonProperty("values")
	private List<Sprint> sprints;

	public int getMaxResults() {
		return maxResults;
	}

	public int getStartAt() {
		return startAt;
	}

	public boolean isLast() {
		return isLast;
	}

	public List<Sprint> getSprints() {
		return sprints;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	public void setStartAt(int startAt) {
		this.startAt = startAt;
	}

	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}

	public void setSprints(List<Sprint> sprints) {
		this.sprints = sprints;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

}
