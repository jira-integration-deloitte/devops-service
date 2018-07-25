package org.deloitte.devops.jira.integration;

public interface JiraEndPoint {

	String GENERAL_SEARCH = "/rest/api/2/search";
	String allIssues = "jql=project=\"DevOps Portal Project\"&fields=id,key,description,summary,creator,status,issuetype,customfield_10201&startAt=0&maxResults=50";
	String GET_ISSUE = "/rest/api/2/issue/";
	String GET_TRANSITIONS = "/rest/api/2/issue/";
	String TRANSITION = "/transitions";
	String UPDATE_CURRENT_ISSUE_TO_TEST_OR_IN_DEVLOPMENT = "/rest/api/2/issue/";
	String UPDATE_ST_STRING = "/transitions";
	String TRANSITION_QUERY_STRING = "expand=transitions.fields";
	String INTEST = "In Test";
	String TESTING = "Testing";
	String IN_DEVELOPMENT = "In Development";
	String DEVELOPMENT_OF_STORY = "Development of story";
	String ALL_ISSUES = "";
	String ALL_BOARDS = "/rest/agile/1.0/board/";

}
