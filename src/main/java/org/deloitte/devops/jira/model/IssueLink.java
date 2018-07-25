package org.deloitte.devops.jira.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IssueLink implements Serializable {
	private final static long serialVersionUID = -7252100793235808374L;

	@JsonProperty("id")
	private String id;

	@JsonProperty("self")
	private String self;

	@JsonProperty("type")
	private Type type;

	@JsonProperty("outwardIssue")
	private OutwardIssue outwardIssue;

	@JsonProperty("inwardIssue")
	private InwardIssue inwardIssue;

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("self")
	public String getSelf() {
		return self;
	}

	@JsonProperty("self")
	public void setSelf(String self) {
		this.self = self;
	}

	@JsonProperty("type")
	public Type getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(Type type) {
		this.type = type;
	}

	@JsonProperty("outwardIssue")
	public OutwardIssue getOutwardIssue() {
		return outwardIssue;
	}

	@JsonProperty("outwardIssue")
	public void setOutwardIssue(OutwardIssue outwardIssue) {
		this.outwardIssue = outwardIssue;
	}

	@JsonProperty("inwardIssue")
	public InwardIssue getInwardIssue() {
		return inwardIssue;
	}

	@JsonProperty("inwardIssue")
	public void setInwardIssue(InwardIssue inwardIssue) {
		this.inwardIssue = inwardIssue;
	}

}
