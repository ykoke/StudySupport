package com.example.demo.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  // パスワードの暗号化
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsServiceImpl userDetailsService() {
    return userDetailsService;
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider(UserDetailsServiceImpl userDetailsService) {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
  }

  @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

  @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .formLogin(login -> login
                .defaultSuccessUrl("/user")
                .permitAll())
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/").permitAll()
                .requestMatchers("/top", "/user", "/taskmanagement/**", "/subjectreview").hasAnyRole("USER")
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                .anyRequest().authenticated());
        return http.build();
    }

  /* @Bean
  InMemoryUserDetailsManager userDetailsService() {
    // ユーザー設定
    UserDetails user = User
        .withUsername("user")
        .password(passwordEncoder().encode("12345"))
        .roles("USER")
        .build();
    return new InMemoryUserDetailsManager(user);
  } */

  /* @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
        // ログインページの許可設定
        .formLogin(login -> login // フォーム認証を使う
            .defaultSuccessUrl("/user") // 認証成功時のデフォルトの遷移先
            .permitAll())

        // リクエストの許可設定
        .authorizeHttpRequests(authz -> authz
            // index.html の参照権限
            .requestMatchers("/")
            .permitAll()
            // top.htmlのの参照権限
            .requestMatchers("/top")
            .hasAnyRole("USER")
            // user.html の参照権限
            .requestMatchers("/user")
            .hasAnyRole("USER")
            // taskmanagement.htmlの参照権限
            .requestMatchers("/taskmanagement")
            .hasAnyRole("USER")
            .requestMatchers("/taskmanagement/form")
            .hasAnyRole("USER")
            .requestMatchers("/taskmanagement/form/insert")
            .hasAnyRole("USER")
            // subjectreview.htmlの参照権限
            .requestMatchers("/subjectreview")
            .hasAnyRole("USER"));
    return http.build();
  } */

}