package web.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/api/users/*", "/api/messages/**", "/upload").hasRole("USER")
            .antMatchers(HttpMethod.GET, "/api/**").permitAll()
            .antMatchers(HttpMethod.POST, "/api/register", "/api/auctions/search/*").permitAll()
            .antMatchers("/api/**").hasRole("USER")
            .and().formLogin()
            .loginPage("/login")
            .failureUrl("/login?error")
            .and()
            .logout()
            .logoutSuccessUrl("/login?logout")
            .deleteCookies("JSESSIONID")
            .and()
            .rememberMe().key("ultraUberUniqueAndSecret")
            .tokenValiditySeconds(86400)
    ;
  }

  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
    auth
            .setUserDetailsService(myUserDetailsService);
    auth.setPasswordEncoder(myUserDetailsService.getEncoder());
    return auth;
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider());
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
