package org.deloitte.devops.jira.model;

public class StoryStatus {

	private String statusName;
	private Integer storyCount;
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public Integer getStoryCount() {
		return storyCount;
	}
	public void setStoryCount(Integer storyCount) {
		this.storyCount = storyCount;
	}
}
