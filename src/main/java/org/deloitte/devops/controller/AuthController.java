package org.deloitte.devops.controller;

import org.deloitte.devops.login.model.LoginData;
import org.deloitte.devops.profile.JiraProfileBuilder.JiraProfile;
import org.deloitte.devops.profile.JiraUsers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

	@PostMapping(value = "/app/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public JiraProfile login(@RequestBody LoginData data) {
		LOG.info("Logging in with [{}]", data);

		return new JiraUsers() {
		}.search(data);
	}

}
