package org.deloitte.devops.profile;

import org.deloitte.devops.login.model.LoginData;
import org.deloitte.devops.profile.JiraProfileBuilder.JiraProfile;

public final class JiraProfileBuilder implements Builder<JiraProfile> {
	private final JiraProfile profile;

	public JiraProfileBuilder() {
		profile = new JiraProfile();
	}

	public JiraProfileBuilder email(String email) {
		profile.email = email;
		return this;
	}

	public JiraProfileBuilder password(String password) {
		profile.password = password;
		return this;
	}

	public JiraProfileBuilder jiraUrl(String jiraUrl) {
		profile.jiraUrl = jiraUrl;
		return this;
	}

	public JiraProfileBuilder storyPointFieldTitle(String storyPointFieldTitle) {
		profile.storyPointFieldTitle = storyPointFieldTitle;
		return this;
	}

	public JiraProfileBuilder storyPointFieldName(String storyPointFieldName) {
		profile.storyPointFieldName = storyPointFieldName;
		return this;
	}

	public JiraProfileBuilder firstName(String firstName) {
		profile.firstName = firstName;
		return this;
	}

	public JiraProfileBuilder lastName(String lastName) {
		profile.lastName = lastName;
		return this;
	}

	public JiraProfileBuilder userName(String userName) {
		profile.userName = userName;
		return this;
	}

	public JiraProfileBuilder jiraPassword(String jiraPassword) {
		profile.jiraPassword = jiraPassword;
		return this;
	}

	@Override
	public JiraProfile build() {
		return profile;
	}

	public static final class JiraProfile {

		public JiraProfile(LoginData data) {
			this.userName = data.getUserName();
			this.password = data.getPassword();
		}

		private String lastName;
		private String firstName;
		private String email;
		private String password;
		private String jiraUrl;
		private String storyPointFieldTitle;
		private String storyPointFieldName;
		private String jiraPassword;
		private String userName;

		private JiraProfile() {
		}

		public String getEmail() {
			return email;
		}

		public String getPassword() {
			return password;
		}

		public String getJiraUrl() {
			return jiraUrl;
		}

		public String getStoryPointFieldTitle() {
			return storyPointFieldTitle;
		}

		public String getStoryPointFieldName() {
			return storyPointFieldName;
		}

		public String getFirstName() {
			return firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public String getJiraPassword() {
			return jiraPassword;
		}

		public String getFullName() {
			return this.firstName + " " + this.lastName;
		}

		public String getUserName() {
			return userName;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((password == null) ? 0 : password.hashCode());
			result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
			JiraProfile other = (JiraProfile) obj;
			if (password == null) {
				if (other.password != null) {
					return false;
				}
			} else if (!password.equals(other.password)) {
				return false;
			}
			if (userName == null) {
				if (other.userName != null) {
					return false;
				}
			} else if (!userName.equals(other.userName)) {
				return false;
			}
			return true;
		}

	}
}
