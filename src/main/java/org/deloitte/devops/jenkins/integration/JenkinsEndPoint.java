package org.deloitte.devops.jenkins.integration;

public interface JenkinsEndPoint {

	
	public static final String JENKIS_BUILD_STATUS_API_URL = "/lastBuild/api/json";
	public static final String ISSUE_STATUS_IN_RELEASE_TO_TEST="Release to Test";
	public static final String SUCCESS_BUILD_STATUS="SUCCESS";
	public static final String FAILURE_BUILD_STATUS="FAILURE";
	public static final String ISSUES_IN_DEV="In Development";
}
