package org.deloitte.devops.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.deloitte.devops.bo.BoardsServiceBO;
import org.deloitte.devops.bo.DevopsServiceBO;
import org.deloitte.devops.jira.model.AllBoards;
import org.deloitte.devops.jira.model.AllIssuesDisplay;
import org.deloitte.devops.jira.model.AllIssuesResponse;
import org.deloitte.devops.jira.model.AllSprints;
import org.deloitte.devops.jira.model.Sprint;
import org.deloitte.devops.jira.model.SprintDetails;
import org.deloitte.devops.jira.model.StoryGroomingStatus;
import org.deloitte.devops.jira.model.StoryStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {
	private static final Logger LOG = LoggerFactory.getLogger(DevopsController.class);

	private static final String SELECTION_SEPARATOR = ",";
	@Autowired
	private BoardsServiceBO boardsServiceBO;


	@GetMapping(value = "/board/{boardId}/sprintsDetails", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<SprintDetails> getSprintsForBoard(@PathVariable("boardId") String boardId) {
		AllSprints allSprints = boardsServiceBO.getSprintsForBoard(boardId);
		Collections.sort(allSprints.getSprints());
		List<Sprint> sprints = allSprints.getSprints();
		
		List<SprintDetails> list= getIssuesForSprint(boardId,sprints);
		
		SprintDetails total = getTotalForEachColumn(list);
		list.add(total);
		
		
		return list;
	}

	
	private SprintDetails getTotalForEachColumn(List<SprintDetails> list) {
		// TODO Auto-generated method stub
		SprintDetails ststotal = new SprintDetails();
		 int totalStories=0;
		 int totalStoryPointsSFDC=0;
		 int capabilitiesFieldPopulated=0;
		 int tshirtSizeFieldPopulated=0;
		 List<StoryGroomingStatus> lstTotalGroom = new ArrayList<>();
		 List<StoryStatus> lstTotalStatus = new ArrayList<>();

		for(SprintDetails sts:list) {
			totalStories = totalStories+sts.getTotalStories();
		    totalStoryPointsSFDC=totalStoryPointsSFDC+sts.getTotalStoryPointsSFDC();
			capabilitiesFieldPopulated=capabilitiesFieldPopulated+sts.getCapabilitiesFieldPopulated();
			tshirtSizeFieldPopulated=tshirtSizeFieldPopulated+sts.getTshirtSizeFieldPopulated();
			
			for(StoryStatus status : sts.getStatuslist()) {
				
			}
			
            for(StoryGroomingStatus status : sts.getGroomingStatusList()) {
				
			}
		}
		ststotal.setTotalStories(totalStories);
		ststotal.setTotalStoryPointsSFDC(totalStoryPointsSFDC);
		ststotal.setCapabilitiesFieldPopulated(capabilitiesFieldPopulated);
		ststotal.setTshirtSizeFieldPopulated(tshirtSizeFieldPopulated);
		ststotal.setStatuslist(lstTotalStatus);
		ststotal.setGroomingStatusList(lstTotalGroom);
		return ststotal;
	}


	private List<SprintDetails> getIssuesForSprint(String boardId, List<Sprint> sprintId) {
		/*List<String> sprintIds = new ArrayList<>();
		for(Sprint spr : sprintId ) {
			sprintIds.add(spr.getId());
		}*/
		AllIssuesDisplay allIssues = new AllIssuesDisplay();
		
		LOG.info("Fetching issues from [{}] sprint(s)", sprintId.size());
		List<SprintDetails> lst = new ArrayList<>();
		for (Sprint sprint : sprintId) {
			AllIssuesDisplay aid = boardsServiceBO.getAllIssuesForSprintForListOfCustomFields(boardId, sprint.getId());
			
			if (aid != null && !CollectionUtils.isEmpty(aid.getIssues())) {
				allIssues.addIssues(aid.getIssues());
			}
			SprintDetails sd = boardsServiceBO.getSprintDetails(aid);
			sd.setSprintName(sprint.getName());
			LOG.info("Sprint details fetched", sd);
			lst.add(sd);
		}
		

		return lst;
	}


}
