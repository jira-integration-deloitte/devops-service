package org.deloitte.devops.controller;

import org.deloitte.devops.config.ApplicationEnvironment;
import org.deloitte.devops.helper.JenkinsJiraHelper;
import org.deloitte.devops.jira.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AuthController {
	private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

	private AuthService authService;

	public AuthController(ApplicationEnvironment env, AuthService authService, JenkinsJiraHelper helper) {
		this.authService = authService;
	}

	@GetMapping("/auth")
	public String authUser() {


		return "";
	}

	@GetMapping("/login")
	public ModelAndView login(ModelMap model) {
		ModelAndView mav = new ModelAndView("login");
		mav.getModelMap().addAttribute("subHeader", "PROJECT LEAD / PROJECT MANAGER LOGIN");
		return mav;
	}

	@GetMapping("/team")
	public ModelAndView team() {
		return new ModelAndView("team");
	}

	@PostMapping("/doLogin")
	public ModelAndView doLogin(@RequestParam(value = "userName") String userName,
			@RequestParam(value = "password") String password, ModelMap model) {
		LOG.info("Logging in the user - [{}]", userName);
		ModelAndView mav = new ModelAndView();
		boolean validUser = authService.checkApplicationAccess(userName, password);
		String viewName = "";
		if (validUser) {
			model.addAttribute("name", "Monika Goyal");
			model.addAttribute("subHeader", "My Projects");
			viewName = "welcome";
		} else {
			model.addAttribute("subHeader", "Access Denied");
			viewName = "error";
		}
		mav.setViewName(viewName);
		return mav;
	}

}
