package org.deloitte.devops.jira.service;

import java.util.ArrayList;
import java.util.List;

import org.deloitte.devops.jenkins.integration.JenkinsIntegration;
import org.deloitte.devops.jira.integration.JiraIntegration;
import org.deloitte.devops.jira.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JiraService {

	@Autowired
	private JiraIntegration jiraIntgration;

	@Autowired
	private JenkinsIntegration jenkinsIntegration;
	public List<Issue> getAllIssues() {
		List<Issue> issues = jiraIntgration.getAllIssues();

		List<Issue> mainList = new ArrayList<>();
		for (Issue issue : issues) {
			if ("Story".equals(issue.getFields().getIssueType().getName())) {
				mainList.add(issue);
			}


		}
		jenkinsIntegration.setBuildStatus(mainList);
		return mainList;
	}


	public Issue getIssue(String id) {
		return jiraIntgration.getIssue(id);
	}

}
