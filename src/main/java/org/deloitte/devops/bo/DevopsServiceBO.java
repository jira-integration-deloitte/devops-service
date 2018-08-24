package org.deloitte.devops.bo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.deloitte.devops.config.ApplicationEnvironment;
import org.deloitte.devops.helper.JenkinsJiraHelper;
import org.deloitte.devops.jira.integration.JiraEndPoint;
import org.deloitte.devops.jira.model.AllBoards;
import org.deloitte.devops.jira.model.AllIssuesDisplay;
import org.deloitte.devops.jira.model.AllIssuesResponse;
import org.deloitte.devops.jira.model.AllSprints;
import org.deloitte.devops.jira.model.CustomField;
import org.deloitte.devops.jira.model.Issue;
import org.deloitte.devops.repository.DevopsRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class DevopsServiceBO {
	private static final Logger LOG = LoggerFactory.getLogger(DevopsServiceBO.class);
	private DevopsRepository repository;
	private JenkinsJiraHelper helper;

	@Autowired
	private ApplicationEnvironment environment;

	public DevopsServiceBO(DevopsRepository repository, JenkinsJiraHelper helper) {
		this.repository = repository;
		this.helper = helper;
	}

	public String getAuthResponse(String url, Map<String, Object> uriVariables, String authHeader) {
		return repository.get(url, String.class, uriVariables, authHeader);
	}

	public AllBoards getAllBoards() {
		AllBoards allBoards = helper.exchangeWithJira(HttpMethod.GET, null, null, AllBoards.class,
				JiraEndPoint.ALL_BOARDS);
		return allBoards;
	}

	public AllIssuesResponse getAllIssuesForBoard(String boardId) {
		String url = JiraEndPoint.ALL_BOARDS + boardId + "/backlog";
		return helper.exchangeWithJira(HttpMethod.GET, null, null, AllIssuesResponse.class, url);
	}

	public AllSprints getSprintsForBoard(String boardId) {
		return helper.exchangeWithJira(HttpMethod.GET, null, null, AllSprints.class, JiraEndPoint.ALL_BOARDS, boardId, "/sprint");
	}

	public AllIssuesDisplay getAllIssuesForSprint(String boardId, String sprintId) {
		String url = JiraEndPoint.ALL_BOARDS + boardId + "/sprint/" + sprintId + "/issue?maxResults=500";

		AllIssuesResponse allIssues = helper.exchangeWithJira(HttpMethod.GET, null, null, AllIssuesResponse.class, url);

		LOG.info("Number of issues - [{}]", allIssues.getIssues().size());
		String customFieldJson = getCustomField("SP");
		LOG.info("Custom field for story point - [{}]", customFieldJson);

		String customField = new JSONObject(customFieldJson).getString("customField");

		return getStoriesFromIssues(allIssues, customField);
	}

	public String getCustomField(String fieldName) {
		LOG.info("Field to retrieve - [{}]", fieldName);
		String customFieldMapper = environment.getCustomFields();

		ParameterizedTypeReference<List<CustomField>> customFieldType = new ParameterizedTypeReference<List<CustomField>>() {
		};

		String field = new JSONObject(customFieldMapper).getString(fieldName);

		List<CustomField> customFields = helper.exchangeWithJira(HttpMethod.GET, null, null, customFieldType,
				"/rest/api/2/field");

		if (!CollectionUtils.isEmpty(customFields)) {
			for (CustomField cf : customFields) {
				if (field.equals(cf.getName())) {
					return "{\"customField\" : \"" + cf.getKey() + "\"}";
				}
			}
		}
		return null;
	}

	private AllIssuesDisplay getStoriesFromIssues(AllIssuesResponse issues, String customField) {
		AllIssuesDisplay issuesToDisplay = null;

		if (issues != null && !CollectionUtils.isEmpty(issues.getIssues())) {
			List<Issue> allIssues = issues.getIssues();

			List<Issue> stories = allIssues.stream().filter(i -> i.isStory()).collect(Collectors.toList());

			updateWithStoryPoint(stories, customField);

			LOG.info("Number of stories - [{}]", stories.size());

			issuesToDisplay = new AllIssuesDisplay(stories);
		}

		return issuesToDisplay;
	}

	private void updateWithStoryPoint(List<Issue> stories, String customField) {
		for (Issue story : stories) {
			String url = story.getSelf();
			url = url.replace(environment.getJiraURL(), "");

			int storyPoints = 0;

			try {
				ParameterizedTypeReference<LinkedHashMap<String, Object>> ptr = new ParameterizedTypeReference<LinkedHashMap<String, Object>>() {
				};
				Map<String, Object> issueDetailsStr = helper.exchangeWithJira(HttpMethod.GET, null, null, ptr, url);

				storyPoints = new JSONObject(issueDetailsStr).getJSONObject("fields").getInt(customField);
				LOG.info("Story point for the story with id [{}] is [{}]", story.getId(), storyPoints);
			} catch (Exception e) {
				LOG.error("Unable to fetch story point - [{}]", e.getMessage());
			}

			story.setStoryPoint(storyPoints);
		}

	}
}
