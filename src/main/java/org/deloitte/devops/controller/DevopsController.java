package org.deloitte.devops.controller;

import org.deloitte.devops.bo.DevopsServiceBO;
import org.deloitte.devops.jira.model.AllBoards;
import org.deloitte.devops.jira.model.AllIssuesDisplay;
import org.deloitte.devops.jira.model.AllIssuesResponse;
import org.deloitte.devops.jira.model.AllSprints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DevopsController {
//	private static final Logger LOG = LoggerFactory.getLogger(DevopsController.class);

	@Autowired
	private DevopsServiceBO devopsServiceBO;

	@GetMapping(value = "/alldashboards", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public AllBoards getAllDashboards() {
		return devopsServiceBO.getAllBoards();
	}

	@GetMapping(value = "/board/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public AllIssuesResponse getAllIssues(@PathVariable("id") String boardId) {

		return devopsServiceBO.getAllIssuesForBoard(boardId);
	}

	@GetMapping(value = "/board/{boardId}/sprints", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public AllSprints getSprintsForBoard(@PathVariable("boardId") String boardId) {

		return devopsServiceBO.getSprintsForBoard(boardId);
	}

	@GetMapping(value = "/board/{boardId}/sprint/{sprintId}/stories", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public AllIssuesDisplay getIssuesForSprint(@PathVariable("boardId") String boardId,
			@PathVariable("sprintId") String sprintId) {
		return devopsServiceBO.getAllIssuesForSprint(boardId, sprintId);
	}


}