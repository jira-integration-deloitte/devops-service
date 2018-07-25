package org.deloitte.devops.jira.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AggregateProgress implements Serializable {

	@JsonProperty("progress")
	private Integer progress;
	@JsonProperty("total")
	private Integer total;
	private final static long serialVersionUID = 4289041032268279680L;

	@JsonProperty("progress")
	public Integer getProgress() {
		return progress;
	}

	@JsonProperty("progress")
	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	@JsonProperty("total")
	public Integer getTotal() {
		return total;
	}

	@JsonProperty("total")
	public void setTotal(Integer total) {
		this.total = total;
	}

}
