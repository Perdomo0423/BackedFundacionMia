package com.SGA.Configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.SGA.seguridad.CustomUserDetailsService;
import com.SGA.seguridad.JwtAuthenticationEntryPoint;
import com.SGA.seguridad.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomUserDetailsService userDetailsService;
	@Bean
	PasswordEncoder passwordeEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.csrf().disable() // CSRF: Es un tipo de sploit malicioso de un sitio web. se usa para falsificación de petición de sitios cruzados
		.exceptionHandling()
		.authenticationEntryPoint(jwtAuthenticationEntryPoint)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeHttpRequests().antMatchers(HttpMethod.GET,"/api/**") //cuando se hace una peticion web, esta ruta puede ser para cualquiera
			.permitAll()
			//.antMatchers("/api/auth/**").permitAll()// significa que despues de autenticado tendre todos los permisos
			.antMatchers("/api/auth/**").permitAll()
			.antMatchers("/v2/api-docs/**").permitAll()
			.antMatchers("/api/**").permitAll()
			.antMatchers("/**").permitAll()
			.anyRequest() //de aquí para abajo significa que cualquier otra petición se va a autenticary va ser a ser una httpBasic
			.authenticated() ;
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	
	}
	  @Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers("/v2/api-docs",
	                                   "/configuration/ui",
	                                   "/swagger-resources/**",
	                                   "/configuration/security",
	                                   "/swagger-ui.html",
	                                   "/webjars/**");
	    }
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordeEncoder());
		
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
	}

}
