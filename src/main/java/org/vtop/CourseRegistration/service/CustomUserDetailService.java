package org.vtop.CourseRegistration.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailService implements UserDetailsService
{	
	private static final Logger logger = LogManager.getLogger(CustomUserDetailService.class);
			
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{	
		String[] userDetail = new String[] {};
		UserBuilder builder = null;
		
		if ((username != null) && (!username.equals("")))
		{
			userDetail = username.split("\\|");
		}
		logger.trace("\n userDetail: "+ userDetail);
		
	    if (userDetail.length > 0)
	    {
	    	 builder = org.springframework.security.core.userdetails.User.withUsername(userDetail[0]);
	         builder.password(userDetail[1]);
	         builder.accountExpired(((Integer.parseInt(userDetail[2]) == 0) ? false : true));
	         builder.authorities(AuthorityUtils.NO_AUTHORITIES);
	         builder.roles("Student");
	    }
	    else
	    {
	    	throw new UsernameNotFoundException(" Invalid credentials. ");
	    }
	    
	    return builder.build();
	}
}
