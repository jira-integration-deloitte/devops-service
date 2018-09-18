package org.deloitte.devops.jira.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SprintDetails implements Comparable<SprintDetails> {

	private Integer totalStories;
	private Integer totalStoryPointsSFDC;
	private Integer capabilitiesFieldPopulated;
	private Integer tshirtSizeFieldPopulated;
	private List<StoryStatus> statuslist;
	private String sprintName;
	private List<StoryStatus> groomingStatusList;

	public String getSprintName() {
		return sprintName;
	}
	public void setSprintName(String sprintName) {
		this.sprintName = sprintName;
	}
	public List<StoryStatus> getStatuslist() {
		return statuslist;
	}
	public void setStatuslist(List<StoryStatus> statuslist) {
		this.statuslist = statuslist;
	}

	public List<StoryStatus> getGroomingStatusList() {
		return groomingStatusList;
	}

	public void setGroomingStatusList(List<StoryStatus> groomingStatusList) {
		this.groomingStatusList = groomingStatusList;
	}
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

	public List<String> getStoryStatuses() {
		List<String> storyStatuses = new ArrayList<>();
		for (StoryStatus status : statuslist) {
			storyStatuses.add(status.getStatusName().trim());
		}
		return storyStatuses;
	}

	public List<String> getGroomingStatuses() {
		List<String> storyStatuses = new ArrayList<>();
		for (StoryStatus status : groomingStatusList) {
			storyStatuses.add(status.getStatusName().trim());
		}
		return storyStatuses;
	}

	public void createStoryStatuses(List<String> statuses) {
		this.setStatuslist(new ArrayList<>());
		for (String status : statuses) {
			this.statuslist.add(new StoryStatus(status));
		}
		Collections.sort(this.statuslist);
	}

	public void createGroomingStatuses(List<String> statuses) {
		this.setGroomingStatusList(new ArrayList<>());

		for (String status : statuses) {
			this.groomingStatusList.add(new StoryStatus(status));
		}

		Collections.sort(this.groomingStatusList);
	}

	@Override
	public int compareTo(SprintDetails o) {
		if (o == null) {
			return 1;
		}
		if (this.sprintName == o.sprintName) {
			return 0;
		}
		if (this.sprintName == null) {
			return -1;
		}
		if (o.sprintName == null) {
			return 1;
		}
		return this.sprintName.compareTo(o.sprintName);
	}

}
