package org.vtop.CourseRegistration;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.vtop.CourseRegistration.service.CustomUserDetailService;


@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailService();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getProvider());
	}

	@Bean
	public AuthenticationProvider getProvider() {
		CustomAuthenticationProvider provider = new CustomAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		return provider;
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/resources/**").and().ignoring().antMatchers("/assets/**").and().ignoring()
				.antMatchers("/webjars/**").and().ignoring().antMatchers("/favicon.ico**").and().ignoring()
				.antMatchers("/actuator/**");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.authorizeRequests().requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().and()
				.formLogin().loginPage("/").failureUrl("/login/error").defaultSuccessUrl("/login/success").permitAll()
				.and().authorizeRequests().anyRequest().authenticated().and().logout().permitAll()
				.clearAuthentication(true).invalidateHttpSession(true).deleteCookies("JSESSIONID");
	}
}
