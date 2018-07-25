package org.deloitte.devops.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="app")
public class EnvironmentConfig implements ApplicationEnvironment {

	private String jiraURL;
	private String jiraUserName;
	private String jiraPassword;

	private String appUserName;
	private String appPassword;


	private String jenkinsUrl;
	private String jenkinsUserName;
	private String jenkinsPassword;

	@Override
	public String getJiraURL() {
		return jiraURL;
	}
	@Override
	public String getJiraUserName() {
		return jiraUserName;
	}
	@Override
	public String getJiraPassword() {
		return jiraPassword;
	}
	@Override
	public String getAppUserName() {
		return appUserName;
	}
	@Override
	public String getAppPassword() {
		return appPassword;
	}
	@Override
	public String getJenkinsUrl() {
		return jenkinsUrl;
	}
	@Override
	public String getJenkinsUserName() {
		return jenkinsUserName;
	}
	@Override
	public String getJenkinsPassword() {
		return jenkinsPassword;
	}

	public void setJiraURL(String jiraURL) {
		this.jiraURL = jiraURL;
	}
	public void setJiraUserName(String jiraUserName) {
		this.jiraUserName = jiraUserName;
	}
	public void setJiraPassword(String jiraPassword) {
		this.jiraPassword = jiraPassword;
	}
	public void setAppUserName(String appUserName) {
		this.appUserName = appUserName;
	}
	public void setAppPassword(String appPassword) {
		this.appPassword = appPassword;
	}
	public void setJenkinsUrl(String jenkinsUrl) {
		this.jenkinsUrl = jenkinsUrl;
	}
	public void setJenkinsUserName(String jenkinsUserName) {
		this.jenkinsUserName = jenkinsUserName;
	}
	public void setJenkinsPassword(String jenkinsPassword) {
		this.jenkinsPassword = jenkinsPassword;
	}



}
