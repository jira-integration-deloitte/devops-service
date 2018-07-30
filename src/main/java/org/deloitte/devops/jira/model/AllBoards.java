package org.deloitte.devops.jira.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AllBoards extends Cachable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("values")
	private List<Board> boards;

	@JsonProperty("maxResults")
	private int maxResults;

	@JsonProperty("startAt")
	private int startAt;

	@JsonProperty("total")
	private int total;

	@JsonProperty("isLast")
	private boolean isLast;

	public List<Board> getBoards() {
		return boards;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public int getStartAt() {
		return startAt;
	}

	public int getTotal() {
		return total;
	}

	public boolean isLast() {
		return isLast;
	}

	public void setBoards(List<Board> boards) {
		this.boards = boards;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	public void setStartAt(int startAt) {
		this.startAt = startAt;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}
}
