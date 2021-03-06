package org.deloitte.devops.jira.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class AllIssuesDisplay implements Serializable {

	private static final long serialVersionUID = 1738364864501507198L;

	private List<IssueDisplay> issues;

	public AllIssuesDisplay() {
		this.issues = new ArrayList<>();
	}

	public AllIssuesDisplay(List<Issue> issues) {
		this();
		for (Issue i : issues) {
			IssueDisplay id = new IssueDisplay();

			id.setId(i.getId());
			if (!CollectionUtils.isEmpty(i.getFields().getIssueLinks())) {
				IssueLink link = i.getFields().getIssueLinks().get(0);

				IssueType type = null;

				if (link.getOutwardIssue() == null || link.getOutwardIssue().getFields() == null
						|| link.getOutwardIssue().getFields().getIssueType() == null) {
					type = i.getFields().getIssueType();
					id.setDescription(type.getDescription());
				} else {
					type = link.getOutwardIssue().getFields().getIssueType();
					id.setDescription(link.getOutwardIssue().getFields().getSummary());
				}

				if (link.getOutwardIssue() != null && link.getOutwardIssue().getFields() != null) {
					id.setDescription(link.getOutwardIssue().getFields().getSummary());
				} else if (link.getInwardIssue() != null && link.getInwardIssue().getFields() != null) {
					id.setDescription(link.getInwardIssue().getFields().getSummary());
				} else {
					id.setDescription(type.getName());
				}

				if (i.getFields().getProject() != null) {
					id.setProjectName(i.getFields().getProject().getName());
				}
				if (i.getFields().getSprint() != null) {
					id.setSprintName(i.getFields().getSprint().getName());
				} else if (!CollectionUtils.isEmpty(i.getFields().getClosedSprints())) {
					Sprint closedSprint = i.getFields().getClosedSprints().get(0);
					id.setSprintName(closedSprint.getName());
				}

				id.setName(type.getName());

			}

			Assignee assignee = i.getFields().getAssignee();
			if (assignee != null && assignee.getAvatarUrls() != null) {
				id.setAssignedTo(assignee.getDisplayName());

				id.setUrlSm(assignee.getAvatarUrls().get16x16());
				id.setUrlMd(assignee.getAvatarUrls().get24x24());
				id.setUrlLg(assignee.getAvatarUrls().get32x32());
			}
			id.setStatus(i.getFields().getStatus().getName());
			id.setStatusColor(i.getFields().getStatus().getStatusCategory().getColorName());
			
			id.setStoryPoint(i.getStoryPoint());
			//Naman Kaushik code for new requirement
			id.setCapability(i.getCapability());
			id.setGroomingStatus(i.getGroomingStatus());
			id.settShirtSize(i.gettShirtSize());
			
			if (StringUtils.isEmpty(id.getDescription())) {
				
				id.setDescription("No Description exists for this story");
			}
			this.issues.add(id);
		}
	}

	public List<IssueDisplay> getIssues() {
		return issues;
	}

	public void setIssues(List<IssueDisplay> issues) {
		this.issues = issues;
	}

	public void addIssues(List<IssueDisplay> issues) {
		if (CollectionUtils.isEmpty(this.issues)) {
			setIssues(issues);
		} else {
			this.issues.addAll(issues);
		}
	}

	public static class IssueDisplay implements Comparable<IssueDisplay> {
		private String id;
		private String name;
		private String description;
		private String assignedTo;
		private String status;
		private String statusColor;
		private String urlSm;
		private String urlMd;
		private String urlLg;
		private int storyPoint;
		private String sprintName;
		private String projectName;
		//Naman Kaushik code for new requirement
		private String capability;
		public String getCapability() {
			return capability;
		}

		public void setCapability(String capability) {
			this.capability = capability;
		}

		public String gettShirtSize() {
			return tShirtSize;
		}

		public void settShirtSize(String tShirtSize) {
			this.tShirtSize = tShirtSize;
		}

		public String getGroomingStatus() {
			return groomingStatus;
		}

		public void setGroomingStatus(String groomingStatus) {
			this.groomingStatus = groomingStatus;
		}

		private String tShirtSize;
		private String groomingStatus;
		
		public String getUrlSm() {
			return urlSm;
		}

		public String getUrlMd() {
			return urlMd;
		}

		public String getUrlLg() {
			return urlLg;
		}

		public void setUrlSm(String urlSm) {
			this.urlSm = urlSm;
		}

		public void setUrlMd(String urlMd) {
			this.urlMd = urlMd;
		}

		public void setUrlLg(String urlLg) {
			this.urlLg = urlLg;
		}

		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getAssignedTo() {
			return assignedTo;
		}

		public String getStatus() {
			return status;
		}

		public String getStatusColor() {
			return statusColor;
		}

		public void setId(String id) {
			this.id = id;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setAssignedTo(String assignedTo) {
			this.assignedTo = assignedTo;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public void setStatusColor(String statusColor) {
			this.statusColor = statusColor;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public int getStoryPoint() {
			return storyPoint;
		}

		public void setStoryPoint(int storyPoint) {
			this.storyPoint = storyPoint;
		}

		public String getSprintName() {
			return sprintName;
		}

		public void setSprintName(String sprintName) {
			this.sprintName = sprintName;
		}

		public String getProjectName() {
			return projectName;
		}

		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}

		@Override
		public int compareTo(IssueDisplay o) {
			if (o == null) {
				return -1;
			}
			if (this.projectName == null) {
				if (o.projectName == null) {
					return 0;
				} else {
					return 1;
				}
			}
			if(o.projectName==null) {
				return -1;
			}
			return this.projectName.compareTo(o.projectName);
		}
	}

}
