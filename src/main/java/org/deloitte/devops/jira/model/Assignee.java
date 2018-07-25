package org.deloitte.devops.jira.model;

import java.io.Serializable;

public class Assignee implements Serializable {

	private static final long serialVersionUID = 7640570368469775506L;

	private String self;
	private String name;
	private String key;
	private String accountId;
	private String emailAddress;
	private String displayName;
	private AvatarUrls avatarUrls;
	private boolean active;
	private String timeZone;

	public String getSelf() {
		return self;
	}

	public String getName() {
		return name;
	}

	public String getKey() {
		return key;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getDisplayName() {
		return displayName;
	}

	public AvatarUrls getAvatarUrls() {
		return avatarUrls;
	}

	public boolean isActive() {
		return active;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setAvatarUrls(AvatarUrls avatarUrls) {
		this.avatarUrls = avatarUrls;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

}
