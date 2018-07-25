package org.deloitte.devops.jira.model;

import java.io.Serializable;

public class StatusCategory implements Serializable {

	/**
	 * "self":
	 * "https://unitedsiteservices.atlassian.net/rest/api/2/statuscategory/3", "id":
	 * 3, "key": "done", "colorName": "green", "name": "Done"
	 */
	private static final long serialVersionUID = 7663100106606525410L;

	private String id;
	private String key;
	private String colorName;
	private String name;

	public String getId() {
		return id;
	}

	public String getKey() {
		return key;
	}

	public String getColorName() {
		return colorName;
	}

	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public void setName(String name) {
		this.name = name;
	}

}
