package org.deloitte.devops.jira.model;

import java.io.Serializable;

public class OutwardIssueFields implements Serializable {
	private final static long serialVersionUID = 210086949493808374L;

	private String summary;
	private Status status;
	private Priority priority;

	private IssueType issueType;

	public String getSummary() {
		return summary;
	}

	public Status getStatus() {
		return status;
	}

	public Priority getPriority() {
		return priority;
	}

	public IssueType getIssueType() {
		return issueType;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}

}
