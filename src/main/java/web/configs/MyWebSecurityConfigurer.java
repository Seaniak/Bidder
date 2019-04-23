package web.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class MyWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

  @Autowired
  MyUserDetailsService myUserDetailsService;

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
            .userDetailsService(myUserDetailsService)
            .passwordEncoder(myUserDetailsService.getEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/api/users/*", "/upload").hasRole("USER")
            .antMatchers(HttpMethod.GET, "/api/**").permitAll()
            .antMatchers(HttpMethod.POST, "/api/register").permitAll()
            .antMatchers("/api/**").hasRole("USER")
            .and().formLogin()
            .loginPage("/login")
            .failureUrl("/login?error")
            .and()
            .logout()
            .logoutSuccessUrl("/login?logout")
            .deleteCookies("JSESSIONID")
            .and()
            .rememberMe().key("uniqueAndSecret")
            .tokenValiditySeconds(86400)
    ;
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/assets/**");
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
            .withUser("user")
            .password(myUserDetailsService.getEncoder().encode("password"))
            .roles("USER");
  }
}
