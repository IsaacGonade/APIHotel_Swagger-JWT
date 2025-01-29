package com.example;

import com.example.security.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Configuration
	@EnableWebSecurity
	@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		// lista de rutas a las que se puede acceder sin necesidad de autorizacion
		private static final String[] AUTH_WHITELIST = {
				"/v2/api-docs",
				"/swagger-resources",
				"/swagger-resources/**",
				"/configuration/ui",
				"/configuration/security",
				"/swagger-ui.html",
				"/webjars/**",
				"/v3/api-docs/**",
				"/swagger-ui/**",
				"/doc/**"
		};

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
					.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests()
					// rutas de las funciones que se pueden usar si autorizacion
					.antMatchers(HttpMethod.GET, "/api/hotel/localidad/{localidad}").permitAll()
					.antMatchers(HttpMethod.GET, "/api/hotel/categoria/{categoria}").permitAll()
					.antMatchers(HttpMethod.GET, "/api/habitaciones/buscar").permitAll()
					.antMatchers(HttpMethod.POST, "/api/usuario").permitAll()
					// permite todas las rutas de la lista blanca
					.antMatchers(AUTH_WHITELIST).permitAll()
					// las demas deben ser autenticadas
					.anyRequest().authenticated();
		}
	}
}
