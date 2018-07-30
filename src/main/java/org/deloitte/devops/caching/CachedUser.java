package org.deloitte.devops.caching;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.deloitte.devops.jira.model.AllBoards;
import org.deloitte.devops.jira.model.AllSprints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CachedUser {
	private static final Logger LOG = LoggerFactory.getLogger(CachedUser.class);
	private CachedUser(String userId) {
		this.userId = userId;
	}

	private static final List<CachedUser> CACHED_USERS = new ArrayList<>();

	private String userId;
	private AllBoards allBoards;
	private AllSprints allSprints;

	private static CachedUser user;

	public static CachedUser createUser() {
		String userId = RandomStringUtils.randomAlphanumeric(16);
		LOG.info("User created with id - [{}]", userId);
		user = new CachedUser(userId);
		CACHED_USERS.add(user);
		return user;
	}

	public static CachedUser getUser(String userId) {
		return CACHED_USERS.stream().filter(cu -> cu.userId.equals(userId)).findFirst().get();
	}

	public CachedUser withBoards(AllBoards allBoards) {
		if (this.allBoards == null) {
			this.allBoards = allBoards;
		} else {
			this.allBoards.getBoards().addAll(allBoards.getBoards());
		}
		return this;
	}

	public CachedUser withSprints(AllSprints allSprints) {
		if (this.allSprints == null) {
			this.allSprints = allSprints;
		} else {
			this.allSprints.getSprints().addAll(allSprints.getSprints());
		}
		return this;
	}

	public String getUserId() {
		return userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		CachedUser other = (CachedUser) obj;
		if (userId == null) {
			if (other.userId != null) {
				return false;
			}
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		return true;
	}

}
