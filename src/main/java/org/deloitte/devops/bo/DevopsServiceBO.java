package org.deloitte.devops.bo;

import java.util.Map;

import org.deloitte.devops.helper.JenkinsJiraHelper;
import org.deloitte.devops.jira.integration.JiraEndPoint;
import org.deloitte.devops.jira.model.AllBoards;
import org.deloitte.devops.jira.model.AllIssuesResponse;
import org.deloitte.devops.jira.model.AllSprints;
import org.deloitte.devops.repository.DevopsRepository;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class DevopsServiceBO {

	private DevopsRepository repository;
	private JenkinsJiraHelper helper;

	public DevopsServiceBO(DevopsRepository repository, JenkinsJiraHelper helper) {
		this.repository = repository;
		this.helper = helper;
	}

	public String getAuthResponse(String url, Map<String, Object> uriVariables, String authHeader) {

		return repository.get(url, String.class, uriVariables, authHeader);
	}

	public AllBoards getAllBoards() {
		return helper.exchangeWithJira(HttpMethod.GET, null, null, AllBoards.class, JiraEndPoint.ALL_BOARDS);
	}

	public AllIssuesResponse getAllIssuesForBoard(String boardId) {
		String url = JiraEndPoint.ALL_BOARDS + boardId + "/backlog";
		return helper.exchangeWithJira(HttpMethod.GET, null, null, AllIssuesResponse.class, url);
	}

	public AllSprints getSprintsForBoard(String boardId) {
		String url = JiraEndPoint.ALL_BOARDS + boardId + "/sprint";
		return helper.exchangeWithJira(HttpMethod.GET, null, null, AllSprints.class, url);
	}

	public AllIssuesResponse getAllIssuesForSprint(String boardId, String sprintId) {
		String url = JiraEndPoint.ALL_BOARDS + boardId + "/sprint/" + sprintId + "/issue";
		return helper.exchangeWithJira(HttpMethod.GET, null, null, AllIssuesResponse.class, url);
	}
}
