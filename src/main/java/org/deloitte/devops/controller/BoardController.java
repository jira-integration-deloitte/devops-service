package org.deloitte.devops.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.deloitte.devops.bo.BoardsServiceBO;
import org.deloitte.devops.jira.model.AllIssuesDisplay;
import org.deloitte.devops.jira.model.AllSprints;
import org.deloitte.devops.jira.model.Sprint;
import org.deloitte.devops.jira.model.SprintDetails;
import org.deloitte.devops.jira.model.SprintSummaryResponse;
import org.deloitte.devops.jira.model.StoryStatus;
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

		//SprintDetails total = getTotalForEachColumn(sprintDetails);
		//sprintDetails.add(total);

		SprintSummaryResponse response = new SprintSummaryResponse(sprintDetails);

		return response;
	}


	private SprintDetails getTotalForEachColumn(List<SprintDetails> list) {
		SprintDetails ststotal = new SprintDetails();
		int totalStories=0;
		int totalStoryPointsSFDC=0;
		int capabilitiesFieldPopulated=0;
		int tshirtSizeFieldPopulated=0;
		List<StoryStatus> lstTotalGroom = new ArrayList<>();
		List<StoryStatus> lstTotalStatus = new ArrayList<>();

		for(SprintDetails sts:list) {
			totalStories = totalStories+sts.getTotalStories();
			totalStoryPointsSFDC=totalStoryPointsSFDC+sts.getTotalStoryPointsSFDC();
			capabilitiesFieldPopulated=capabilitiesFieldPopulated+sts.getCapabilitiesFieldPopulated();
			tshirtSizeFieldPopulated=tshirtSizeFieldPopulated+sts.getTshirtSizeFieldPopulated();

		}
		ststotal.setTotalStories(totalStories);
		ststotal.setTotalStoryPointsSFDC(totalStoryPointsSFDC);
		ststotal.setCapabilitiesFieldPopulated(capabilitiesFieldPopulated);
		ststotal.setTshirtSizeFieldPopulated(tshirtSizeFieldPopulated);
		ststotal.setStatuslist(lstTotalStatus);
		ststotal.setGroomingStatusList(lstTotalGroom);
		return ststotal;
	}


	private List<SprintDetails> getIssuesForSprint(String boardId, List<Sprint> sprintIDs) {
		AllIssuesDisplay allIssues = new AllIssuesDisplay();

		LOG.info("Fetching issues from [{}] sprint(s)", sprintIDs.size());
		List<SprintDetails> lst = new ArrayList<>();
		for (Sprint sprint : sprintIDs) {
			AllIssuesDisplay allIssuesDisplay = boardsServiceBO.getAllIssuesForSprintForListOfCustomFields(boardId, sprint.getId());

			if (allIssuesDisplay != null && !CollectionUtils.isEmpty(allIssuesDisplay.getIssues())) {
				allIssues.addIssues(allIssuesDisplay.getIssues());
			}
			SprintDetails sprintDetails = boardsServiceBO.getSprintDetails(allIssuesDisplay);
			// sprintDetails.setSprintName(sprint.getName());
			LOG.info("Sprint details fetched", sprintDetails);
			lst.add(sprintDetails);
		}


		return lst;
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
