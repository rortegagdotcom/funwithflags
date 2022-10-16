package com.rortegag.funwithflags.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration
{
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private DataSource dataSource;
	
	private final String USERS_QUERY = "select email, password, active from users where email = ?";
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.usersByUsernameQuery(USERS_QUERY)
		.authoritiesByUsernameQuery(USERS_QUERY)
		.dataSource(dataSource)
		.passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {      
        http.authorizeRequests()
    		.antMatchers(HttpMethod.GET, 
    				"/legal_notice", 
    				"/privacy_policy", 
    				"/cookies_policy")
    		.permitAll();
        
        http.authorizeRequests()
			.antMatchers(HttpMethod.GET, 
					"/country_flags/", 
					"/country_flags/africa_flags/**", 
					"/country_flags/asia_flags/**", 
					"/country_flags/centralamerica_flags/**", 
					"/country_flags/europe_flags/**", 
					"/country_flags/northamerica_flags/**", 
					"/country_flags/oceania_flags/**", 
					"/country_flags/southamerica_flags/**")
			.permitAll();
        
        http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/login").permitAll()
			.antMatchers("/signup").permitAll()
			.antMatchers("/home/**")
			.authenticated().and().csrf().disable()
			.formLogin().loginPage("/login").failureUrl("/login?error=true")
			.defaultSuccessUrl("/home")
			.usernameParameter("email")
			.passwordParameter("password")
			.and().logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/")
			.and().rememberMe()
			.tokenRepository(persistentTokenRepository())
			.tokenValiditySeconds(60*60)
			.and().exceptionHandling().accessDeniedPage("/access_denied");
        
        return http.build();
    }
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizier() throws Exception {
		return (web) -> web. ignoring().antMatchers("/img/**", "/js/**", "/css/**", "/webjars/**");
	}
	
	@Bean
	protected PersistentTokenRepository persistentTokenRepository() 
	{
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource);
		
		return db;
	}
}
