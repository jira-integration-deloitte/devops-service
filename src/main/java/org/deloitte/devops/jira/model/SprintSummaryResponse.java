package org.deloitte.devops.jira.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

public class SprintSummaryResponse implements Serializable {
	private static final long serialVersionUID = -9069041262743825151L;

	public SprintSummaryResponse() {
	}

	public SprintSummaryResponse(List<SprintDetails> sprintDetails) {
		this.sprintDetails = sprintDetails;
		setTitleMakers();
	}

	private List<SprintDetails> sprintDetails;

	private Integer statusColCount;
	private Integer groomingStatusColCount;
	private List<String> statuses;
	private List<String> groomingStatuses;

	public List<SprintDetails> getSprintDetails() {
		return sprintDetails;
	}

	public Integer getStatusColCount() {
		return statusColCount;
	}

	public Integer getGroomingStatusColCount() {
		return groomingStatusColCount;
	}

	public void setSprintDetails(List<SprintDetails> sprintDetails) {
		this.sprintDetails = sprintDetails;
	}

	public void setStatusColCount(Integer statusColCount) {
		this.statusColCount = statusColCount;
	}

	public void setGroomingStatusColCount(Integer groomingStatusColCount) {
		this.groomingStatusColCount = groomingStatusColCount;
	}

	public List<String> getStatuses() {
		return statuses;
	}

	public List<String> getGroomingStatuses() {
		return groomingStatuses;
	}

	public void setStatuses(List<String> statuses) {
		this.statuses = statuses;
	}

	public void setGroomingStatuses(List<String> groomingStatuses) {
		this.groomingStatuses = groomingStatuses;
	}

	private void setTitleMakers() {
		Set<String> storyStatuses = new TreeSet<>();
		Set<String> groomingStatuses = new TreeSet<>();

		for (SprintDetails details : sprintDetails) {
			storyStatuses.addAll(details.getStoryStatuses());
			groomingStatuses.addAll(details.getGroomingStatuses());
		}

		setStatuses(new ArrayList<>(storyStatuses));
		setGroomingStatuses(new ArrayList<>(groomingStatuses));

		setStatusColCount(sizeOf(statuses));
		setGroomingStatusColCount(sizeOf(groomingStatuses));

		// Now sorting by natural order i.e., sort by sprint name
		Collections.sort(this.sprintDetails);

		Collections.sort(this.statuses);
		Collections.sort(this.groomingStatuses);

		for (SprintDetails details : sprintDetails) {
			List<StoryStatus> absentStoryStatuses = new ArrayList<>();
			List<StoryStatus> absentGroomingStatuses = new ArrayList<>();
			for (String status : statuses) {
				Optional<StoryStatus> existing = details.getStatuslist().stream()
						.filter(statusObj -> statusObj.getStatusName().equals(status)).findFirst();
				if (!existing.isPresent()) {
					StoryStatus emptyStatus = new StoryStatus();
					emptyStatus.setStatusName(status);
					emptyStatus.setStoryCount(0);
					absentStoryStatuses.add(emptyStatus);
				}
			}
			for (String status : groomingStatuses) {
				Optional<StoryStatus> existing = details.getGroomingStatusList().stream()
						.filter(statusObj -> statusObj.getStatusName().equals(status)).findFirst();
				if (!existing.isPresent()) {
					StoryStatus emptyStatus = new StoryStatus();
					emptyStatus.setStatusName(status);
					emptyStatus.setStoryCount(0);
					absentGroomingStatuses.add(emptyStatus);
				}
			}

			details.getStatuslist().addAll(absentStoryStatuses);
			details.getGroomingStatusList().addAll(absentGroomingStatuses);

			Collections.sort(details.getStatuslist());
			Collections.sort(details.getGroomingStatusList());
		}

	}

	private int sizeOf(Collection<?> statuses) {
		return statuses == null ? 0 : statuses.size();
	}

}
