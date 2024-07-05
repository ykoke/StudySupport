package com.example.demo.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  // パスワードの暗号化
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  InMemoryUserDetailsManager userDetailsService() {
    // ユーザー設定
    UserDetails user = User
        .withUsername("user")
        .password(passwordEncoder().encode("user"))
        .roles("USER")
        .build();
    return new InMemoryUserDetailsManager(user);
  }

  @Bean
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
  }

}