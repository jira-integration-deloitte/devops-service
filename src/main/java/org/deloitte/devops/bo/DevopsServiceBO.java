package org.deloitte.devops.bo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.deloitte.devops.helper.JenkinsJiraHelper;
import org.deloitte.devops.jira.integration.JiraEndPoint;
import org.deloitte.devops.jira.model.AllBoards;
import org.deloitte.devops.jira.model.AllIssuesDisplay;
import org.deloitte.devops.jira.model.AllIssuesResponse;
import org.deloitte.devops.jira.model.AllSprints;
import org.deloitte.devops.jira.model.Issue;
import org.deloitte.devops.repository.DevopsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class DevopsServiceBO {
	private static final Logger LOG = LoggerFactory.getLogger(DevopsServiceBO.class);
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

	public AllIssuesDisplay getAllIssuesForSprint(String boardId, String sprintId) {
		String url = JiraEndPoint.ALL_BOARDS + boardId + "/sprint/" + sprintId + "/issue";
		AllIssuesResponse allIssues = helper.exchangeWithJira(HttpMethod.GET, null, null, AllIssuesResponse.class, url);
		LOG.info("Number of issues - [{}]", allIssues.getIssues().size());

		return getStoriesFromIssues(allIssues);
	}

	private AllIssuesDisplay getStoriesFromIssues(AllIssuesResponse issues) {
		AllIssuesDisplay issuesToDisplay = null;

		if (issues != null && !CollectionUtils.isEmpty(issues.getIssues())) {
			List<Issue> allIssues = issues.getIssues();

			List<Issue> stories = allIssues.stream().filter(i -> i.isStory()).collect(Collectors.toList());

			LOG.info("Number of stories - [{}]", stories.size());

			issuesToDisplay = new AllIssuesDisplay(stories);
		}

		return issuesToDisplay;
	}
}
