package org.deloitte.devops.profile;

import java.util.Arrays;
import java.util.List;

import org.deloitte.devops.login.model.LoginData;
import org.deloitte.devops.profile.JiraProfileBuilder.JiraProfile;

public interface JiraUsers {

	JiraProfile USER_ANKIT = new JiraProfileBuilder()
							.email("ankitsaxena7@deloitte.com")
							.firstName("Ankit")
							.lastName("Saxena")
							.jiraUrl("https://unitedsiteservices.atlassian.net")
							.jiraPassword("Amaze@1234")
							.userName("ankit1")
							.password("12345")
							.storyPointFieldName("customfield_10073")
							.storyPointFieldTitle("Total Story Point")
							.build();
	
	
	List<JiraProfile> jiraUsers = Arrays.asList(new JiraProfile[] { USER_ANKIT });

	default JiraProfile search(LoginData data) {
		JiraProfile toSearch = new JiraProfile(data);
		
		return jiraUsers.contains(toSearch) ? jiraUsers.get(jiraUsers.indexOf(toSearch)) : null;
	}
}
