package org.deloitte.devops.jira.model;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestInvoker {
	private static final Logger LOG = LoggerFactory.getLogger(RestInvoker.class);
	public static void main(String[] args) throws IOException {
		ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
				.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false)
				.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
		File json = new File("C:\\Users\\arnamondal\\Desktop\\file.json");
		AllIssuesResponse res = om.readValue(json, AllIssuesResponse.class);

		System.out.println(res);
	}

	static HttpHeaders getHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic YW5raXRzYXhlbmE3QGRlbG9pdHRlLmNvbTpBbWF6ZUAxMjM0");
		headers.add("Content-Type", "application/json");
		headers.add("Accept", "application/json");
		return headers;
	}
}
