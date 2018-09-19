package org.deloitte.devops.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.deloitte.devops.bo.BoardsServiceBO;
import org.deloitte.devops.jira.model.AllIssuesDisplay;
import org.deloitte.devops.jira.model.AllSprints;
import org.deloitte.devops.jira.model.Sprint;
import org.deloitte.devops.jira.model.SprintDetails;
import org.deloitte.devops.jira.model.SprintSummaryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {
	private static final Logger LOG = LoggerFactory.getLogger(DevopsController.class);

	@Autowired
	private BoardsServiceBO boardsServiceBO;

	@GetMapping(value = "/board/{boardId}/sprintsDetails", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object getSprintsForBoard(@PathVariable("boardId") String boardId,
			@RequestParam(required = false) boolean testOnly) {

		if (testOnly) {
			return testResult();
		}

		AllSprints allSprints = boardsServiceBO.getSprintsForBoard(boardId);
		Collections.sort(allSprints.getSprints());
		List<Sprint> sprints = allSprints.getSprints();

		List<SprintDetails> sprintDetails = getIssuesForSprint(boardId, sprints);

		SprintSummaryResponse response = new SprintSummaryResponse(sprintDetails);

		return response;
	}

	private List<SprintDetails> getIssuesForSprint(String boardId, List<Sprint> sprintIDs) {
		AllIssuesDisplay allIssues = new AllIssuesDisplay();

		LOG.info("Fetching issues from [{}] sprint(s)", sprintIDs.size());
		List<SprintDetails> sprints = new CopyOnWriteArrayList<>();

		Future<SprintDetails> future = null;
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for (Sprint sprint : sprintIDs) {
			Callable<SprintDetails> allIssuesDisplayCall = new Callable<SprintDetails>() {

				@Override
				public SprintDetails call() throws Exception {
					AllIssuesDisplay allIssuesDisplay = boardsServiceBO.getAllIssuesForSprintForListOfCustomFields(boardId, sprint.getId());
					if (allIssuesDisplay != null && !CollectionUtils.isEmpty(allIssuesDisplay.getIssues())) {
						allIssues.addIssues(allIssuesDisplay.getIssues());
					}

					SprintDetails sprintDetails = boardsServiceBO.getSprintDetails(allIssuesDisplay);
					// sprintDetails.setSprintName(sprint.getName());
					LOG.info("Sprint details fetched", sprintDetails);

					return sprintDetails;
				}
			};

			future = executorService.submit(allIssuesDisplayCall);
			try {
				SprintDetails sprintDetails = future.get();
				sprints.add(sprintDetails);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}


		executorService.shutdown();
		return sprints;
	}

	private String testResult() {
		try {
			FileReader reader = new FileReader(
					this.getClass().getClassLoader().getResource("sampleResponse.json").getFile());
			BufferedReader br = new BufferedReader(reader);
			StringBuilder sb = new StringBuilder();
			String read = null;
			while ((read = br.readLine()) != null) {
				sb.append(read);
			}
			br.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "[]";
		}
	}

}
