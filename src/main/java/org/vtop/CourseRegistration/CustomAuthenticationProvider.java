package org.vtop.CourseRegistration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.vtop.CourseRegistration.service.CustomUserDetailService;
import org.vtop.CourseRegistration.service.SemesterMasterService;


public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

	@Autowired private HttpSession session;
	@Autowired private HttpServletResponse response;
	@Autowired private CustomUserDetailService customUserDetailService;
	@Autowired 	HttpServletRequest request;
	@Autowired private SemesterMasterService semesterMasterService;
	

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		
		String userId = authentication.getName().toUpperCase().trim();
		String passwordInput = authentication.getCredentials().toString().trim();
		String ipAddress = request.getRemoteAddr();
		//String captchaInput = request.getParameter("captchaStr").trim();
		//captchaInput=(captchaInput!=null)?captchaInput.toUpperCase():"";
		//captchaInput = "hi";
	
		
		if (userId != null && passwordInput != null) {

			try {

				//if (!verifyCaptchaString("captchaStr", captchaInput)) {
				//{					
				//	throw new BadCredentialsException(" Invalid Captcha ");
				//}
				//else
				//{
					UserDetails userDetails = customUserDetailService.loadUserByUsername(userId);
					
					if (userDetails != null) 
					{	
						// verify password
						int validateLogin = semesterMasterService.getUserLoginValidation(userId, passwordInput, userDetails.getPassword(), 2);
			
						if(validateLogin==1) // password true
							
						{
							//Cookie assignment
							Cookie cookie = new Cookie("RegisterNumber", userId);
							cookie.setSecure(true);
							cookie.setHttpOnly(true);
							cookie.setMaxAge(-1);
							response.addCookie(cookie);
											
							//Session assignment
							session.setMaxInactiveInterval(15 * 60);
							
							//Assigning IP address 
							if (request != null) 
							{
								ipAddress = request.getHeader("X-FORWARDED-FOR");
					            if (ipAddress == null || "".equals(ipAddress)) {
					            	  ipAddress = request.getRemoteAddr();
					            }
					        }
							session.setAttribute("IpAddress", ipAddress);
							
							session.setAttribute("RegisterNumber", userId);
						
							return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
						}
					}
					else
					{
						throw new BadCredentialsException(" Invalid username / password ");
					}
				//}
				
			} catch (Exception x) {
				throw new BadCredentialsException(x.getMessage());
			}
		} else {
			throw new BadCredentialsException(" Invalid username / password ");
		}
		return authentication;

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	/*private boolean verifyCaptchaString(String sessionVarName, String captchaInput) {
		String storedCaptcha = (String) session.getAttribute(sessionVarName);
		if (storedCaptcha.equals(captchaInput)) {
			return true;
		}
		
		return false;
	}*/
}
