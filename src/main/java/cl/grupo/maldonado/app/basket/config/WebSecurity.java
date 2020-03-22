package cl.grupo.maldonado.app.basket.config;

import cl.grupo.maldonado.app.basket.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService service;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and().csrf().disable().authorizeRequests()
                .antMatchers( "/**").permitAll();
                /**.antMatchers( "/api/v1/**").permitAll()
                .antMatchers( "/configuration/*").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
                .antMatchers(HttpMethod.GET, "/v2/api-docs").permitAll()
                .antMatchers(HttpMethod.GET, "/webjars/**").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()

                .anyRequest().authenticated()
                .and()
                //.addFilter(new JWTAuthenticationFilter(authenticationManager()))
                //.addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                 **/
    }
}