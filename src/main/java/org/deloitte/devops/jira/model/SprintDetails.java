package org.deloitte.devops.jira.model;

import java.util.List;

public class SprintDetails {

	private Integer totalStories;
	private Integer totalStoryPointsSFDC;
	private Integer capabilitiesFieldPopulated;
	private Integer tshirtSizeFieldPopulated;
	private List<StoryStatus> statuslist;
	public List<StoryStatus> getStatuslist() {
		return statuslist;
	}
	public void setStatuslist(List<StoryStatus> statuslist) {
		this.statuslist = statuslist;
	}
	public List<StoryGroomingStatus> getGroomingStatusList() {
		return groomingStatusList;
	}
	public void setGroomingStatusList(List<StoryGroomingStatus> groomingStatusList) {
		this.groomingStatusList = groomingStatusList;
	}
	private List<StoryGroomingStatus> groomingStatusList;
	public Integer getTotalStories() {
		return totalStories;
	}
	public void setTotalStories(Integer totalStories) {
		this.totalStories = totalStories;
	}
	public Integer getTotalStoryPointsSFDC() {
		return totalStoryPointsSFDC;
	}
	public void setTotalStoryPointsSFDC(Integer totalStoryPointsSFDC) {
		this.totalStoryPointsSFDC = totalStoryPointsSFDC;
	}
	public Integer getCapabilitiesFieldPopulated() {
		return capabilitiesFieldPopulated;
	}
	public void setCapabilitiesFieldPopulated(Integer capabilitiesFieldPopulated) {
		this.capabilitiesFieldPopulated = capabilitiesFieldPopulated;
	}
	public Integer getTshirtSizeFieldPopulated() {
		return tshirtSizeFieldPopulated;
	}
	public void setTshirtSizeFieldPopulated(Integer tshirtSizeFieldPopulated) {
		this.tshirtSizeFieldPopulated = tshirtSizeFieldPopulated;
	}
	
}
