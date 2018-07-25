package org.deloitte.devops.jira.model;

import java.io.Serializable;

public class Schema implements Serializable {
	private static final long serialVersionUID = 1L;
	private String type;
	private String custom;
	private String customId;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCustom() {
		return custom;
	}

	public void setCustom(String custom) {
		this.custom = custom;
	}

	public String getCustomId() {
		return customId;
	}

	public void setCustomId(String customId) {
		this.customId = customId;
	}

}
