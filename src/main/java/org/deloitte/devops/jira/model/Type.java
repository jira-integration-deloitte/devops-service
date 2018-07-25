package org.deloitte.devops.jira.model;

import java.io.Serializable;

public class Type implements Serializable {
	private static final long serialVersionUID = 2495373878611393862L;

	private String id;
	private String name;
	private String inward;
	private String outward;
	private String self;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getInward() {
		return inward;
	}

	public String getOutward() {
		return outward;
	}

	public String getSelf() {
		return self;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setInward(String inward) {
		this.inward = inward;
	}

	public void setOutward(String outward) {
		this.outward = outward;
	}

	public void setSelf(String self) {
		this.self = self;
	}

}
