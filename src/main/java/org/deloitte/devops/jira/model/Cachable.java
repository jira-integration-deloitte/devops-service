package org.deloitte.devops.jira.model;

import java.io.Serializable;

public class Cachable implements Serializable {

	private static final long serialVersionUID = 5498282681002163965L;

	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
