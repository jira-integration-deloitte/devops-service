package org.deloitte.devops.jira.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Creator implements Serializable {

	@JsonProperty("self")
	private String self;
	@JsonProperty("name")
	private String name;
	@JsonProperty("key")
	private String key;
	@JsonProperty("accountId")
	private String accountId;
	@JsonProperty("emailAddress")
	private String emailAddress;
	@JsonProperty("avatarUrls")
	private AvatarUrls avatarUrls;
	@JsonProperty("displayName")
	private String displayName;
	@JsonProperty("active")
	private Boolean active;
	@JsonProperty("timeZone")
	private String timeZone;
	private final static long serialVersionUID = 7354647486416609665L;

	@JsonProperty("self")
	public String getSelf() {
		return self;
	}

	@JsonProperty("self")
	public void setSelf(String self) {
		this.self = self;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("key")
	public String getKey() {
		return key;
	}

	@JsonProperty("key")
	public void setKey(String key) {
		this.key = key;
	}

	@JsonProperty("accountId")
	public String getAccountId() {
		return accountId;
	}

	@JsonProperty("accountId")
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@JsonProperty("emailAddress")
	public String getEmailAddress() {
		return emailAddress;
	}

	@JsonProperty("emailAddress")
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@JsonProperty("avatarUrls")
	public AvatarUrls getAvatarUrls() {
		return avatarUrls;
	}

	@JsonProperty("avatarUrls")
	public void setAvatarUrls(AvatarUrls avatarUrls) {
		this.avatarUrls = avatarUrls;
	}

	@JsonProperty("displayName")
	public String getDisplayName() {
		return displayName;
	}

	@JsonProperty("displayName")
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@JsonProperty("active")
	public Boolean getActive() {
		return active;
	}

	@JsonProperty("active")
	public void setActive(Boolean active) {
		this.active = active;
	}

	@JsonProperty("timeZone")
	public String getTimeZone() {
		return timeZone;
	}

	@JsonProperty("timeZone")
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

}
