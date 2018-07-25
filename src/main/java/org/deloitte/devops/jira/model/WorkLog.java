package org.deloitte.devops.jira.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkLog implements Serializable {

	@JsonProperty("startAt")
	private Integer startAt;
	@JsonProperty("maxResults")
	private Integer maxResults;
	@JsonProperty("total")
	private Integer total;
	@JsonProperty("worklogs")
	private List<Object> worklogs = null;
	private final static long serialVersionUID = -6219064795352715272L;

	@JsonProperty("startAt")
	public Integer getStartAt() {
		return startAt;
	}

	@JsonProperty("startAt")
	public void setStartAt(Integer startAt) {
		this.startAt = startAt;
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

	@JsonProperty("worklogs")
	public List<Object> getWorklogs() {
		return worklogs;
	}

	@JsonProperty("worklogs")
	public void setWorklogs(List<Object> worklogs) {
		this.worklogs = worklogs;
	}

}
