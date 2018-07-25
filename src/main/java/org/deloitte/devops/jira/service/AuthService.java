package org.deloitte.devops.jira.service;

import org.deloitte.devops.config.EnvironmentConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	@Autowired
	private EnvironmentConfig env;
	
	public boolean checkApplicationAccess(String userName, String password) {
		// TODO Auto-generated method stub
		
		if(null!=userName && userName.equalsIgnoreCase(env.getAppUserName()) && null !=password && password.equalsIgnoreCase(env.getAppPassword()))
			return true;
		else
			return false;
	
	}

}
