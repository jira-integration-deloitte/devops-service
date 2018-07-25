package org.deloitte.devops.jenkins.integration;

import java.util.List;

import org.deloitte.devops.jira.model.Issue;
import org.deloitte.devops.jira.model.Transitions;
import org.springframework.stereotype.Component;

@Component
public class JenkinsIntegration {
	//private static final Logger LOG = LoggerFactory.getLogger(JenkinsIntegration.class);
	public void setBuildStatus(List<Issue> issues) {
		/*
		for (Issue issue : issues) {
			if (null != issue.getFields().getJobName() && issue.getFields().getStatus().getName()
					.equals(JenkinsEndPoint.ISSUE_STATUS_IN_RELEASE_TO_TEST)) {

				checkExistingBuildForIssue(issue, issues);
				if (null == issue.getFields().getBuildStatus()) {

					String result = helper.getFromJenkins(null, JENKINS_JOB_URI, issue.getFields().getJobName(),
							JenkinsEndPoint.JENKIS_BUILD_STATUS_API_URL);

					LOG.info("Jenkins status - [{}]", result);
					setBuildStatus(issue, result);
				}

			}

		}
		*/

	}

	public void setBuildStatusForIssue(Issue issue) {/*
		String queryString = JenkinsEndPoint.JENKIS_BUILD_STATUS_API_URL;// /lastBuild/api/json
		String result = null;

		String jobName = null != issue.getFields() && !StringUtils.isEmpty(issue.getFields().getJobName())
				? issue.getFields().getJobName()
				: null;
		String status = null != issue.getFields() && null != issue.getFields().getStatus()
				&& StringUtils.isEmpty(issue.getFields().getStatus().getName())
						? issue.getFields().getStatus().getName()
						: null;

		if (null != jobName && JenkinsEndPoint.ISSUE_STATUS_IN_RELEASE_TO_TEST.equals(status)) {

			try {
				result = helper.getFromJenkins(null, JENKINS_JOB_URI, jobName, queryString);
				setBuildStatus(issue, result);
				LOG.info("Build status was fetched successfully - [{}]", result);
			} catch (Exception e) {
				LOG.error("Unable to fetch build status - [{}]", e.getMessage());
			}

		}
		if (null != jobName && JenkinsEndPoint.ISSUES_IN_DEV.equals(status)) {
			try {
				result = helper.getFromJenkins(null, JENKINS_JOB_URI, jobName, queryString);
				setBuildStatusDev(issue, result);
				LOG.info("Build status was fetched successfully - [{}]", result);
			} catch (Exception e) {
				LOG.error("Unable to fetch build status - [{}]", e.getMessage());
			}
		}

	*/}

	String formulateBody(String transitionId) {
		return "{\"transition\":{\"id\":" + transitionId + "}}";
	}

	 String getTransitionId(List<Transitions> transitionList, Issue issue) {
		for (Transitions transitions : transitionList) {
			if (issue.getFields().getStatus().getName().equals(transitions.getName())) {
				return transitions.getId();
			}
		}
		return null;
	}

	 /*
	private List<Transitions> getTransitions(Issue issue) {
		String result = helper.exchangeWithJira(HttpMethod.GET, null, null, JiraEndPoint.GET_TRANSITIONS, issue.getId(),
				JiraEndPoint.TRANSITION);

		if (!StringUtils.isEmpty(result)) {
			JSONObject responseJson = new JSONObject(result);
			Gson gson = new Gson();

			List<Transitions> list = Arrays
					.asList(gson.fromJson(responseJson.get("transitions").toString(), Transitions[].class));
			return list;
		}

		return null;
	 }
	*/

}