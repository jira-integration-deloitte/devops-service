package org.deloitte.devops.jira.model;

/**
 * Custom Exception class for the application. Extends {@link RuntimeException}
 * because don't really want to force the developer to write code for catching
 * the exception and also it will make it easy to have a better exception
 * handling.
 * 
 * @author arnamondal
 *
 */
public class DevopsServiceException extends RuntimeException {
	private static final long serialVersionUID = -12898945849121L;

	public DevopsServiceException() {
		super();
	}

	public DevopsServiceException(Throwable cause) {
		super(cause);
	}

	public DevopsServiceException(String message) {
		super(message);
	}

	public DevopsServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
