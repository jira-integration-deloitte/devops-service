package org.deloitte.devops.jira.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
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
	private Set<String> statuses;
	private Set<String> groomingStatuses;

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

	public Set<String> getStatuses() {
		return statuses;
	}

	public Set<String> getGroomingStatuses() {
		return groomingStatuses;
	}

	public void setStatuses(Set<String> statuses) {
		this.statuses = statuses;
	}

	public void setGroomingStatuses(Set<String> groomingStatuses) {
		this.groomingStatuses = groomingStatuses;
	}

	private void setTitleMakers() {
		Set<String> storyStatuses = new TreeSet<>();
		Set<String> groomingStatuses = new TreeSet<>();

		for (SprintDetails details : sprintDetails) {
			storyStatuses.addAll(details.getStoryStatuses());
			groomingStatuses.addAll(details.getGroomingStatuses());
		}

		setStatuses(storyStatuses);
		setGroomingStatuses(groomingStatuses);

		setStatusColCount(sizeOf(statuses));
		setGroomingStatusColCount(sizeOf(groomingStatuses));

		// Now sorting by natural order i.e., sort by sprint name
		Collections.sort(this.sprintDetails);
	}

	private int sizeOf(Collection<?> statuses) {
		return statuses == null ? 0 : statuses.size();
	}

}
