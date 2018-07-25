package org.deloitte.devops.helper;

import java.util.Base64;
import java.util.Map;

import org.deloitte.devops.config.ApplicationEnvironment;
import org.deloitte.devops.config.EnvironmentConfig;
import org.deloitte.devops.repository.DevopsRepository;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class JenkinsJiraHelper {
	private DevopsRepository repository;
	private ApplicationEnvironment env;

	public JenkinsJiraHelper(DevopsRepository repository, EnvironmentConfig env) {
		this.repository = repository;
		this.env = env;
	}

	public String getFromJenkins(Map<String, Object> uriVariables, String... urlComponents) {
		String authHeader = new String(
				Base64.getEncoder().encode((env.getJenkinsUserName() + ":" + env.getJenkinsPassword()).getBytes()));
		StringBuilder sb = new StringBuilder();
		sb.append(env.getJenkinsUrl());
		for (String s : urlComponents) {
			sb.append(s);
		}
		return repository.get(sb.toString(), String.class, uriVariables, authHeader);
	}

	public <T> T exchangeWithJira(HttpMethod method, Map<String, Object> uriVariables, Object requestBody,
			Class<T> type,
			String... urlComponents) {
		StringBuilder sb = new StringBuilder();
		sb.append(env.getJiraURL());
		for (String s : urlComponents) {
			sb.append(s);
		}
		String cred = env.getJiraUserName() + ":" + env.getJiraPassword();
		String authHeader = Base64.getEncoder().encodeToString(cred.getBytes());

		if (HttpMethod.GET.equals(method)) {
			return repository.get(sb.toString(), type, uriVariables, authHeader);
		} else if (HttpMethod.POST.equals(method)) {
			return repository.post(sb.toString(), type, requestBody, authHeader);
		}
		return null;
	}

}
