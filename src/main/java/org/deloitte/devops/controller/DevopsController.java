package org.deloitte.devops.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.deloitte.devops.bo.DevopsServiceBO;
import org.deloitte.devops.jira.model.AllBoards;
import org.deloitte.devops.jira.model.AllIssuesDisplay;
import org.deloitte.devops.jira.model.AllIssuesResponse;
import org.deloitte.devops.jira.model.AllSprints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DevopsController {
	private static final Logger LOG = LoggerFactory.getLogger(DevopsController.class);

	private static final String SELECTION_SEPARATOR = ",";
	@Autowired
	private DevopsServiceBO devopsServiceBO;

	@GetMapping(value = "/allboards", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public AllBoards getAllBoards() {
		AllBoards allBoards = devopsServiceBO.getAllBoards();
		Collections.sort(allBoards.getBoards());
		return allBoards;
	}

	@GetMapping(value = "/board/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public AllIssuesResponse getAllIssues(@PathVariable("id") String boardId) {
		return devopsServiceBO.getAllIssuesForBoard(boardId);
	}

	@GetMapping(value = "/board/{boardId}/sprints", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public AllSprints getSprintsForBoard(@PathVariable("boardId") String boardId) {
		AllSprints allSprints = devopsServiceBO.getSprintsForBoard(boardId);
		Collections.sort(allSprints.getSprints());
		return allSprints;
	}

	@GetMapping(value = "/board/{boardId}/sprint/{sprintId}/stories", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public AllIssuesDisplay getIssuesForSprint(@PathVariable("boardId") String boardId,
			@PathVariable("sprintId") String sprintId) {
		List<String> sprintIds = null;
		AllIssuesDisplay allIssues = new AllIssuesDisplay();
		if (sprintId.contains(SELECTION_SEPARATOR)) {
			String[] sprintIdArr = sprintId.split(SELECTION_SEPARATOR);
			sprintIds = Arrays.asList(sprintIdArr);
		} else {
			sprintIds = Arrays.asList(new String[] { sprintId });
		}
		LOG.info("Fetching issues from [{}] sprint(s)", sprintIds.size());

		for (String theSprintId : sprintIds) {
			AllIssuesDisplay aid = devopsServiceBO.getAllIssuesForSprint(boardId, theSprintId);
			if (aid != null && !CollectionUtils.isEmpty(aid.getIssues())) {
				allIssues.addIssues(aid.getIssues());
			}
		}
		Collections.sort(allIssues.getIssues());
		LOG.info("gathered total of [{}] issues", allIssues.getIssues().size());

		return allIssues;
	}

	@GetMapping(value = "/fields/{fieldName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getCustomField(@PathVariable("fieldName") String fieldName) {
		return devopsServiceBO.getCustomField(fieldName);
	}

}
