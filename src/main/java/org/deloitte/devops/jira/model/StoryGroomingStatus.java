package org.deloitte.devops.jira.model;

public class StoryGroomingStatus {
	private String groomingStatusName;
	private Integer groomingStatusStoryCount;
	public String getGroomingStatusName() {
		return groomingStatusName;
	}
	public void setGroomingStatusName(String groomingStatusName) {
		this.groomingStatusName = groomingStatusName;
	}
	public Integer getGroomingStatusStoryCount() {
		return groomingStatusStoryCount;
	}
	public void setGroomingStatusStoryCount(Integer groomingStatusStoryCount) {
		this.groomingStatusStoryCount = groomingStatusStoryCount;
	}
}
