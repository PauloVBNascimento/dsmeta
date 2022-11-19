package br.com.urnawebapi.projeto.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

    public void configure(HttpSecurity httpSec) throws Exception {
     
        httpSec.csrf()  .disable()
                        .authorizeHttpRequests()
                        .antMatchers(HttpMethod.POST, "/eleitores/login").permitAll()
                        .antMatchers(HttpMethod.POST, "/eleitores").permitAll()
                        .antMatchers(HttpMethod.GET, "/eleitores").permitAll()
                        .antMatchers(HttpMethod.GET, "/eleitores/{id}/notificar").permitAll()
                        //.antMatchers(HttpMethod.GET, "/eleitores").permitAll()
                        .anyRequest().authenticated().and().cors();

        httpSec.addFilterBefore(new SecurityFilter(), UsernamePasswordAuthenticationFilter.class);

    }

}
