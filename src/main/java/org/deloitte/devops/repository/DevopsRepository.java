package org.deloitte.devops.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public interface DevopsRepository {

	public <T> T get(String url, Class<T> responseType, Map<String, Object> uriVariables, String authHeader);

	public <T, E> T post(String url, Class<T> responseType, E requestBody, String authHeader);

	public default HttpHeaders getHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		List<MediaType> accepts = new ArrayList<>();

		accepts.add(MediaType.APPLICATION_JSON_UTF8);
		headers.setAccept(accepts);
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return headers;
	}


}
