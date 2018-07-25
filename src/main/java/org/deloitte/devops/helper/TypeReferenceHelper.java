package org.deloitte.devops.helper;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

@Component
public class TypeReferenceHelper {
	public <T> ParameterizedTypeReference<List<T>> getListTypeRefecence(Class<T> ofType) {
		return new ParameterizedTypeReference<List<T>>() {
		};
	}

	public <T> ParameterizedTypeReference<T> getTypeRefecence(Class<T> ofType) {
		return new ParameterizedTypeReference<T>() {
		};
	}
}
