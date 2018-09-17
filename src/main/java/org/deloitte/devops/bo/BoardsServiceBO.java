package org.deloitte.devops.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.deloitte.devops.config.ApplicationEnvironment;
import org.deloitte.devops.helper.JenkinsJiraHelper;
import org.deloitte.devops.jira.integration.JiraEndPoint;
import org.deloitte.devops.jira.model.AllIssuesDisplay;
import org.deloitte.devops.jira.model.AllIssuesDisplay.IssueDisplay;
import org.deloitte.devops.jira.model.AllIssuesResponse;
import org.deloitte.devops.jira.model.AllSprints;
import org.deloitte.devops.jira.model.CustomField;
import org.deloitte.devops.jira.model.GroomingStatus;
import org.deloitte.devops.jira.model.Issue;
import org.deloitte.devops.jira.model.SprintDetails;
import org.deloitte.devops.jira.model.StoryStatus;
import org.deloitte.devops.repository.DevopsRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BoardsServiceBO {
	private static final Logger LOG = LoggerFactory.getLogger(DevopsServiceBO.class);
	private DevopsRepository repository;
	private JenkinsJiraHelper helper;

	@Autowired
	private ApplicationEnvironment environment;

	public BoardsServiceBO(DevopsRepository repository, JenkinsJiraHelper helper) {
		this.repository = repository;
		this.helper = helper;
	}

	public String getAuthResponse(String url, Map<String, Object> uriVariables, String authHeader) {
		return repository.get(url, String.class, uriVariables, authHeader);
	}

	public AllSprints getSprintsForBoard(String boardId) {
		return helper.exchangeWithJira(HttpMethod.GET, null, null, AllSprints.class, JiraEndPoint.ALL_BOARDS, boardId,
				"/sprint");
	}

	// Naman Kaushik code
	public AllIssuesDisplay getAllIssuesForSprintForListOfCustomFields(String boardId, String sprintId) {
		String url = JiraEndPoint.ALL_BOARDS + boardId + "/sprint/" + sprintId + "/issue?maxResults=500";

		AllIssuesResponse allIssues = helper.exchangeWithJira(HttpMethod.GET, null, null, AllIssuesResponse.class, url);

		LOG.info("Number of issues - [{}]", allIssues.getIssues().size());
		String[] customFieldsList = { "SP", "Capabilities", "T-shirt", "GS" };
		List<String> customFieldJsonList = new ArrayList<>();
		for (String field : customFieldsList) {
			String customFieldJson = getCustomField(field);
			String customField = new JSONObject(customFieldJson).getString("customField");
			customFieldJsonList.add(customField);
		}
		// String customFieldJson = getCustomField("SP");
		LOG.info("Custom field for story point - [{}]", customFieldJsonList);

		// String customField = new
		// JSONObject(customFieldJsonList).getString("customField");

		return getStoriesFromIssues(allIssues, customFieldJsonList);
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

	private AllIssuesDisplay getStoriesFromIssues(AllIssuesResponse issues, List<String> customFieldList) {
		AllIssuesDisplay issuesToDisplay = null;

		if (issues != null && !CollectionUtils.isEmpty(issues.getIssues())) {
			List<Issue> allIssues = issues.getIssues();

			List<Issue> stories = allIssues.stream().filter(i -> i.isStory()).collect(Collectors.toList());

			updateStoryDetails(stories, customFieldList);

			LOG.info("Number of stories - [{}]", stories.size());

			issuesToDisplay = new AllIssuesDisplay(stories);
		}

		return issuesToDisplay;
	}

	private void updateStoryDetails(List<Issue> stories, List<String> customFieldList) {
		for (Issue story : stories) {
			String url = story.getSelf();
			url = url.replace(environment.getJiraURL(), "");

			int storyPoints = 0;
			String capability = "";
			String tShirtSize = "";
			GroomingStatus groomingStatusObj = new GroomingStatus();
			try{
				ParameterizedTypeReference<LinkedHashMap<String, Object>> ptr = new ParameterizedTypeReference<LinkedHashMap<String, Object>>() {
				};
				Map<String, Object> issueDetailsStr = helper.exchangeWithJira(HttpMethod.GET, null, null, ptr, url);
				try {
					Object customFieldObj = new JSONObject(issueDetailsStr).getJSONObject("fields")
							.get(customFieldList.get(0));
					if (customFieldObj instanceof JSONObject) {
						JSONObject customFieldJO = (JSONObject) customFieldObj;
						storyPoints = customFieldJO.getInt("value");
					} else {
						storyPoints = Integer.parseInt((String) customFieldObj);
						
					}
					LOG.info("Story point for the story with id [{}] is [{}]", story.getId(), storyPoints);
				}
				catch (Exception e) {
					LOG.error("Unable to fetch story point - [{}]", e.getMessage());
				}
				try {
					capability = new JSONObject(issueDetailsStr).getJSONObject("fields").get(customFieldList.get(1))
							.toString();
					LOG.info("Capabilities for the story with id [{}] is [{}]", story.getId(), capability);
				}catch (Exception e) {
					LOG.error("Unable to fetch capability - [{}]", e.getMessage());
				}
				try {
					tShirtSize = new JSONObject(issueDetailsStr).getJSONObject("fields").get(customFieldList.get(2))
							.toString();
					LOG.info("Tshirt size for the story with id [{}] is [{}]", story.getId(), tShirtSize);
				}
				catch (Exception e) {
					LOG.error("Unable to fetch tShirtSize - [{}]", e.getMessage());
				}
				try {
					String groomingStatus = new JSONObject(issueDetailsStr).getJSONObject("fields")
							.get(customFieldList.get(3)).toString();
					ObjectMapper mapper = new ObjectMapper();
					groomingStatusObj = mapper.readValue(groomingStatus, GroomingStatus.class);
					LOG.info("Grooming status for the story with id [{}] is [{}]", story.getId(), groomingStatus);
				}
				catch (Exception e) {
					LOG.error("Some exception occured while fetching Grooming status", e.getMessage());
				}
			} catch (Exception e) {
				LOG.error("Some exception occured while fetching issue details.", e.getMessage());
			}

			story.setStoryPoint(storyPoints);
			story.setCapability(capability);
			story.setGroomingStatus(groomingStatusObj.getValue());
			story.settShirtSize(tShirtSize);
		}

	}

	public SprintDetails getSprintDetails(AllIssuesDisplay allIssuesDisplay) {
		SprintDetails sprintDetails = new SprintDetails();
		List<IssueDisplay> storyList = allIssuesDisplay.getIssues();
		Integer storiesCount = storyList.size();
		Integer sumOfStoryPoints = addTotalStoryPoints(storyList);
		Integer numOfCapabilititesField = calculateCapabilitiesFieldPopulated(storyList);
		Integer numOfTShirtSizeField = calculateTShirtSizeFieldPopulated(storyList);
		Map<String, Integer> statusMap = gatherTotalStatusCounts(storyList);
		List<StoryStatus> statusList = new ArrayList<>();
		List<StoryStatus> groomingStatusList = new ArrayList<>();
		StoryStatus storyStatusObj = null;
		if (statusMap != null) {
			for (String status : statusMap.keySet()) {
				storyStatusObj = new StoryStatus();
				storyStatusObj.setStatusName(status);
				storyStatusObj.setStoryCount(statusMap.get(status));
				statusList.add(storyStatusObj);
			}
		}
		Map<String, Integer> groomingStatusMap = gatherTotalGroomingStatusCounts(storyList);

		if (groomingStatusMap != null) {
			for (String groomingStatus : groomingStatusMap.keySet()) {
				StoryStatus storyGroomingStatusObj = new StoryStatus();
				storyGroomingStatusObj.setStatusName(groomingStatus);
				storyGroomingStatusObj.setStoryCount(groomingStatusMap.get(groomingStatus));
				groomingStatusList.add(storyGroomingStatusObj);
			}
		}
		sprintDetails.setTotalStoryPointsSFDC(sumOfStoryPoints);
		sprintDetails.setTotalStories(storiesCount);
		sprintDetails.setCapabilitiesFieldPopulated(numOfCapabilititesField);
		sprintDetails.setTshirtSizeFieldPopulated(numOfTShirtSizeField);
		sprintDetails.setGroomingStatusList(groomingStatusList);
		sprintDetails.setStatuslist(statusList);
		sprintDetails.setSprintName(storyList.get(0).getSprintName());
		return sprintDetails;
	}

	private Integer addTotalStoryPoints(List<IssueDisplay> storyList) {
		Integer totalStoryPoints = 0;
		for (IssueDisplay story : storyList) {
			totalStoryPoints += story.getStoryPoint();
		}
		return totalStoryPoints;
	}

	private Integer calculateCapabilitiesFieldPopulated(List<IssueDisplay> storyList) {
		Integer totalStoriesWithCapabilityField = 0;
		for (IssueDisplay story : storyList) {
			if (!StringUtils.isEmpty(story.getCapability())) {
				totalStoriesWithCapabilityField++;
			}
		}
		return totalStoriesWithCapabilityField;
	}

	private Integer calculateTShirtSizeFieldPopulated(List<IssueDisplay> storyList) {
		Integer totalStoriesWithTShirtSizeField = 0;
		for (IssueDisplay story : storyList) {
			if (!StringUtils.isEmpty(story.gettShirtSize())) {
				totalStoriesWithTShirtSizeField++;
			}
		}
		return totalStoriesWithTShirtSizeField;
	}

	private Map<String, Integer> gatherTotalStatusCounts(List<IssueDisplay> storyList) {
		Map<String, Integer> statusMap = new HashMap<String, Integer>();
		Integer statusCounts = 1;
		for (IssueDisplay story : storyList) {
			if (!StringUtils.isEmpty(story.getStatus())) {
				if (statusMap.containsKey(story.getStatus())) {
					statusMap.put(story.getStatus(), statusMap.get(story.getStatus()) + 1);
				} else {
					statusMap.put(story.getStatus(), statusCounts);
				}
			}
		}
		return statusMap;
	}

	private Map<String, Integer> gatherTotalGroomingStatusCounts(List<IssueDisplay> storyList) {
		Map<String, Integer> groomingStatusMap = new HashMap<String, Integer>();
		Integer statusCounts = 1;
		for (IssueDisplay story : storyList) {
			if (!StringUtils.isEmpty(story.getGroomingStatus())) {
				if (groomingStatusMap.containsKey(story.getGroomingStatus())) {
					groomingStatusMap.put(story.getGroomingStatus(),
							groomingStatusMap.get(story.getGroomingStatus()) + 1);
				} else {
					groomingStatusMap.put(story.getGroomingStatus(), statusCounts);
				}
			}
		}
		return groomingStatusMap;
	}
}
