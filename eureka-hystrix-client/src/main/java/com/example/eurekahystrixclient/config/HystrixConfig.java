package com.example.eurekahystrixclient.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@EnableWebSecurity
public class HystrixConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("22013330"));

        String userSQL = "select username,password,valid from t_customer " +
                "where username = ?";
        String authoritySQL = "select c.username,a.authority from t_customer c,t_authority a," +
                "t_customer_authority ca where ca.customer_id=c.id " +
                "and ca.authority_id=a.id and c.username =?";
        auth.jdbcAuthentication().passwordEncoder(encoder)
                .dataSource(dataSource)
                .usersByUsernameQuery(userSQL)
                .authoritiesByUsernameQuery(authoritySQL);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/detail/common/**").hasRole("common")
                .antMatchers("/detail/vip/**").hasRole("vip")
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/userLogin").permitAll()
                .usernameParameter("name").passwordParameter("pwd")
                .defaultSuccessUrl("/")
                .failureUrl("/userLogin?error");
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate(RestTemplateBuilder builder){
//        return builder.build();
//    }
}






