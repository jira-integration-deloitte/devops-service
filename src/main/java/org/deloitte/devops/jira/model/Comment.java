package org.deloitte.devops.jira.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Comment implements Serializable {

	@JsonProperty("comments")
	private List<Object> comments = null;
	@JsonProperty("maxResults")
	private Integer maxResults;
	@JsonProperty("total")
	private Integer total;
	@JsonProperty("startAt")
	private Integer startAt;
	private final static long serialVersionUID = 6756770783939025951L;

	@JsonProperty("comments")
	public List<Object> getComments() {
		return comments;
	}

	@JsonProperty("comments")
	public void setComments(List<Object> comments) {
		this.comments = comments;
	}

	@JsonProperty("maxResults")
	public Integer getMaxResults() {
		return maxResults;
	}

	@JsonProperty("maxResults")
	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

	@JsonProperty("total")
	public Integer getTotal() {
		return total;
	}

	@JsonProperty("total")
	public void setTotal(Integer total) {
		this.total = total;
	}

	@JsonProperty("startAt")
	public Integer getStartAt() {
		return startAt;
	}

	@JsonProperty("startAt")
	public void setStartAt(Integer startAt) {
		this.startAt = startAt;
	}

}
