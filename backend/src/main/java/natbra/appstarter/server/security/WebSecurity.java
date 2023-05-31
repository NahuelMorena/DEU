package natbra.appstarter.server.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
	
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurity {

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailService) 
    throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(userDetailService)
            .passwordEncoder(bCryptPasswordEncoder)
            .and()
            .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
    	http.csrf().disable();
    	
    	http.authorizeRequests().antMatchers(
    			"/login", 
    			"/actuator/**", 
    			"/css/**", 
    			"/js/**", 
    			"/img/**", 
    			"/lib/**", 
    			"/favicon.ico",
    			"/")
    		.permitAll();
    	
    	http.authorizeRequests().anyRequest().authenticated();
    	
    	http.exceptionHandling()
        	.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
    		
    	http.formLogin()
	    	.usernameParameter("username")
	    	.passwordParameter("password")
	    	.successHandler(new CustomAuthenticationSuccessHandler())
	    	.failureHandler(new CustomAuthenticationFailureHandler());
    		
    	http.logout()
    		.logoutSuccessUrl("/login")
    		.logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
    		    httpServletResponse.setStatus(HttpStatus.OK.value());
    		})
    		.invalidateHttpSession(true);
        
    	http.cors();
    	
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
