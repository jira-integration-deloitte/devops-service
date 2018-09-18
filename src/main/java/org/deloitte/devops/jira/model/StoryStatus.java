package org.deloitte.devops.jira.model;

public class StoryStatus implements Comparable<StoryStatus> {

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

	@Override
	public int compareTo(StoryStatus o) {
		if (o == null) {
			return 1;
		}
		if (this.statusName == o.statusName) {
			return 0;
		}
		if (this.statusName == null) {
			return -1;
		}
		if (o.statusName == null) {
			return 1;
		}
		return this.statusName.compareTo(o.statusName);
	}
}
