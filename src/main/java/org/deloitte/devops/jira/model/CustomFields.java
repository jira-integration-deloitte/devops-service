package org.deloitte.devops.jira.model;

import java.io.Serializable;

public class CustomFields implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String key;
	private String name;
	private boolean custom;
	private boolean navigable;
	private boolean ordarable;
	private boolean searchable;
	private String[] clauseNames;
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
	public String[] getClauseNames() {
		return clauseNames;
	}
	public void setClauseNames(String[] clauseNames) {
		this.clauseNames = clauseNames;
	}
	public Schema getSchema() {
		return schema;
	}
	public void setSchema(Schema schema) {
		this.schema = schema;
	}

}
