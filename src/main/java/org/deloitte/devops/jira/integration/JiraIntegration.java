package org.deloitte.devops.jira.integration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.deloitte.devops.helper.JenkinsJiraHelper;
import org.deloitte.devops.jira.model.AllIssuesResponse;
import org.deloitte.devops.jira.model.Issue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class JiraIntegration {
	private static final Logger LOG = LoggerFactory.getLogger(JiraIntegration.class);

	@Autowired
	private JenkinsJiraHelper helper;
	public List<Issue> getAllIssues() {
		Map<String, Object> allIssuesParamMap = new HashMap<>();
		// allIssuesParamMap.put("jql", "project=\"DevOps Portal Project\"");
		// allIssuesParamMap.put("fields",
		// "id,key,description,summary,creator,status,issuetype,customfield_10201");
		// allIssuesParamMap.put("startAt", 0);
		// allIssuesParamMap.put("maxResults", 50);
		//		ParameterizedTypeReference<AllIssuesResponse> ptr = typeReferenceHelper.getTypeRefecence(AllIssuesResponse.class);

		AllIssuesResponse result = helper.exchangeWithJira(HttpMethod.GET, allIssuesParamMap, null,
				AllIssuesResponse.class, JiraEndPoint.GENERAL_SEARCH);

		LOG.info("Result - - [{}]", result);
		return result.getIssues();
	}

	public Issue getIssue(String id) {
		Issue result = helper.exchangeWithJira(HttpMethod.GET, null, null, Issue.class, JiraEndPoint.GET_ISSUE, id);
		LOG.info("Result - - [{}]", result);
		return result;

	}
}
