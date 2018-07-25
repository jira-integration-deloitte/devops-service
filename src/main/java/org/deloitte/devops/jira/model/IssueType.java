package org.deloitte.devops.jira.model;

public class IssueType {

	private String name;
	private String self;
	private String id;
	private String description;
	private String iconUrl;
	private String avatarId;
	private boolean subtask;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getSelf() {
		return self;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public String getAvatarId() {
		return avatarId;
	}

	public boolean isSubtask() {
		return subtask;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public void setAvatarId(String avatarId) {
		this.avatarId = avatarId;
	}

	public void setSubtask(boolean subtask) {
		this.subtask = subtask;
	}

}
