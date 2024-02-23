package u5w3d5.finalproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import u5w3d5.finalproject.security.JWTFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class Config {
  @Autowired
  private JWTFilter jwtFilter;

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.formLogin(AbstractHttpConfigurer::disable);
    httpSecurity.csrf(AbstractHttpConfigurer::disable);
    httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Non vogliamo le sessioni
    httpSecurity.cors(Customizer.withDefaults());

    httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/**").permitAll());

    return httpSecurity.build();
  }

  @Bean
  PasswordEncoder getBCrypt() {
    return new BCryptPasswordEncoder(11);
  }
}
