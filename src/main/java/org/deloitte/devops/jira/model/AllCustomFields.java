package org.deloitte.devops.jira.model;

import java.io.Serializable;
import java.util.List;

public class AllCustomFields implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<CustomFields> customFields;
	
	public List<CustomFields> getCustomFields() {
		return customFields;
	}
	public void setCustomFields(List<CustomFields> customFields) {
		this.customFields = customFields;
	}
}
