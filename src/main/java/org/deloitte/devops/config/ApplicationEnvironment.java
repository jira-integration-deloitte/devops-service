package org.deloitte.devops.config;

public interface ApplicationEnvironment {
	public String getJiraURL();

	public String getJiraUserName();

	public String getJiraPassword();

	public String getAppUserName();

	public String getAppPassword();

	public String getJenkinsUrl();

	public String getJenkinsUserName();

	public String getJenkinsPassword();

	public String getCustomFields();
}
