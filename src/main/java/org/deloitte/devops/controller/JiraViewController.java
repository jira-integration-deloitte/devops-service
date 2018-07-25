package org.deloitte.devops.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.deloitte.devops.jira.model.Issue;
import org.deloitte.devops.jira.model.User;
import org.deloitte.devops.jira.service.JiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JiraViewController {

	@Autowired
	private JiraService jiraService;

	@Value("${welcome.message:test}")
	private String message;

	@RequestMapping("/welcome")
	public String welcome(ModelMap model) {
		model.addAttribute("message", this.message);
		List<User> user = new ArrayList<>();
		User us = new User();
		us.setDisplayName("Neel");
		us.setEmailAddress("abc@abc.com");
		user.add(us);
		model.addAttribute("list", user);
		return "welcome";
	}

	@RequestMapping("/getAllIssues")
	public String getAllIssues(ModelMap model) {
		List<Issue> list = jiraService.getAllIssues();
		model.addAttribute("list", list);
		model.addAttribute("name", "Monika Goyal");
		model.addAttribute("subHeader", "Eli Lilly");

		return "list";
	}

	@RequestMapping("/getIssue/{Id}")
	public String getIssue(ModelMap model, @PathVariable final String Id) {
		Issue issue = jiraService.getIssue(Id);
		if (issue != null) {
			String status = issue.getFields().getStatus().getName();
			String subStatus = "";
			String subBoxStatus = "";

			String boxStatus = getBox(status);
			model.addAttribute("status", status);
			model.addAttribute("subStatus", subStatus);
			model.addAttribute("box", boxStatus);
			model.addAttribute("summary", issue.getFields().getSummary());
			model.addAttribute("description", issue.getFields().getStatus().getDescription());
			model.addAttribute("key", issue.getKey());
			model.addAttribute("id", issue.getId());
			// model.addAttribute("buildStatus", issue.getFields().getBuildStatus());

			Map<String, String> map = new HashMap<>();
			if (boxStatus.equals("User Story Grooming")) {
				map.put("box1", "yellow");
				map.put("box2", "grey");
				map.put("box3", "grey");
				map.put("box4", "grey");
				map.put("box5", "grey");
				map.put("box6", "grey");
				map.put("box9", "grey");
			}
			if (boxStatus.equals("User Story Approved")) {
				map.put("box1", "green");
				map.put("box2", "yellow");
				map.put("box3", "grey");
				map.put("box4", "grey");
				map.put("box5", "grey");
				map.put("box6", "grey");
				map.put("box9", "grey");
				model.addAttribute("map", map);
			}
			if (boxStatus.equals("Dev Box")) {
				map.put("box1", "green");
				map.put("box2", "green");
				map.put("box3", "yellow");
				map.put("box4", "grey");
				map.put("box5", "grey");
				map.put("box6", "grey");
				map.put("box9", "grey");

			}

			if (boxStatus.equals("CI Box")) {
				map.put("box1", "green");
				map.put("box2", "green");
				map.put("box3", "green");
				map.put("box4", "yellow");
				map.put("box5", "grey");
				map.put("box6", "grey");
				map.put("box9", "grey");

			}
			if (boxStatus.equals("Test Box")) {
				map.put("box1", "green");
				map.put("box2", "green");
				map.put("box3", "green");
				map.put("box4", "green");
				map.put("box5", "yellow");
				map.put("box6", "grey");
				map.put("box9", "grey");

			}
			if (boxStatus.equals("Pre Prod")) {
				map.put("box1", "green");
				map.put("box2", "green");
				map.put("box3", "green");
				map.put("box4", "green");
				map.put("box5", "green");
				map.put("box6", "yellow");
				map.put("box9", "grey");
			}
			if (boxStatus.equals("Production")) {
				map.put("box1", "green");
				map.put("box2", "green");
				map.put("box3", "green");
				map.put("box4", "green");
				map.put("box5", "green");
				map.put("box6", "green");
				map.put("box9", "yellow");

			}

			Map<String, String> subMap = new HashMap<>();
			if (subBoxStatus.equals("") || subBoxStatus.equals("Test Script Submitted")) {
				subMap.put("box7", "yellow");
				subMap.put("box8", "grey");

			}
			if (subBoxStatus.equals("Test Script Approved")) {
				subMap.put("box7", "green");
				subMap.put("box8", "yellow");
			}
			if (subBoxStatus.equals("Done")) {
				subMap.put("box7", "green");
				subMap.put("box8", "green");
			}
			model.addAttribute("map", map);
			model.addAttribute("subMap", subMap);

		}
		model.addAttribute("name", "Monika Goyal");
		model.addAttribute("subHeader", "Eli Lilly");

		return "control";
	}

	private String getBox(String status) {
		// TODO Auto-generated method stub
		String box = "";
		switch (status.toUpperCase()) {
		case "BACKLOG":
			box = "User Story Grooming";
			break;
		case "STORY DEFINITION":
			box = "User Story Grooming";
			break;
		case "IN DEVELOPMENT":
			box = "Dev Box";
			break;
		case "RELEASE TO TEST":
			box = "CI Box";
			break;
		case "IN TEST":
			box = "Test Box";
			break;
		case "PRE PROD":
			box = "Pre Prod";
			break;
		case "PRODUCTION":
			box = "Production";
			break;
		case "USER STORY APPROVED":
			box = "User Story Approved";
			break;

		default:
			break;
		}
		return box;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(ModelMap model) {
		model.addAttribute("subHeader", "Experience and Explore!!");
		return "index";
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(ModelMap model) {
		model.addAttribute("subHeader", "Experience and Explore!!");
		return "about";
	}
}
