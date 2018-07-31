package org.deloitte.devops.jira.model;

import java.io.Serializable;

public class Board implements Serializable, Comparable<Board> {

	private static final long serialVersionUID = 1L;

	private String id;
	private String self;
	private String name;
	private String type;
	private Location location;

	public String getId() {
		return id;
	}

	public String getSelf() {
		return self;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public Location getLocation() {
		return location;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public int compareTo(Board o) {
		if (o == null) {
			return 1;
		}
		if (location == null || location.getName() == null) {
			if (location == o.location || location.getName() == o.location.getName()) {
				return 0;
			} else {
				return -1;
			}
		}

		return location.getName().compareTo(o.location.getName());
	}

}
