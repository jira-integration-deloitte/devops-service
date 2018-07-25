package org.deloitte.devops.jira.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class Fields implements Serializable {
	private static final long serialVersionUID = -4132377905833162123L;

	@JsonProperty("epic")
	private Epic epic;

	@JsonProperty("priority")
	private Priority priority;

	@JsonProperty("issuelinks")
	private List<IssueLink> issueLinks;

	@JsonProperty("status")
	private Status status;

	@JsonProperty("creator")
	private Creator creator;

	@JsonProperty("reporter")
	private Reporter reporter;

	@JsonProperty("aggregateprogress")
	private AggregateProgress aggregateProgress;

	@JsonProperty("progress")
	private Progress progress;

	@JsonProperty("votes")
	private Votes votes;

	@JsonProperty("worklog")
	private WorkLog workLog;

	@JsonProperty("issuetype")
	private IssueType issueType;

	@JsonProperty("project")
	private Project project;

	@JsonProperty("workratio")
	private int workRatio;

	@JsonProperty("watches")
	private Watches watches;

	@JsonProperty("created")
	private String created;

	@JsonProperty("updated")
	private String updated;

	@JsonProperty("flagged")
	private boolean flagged;

	@JsonProperty("summary")
	private String summary;

	@JsonProperty("comment")
	private Comment comment;

	@JsonProperty("assignee")
	private Assignee assignee;

	public Epic getEpic() {
		return epic;
	}

	public Priority getPriority() {
		return priority;
	}

	public List<IssueLink> getIssueLinks() {
		return issueLinks;
	}

	public Status getStatus() {
		return status;
	}

	public Creator getCreator() {
		return creator;
	}

	public Reporter getReporter() {
		return reporter;
	}

	public AggregateProgress getAggregateProgress() {
		return aggregateProgress;
	}

	public Progress getProgress() {
		return progress;
	}

	public Votes getVotes() {
		return votes;
	}

	public WorkLog getWorkLog() {
		return workLog;
	}

	public IssueType getIssueType() {
		return issueType;
	}

	public Project getProject() {
		return project;
	}

	public Watches getWatches() {
		return watches;
	}

	public String getCreated() {
		return created;
	}

	public String getUpdated() {
		return updated;
	}

	public boolean isFlagged() {
		return flagged;
	}

	public String getSummary() {
		return summary;
	}

	public Comment getComment() {
		return comment;
	}

	public void setEpic(Epic epic) {
		this.epic = epic;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public void setIssueLinks(List<IssueLink> issueLinks) {
		this.issueLinks = issueLinks;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setCreator(Creator creator) {
		this.creator = creator;
	}

	public void setReporter(Reporter reporter) {
		this.reporter = reporter;
	}

	public void setAggregateProgress(AggregateProgress aggregateProgress) {
		this.aggregateProgress = aggregateProgress;
	}

	public void setProgress(Progress progress) {
		this.progress = progress;
	}

	public void setVotes(Votes votes) {
		this.votes = votes;
	}

	public void setWorkLog(WorkLog workLog) {
		this.workLog = workLog;
	}

	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void setWorkRatio(int workRatio) {
		this.workRatio = workRatio;
	}

	public void setWatches(Watches watches) {
		this.watches = watches;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public int getWorkRatio() {
		return workRatio;
	}

	public Assignee getAssignee() {
		return assignee;
	}

	public void setAssignee(Assignee assignee) {
		this.assignee = assignee;
	}

}