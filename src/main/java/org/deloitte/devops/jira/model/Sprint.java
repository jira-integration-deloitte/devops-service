package org.deloitte.devops.jira.model;

import java.io.Serializable;

public class Sprint implements Serializable {
	private static final long serialVersionUID = 9109342170873490694L;

	private String id;
	private String self;
	private String state;
	private String name;
	private String startDate;
	private String endDate;
	private String completeDate;
	private String originBoardId;
	private String goal;

	public String getId() {
		return id;
	}

	public String getSelf() {
		return self;
	}

	public String getState() {
		return state;
	}

	public String getName() {
		return name;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getCompleteDate() {
		return completeDate;
	}

	public String getOriginBoardId() {
		return originBoardId;
	}

	public String getGoal() {
		return goal;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setCompleteDate(String completeDate) {
		this.completeDate = completeDate;
	}

	public void setOriginBoardId(String originBoardId) {
		this.originBoardId = originBoardId;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	@Override
	public String toString() {
		return "Sprint [id=" + id + ", self=" + self + ", state=" + state + ", name=" + name + ", startDate="
				+ startDate + ", endDate=" + endDate + ", completeDate=" + completeDate + ", originBoardId="
				+ originBoardId + ", goal=" + goal + "]";
	}

}
