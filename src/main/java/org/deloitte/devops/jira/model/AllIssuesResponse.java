package org.deloitte.devops.jira.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AllIssuesResponse {
	@JsonProperty("expand")
	private String expand;
	@JsonProperty("startAt")
	private Integer startAt;
	@JsonProperty("maxResults")
	private Integer maxResults;
	@JsonProperty("total")
	private Integer total;

	@JsonProperty("issues")
	private List<Issue> issues;

	public String getExpand() {
		return expand;
	}

	public Integer getStartAt() {
		return startAt;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public Integer getTotal() {
		return total;
	}

	public List<Issue> getIssues() {
		return issues;
	}

	public void setExpand(String expand) {
		this.expand = expand;
	}

	public void setStartAt(Integer startAt) {
		this.startAt = startAt;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

}
