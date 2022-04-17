package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.repository.SemesterMasterRepository;



@Service
@Transactional
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private SemesterMasterRepository semesterMasterRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//List<GrantedAuthority> authorities= new ArrayList<>();
		//authorities.add(new SimpleGrantedAuthority("USER"));
		
		List<Object[]> user=semesterMasterRepository.findStudentDetailByRegisterNumber2(username);
		
		UserBuilder builder = null;
	    if (!user.isEmpty()) {
	    	 builder = org.springframework.security.core.userdetails.User.withUsername(String.valueOf(user.get(0)[0]));
	         builder.password(String.valueOf(user.get(0)[18]));
	         builder.accountExpired(((Integer.parseInt(String.valueOf(user.get(0)[20])) == 0) ? false : true));
	         builder.authorities(AuthorityUtils.NO_AUTHORITIES);  //AuthorityUtils.NO_AUTHORITIES
	         builder.roles("Student");
	    }
	    else
	    {
	    	throw new UsernameNotFoundException(" Invalid credentials. ");
	    }
	    
	    return builder.build();
	}
}
