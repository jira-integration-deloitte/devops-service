package org.deloitte.devops.repository;

import java.util.Collections;
import java.util.Map;

import org.deloitte.devops.jira.constants.ApplicationConstants;
import org.deloitte.devops.jira.model.DevopsServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Repository
public class DevposServiceRepository implements DevopsRepository, ApplicationConstants {
	private static final Logger LOG = LoggerFactory.getLogger(DevposServiceRepository.class);

	private RestTemplate template;
	public DevposServiceRepository(RestTemplate template) {
		this.template = template;
	}

	@Override
	public <T> T get(String url, Class<T> responseType, Map<String, Object> uriVariables, String authHeader) {
		HttpHeaders headers = getHttpHeaders();

		if (!StringUtils.isEmpty(authHeader)) {
			headers.add(HEADER_AUTHORIZATION, BASIC_AUTH + authHeader);
		}

		HttpEntity<?> entity = new HttpEntity<>(headers);

		if (uriVariables == null) {
			uriVariables = Collections.emptyMap();
		}

		ResponseEntity<T> response = template.exchange(url, HttpMethod.GET, entity, responseType, uriVariables);

		return getBody(response);
	}

	@Override
	public <T, E> T post(String url, Class<T> responseType, E requestBody, String authHeader) {
		HttpHeaders headers = getHttpHeaders();

		if (!StringUtils.isEmpty(authHeader)) {
			headers.add(HEADER_AUTHORIZATION, BASIC_AUTH + authHeader);
		}

		HttpEntity<?> entity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<T> response = template.exchange(url, HttpMethod.POST, entity, responseType, Collections.emptyMap());
		
		return getBody(response);
	}


	private <T> T getBody(ResponseEntity<T> response) {
		LOG.info("Response returned with status - [{}]", response.getStatusCodeValue());

		if (!HttpStatus.OK.equals(response.getStatusCode())) {
			LOG.error("Request has failed with status [{}]", response.getStatusCode().value());
			throw new DevopsServiceException("Request has failed with status " + response.getStatusCode().value());
		}

		return response.getBody();
	}

}
