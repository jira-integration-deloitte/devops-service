package org.deloitte.devops.jira.model;

import java.io.Serializable;
import java.util.List;

public class CustomField implements Serializable {
	private static final long serialVersionUID = 1L;

	public CustomField() {
	}

	public CustomField(String name) {
		this.name = name;
	}

	private String id;
	private String key;
	private String name;
	private boolean custom;
	private boolean navigable;
	private boolean ordarable;
	private boolean searchable;
	private List<String> clauseNames;
	private Schema schema;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCustom() {
		return custom;
	}

	public void setCustom(boolean custom) {
		this.custom = custom;
	}

	public boolean isNavigable() {
		return navigable;
	}

	public void setNavigable(boolean navigable) {
		this.navigable = navigable;
	}

	public boolean isOrdarable() {
		return ordarable;
	}

	public void setOrdarable(boolean ordarable) {
		this.ordarable = ordarable;
	}

	public boolean isSearchable() {
		return searchable;
	}

	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}

	public List<String> getClauseNames() {
		return clauseNames;
	}

	public void setClauseNames(List<String> clauseNames) {
		this.clauseNames = clauseNames;
	}

	public Schema getSchema() {
		return schema;
	}

	public void setSchema(Schema schema) {
		this.schema = schema;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CustomField other = (CustomField) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

}
