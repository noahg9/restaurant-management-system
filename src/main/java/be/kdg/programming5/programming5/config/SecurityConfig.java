package be.kdg.programming5.programming5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
import static org.springframework.security.web.util.matcher.RegexRequestMatcher.regexMatcher;

/**
 * The type Security config.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    /**
     * Filter chain security filter chain.
     *
     * @param http the http
     * @return the security filter chain
     * @throws Exception the exception
     */
    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auths -> auths.requestMatchers(regexMatcher("^/(menu-items|chefs|search-chefs|search-menu-items|history|switch-language|error|registration)")).permitAll().requestMatchers(antMatcher(HttpMethod.GET, "/api/**")).permitAll().requestMatchers(antMatcher(HttpMethod.GET, "/js/**"), antMatcher(HttpMethod.GET, "/css/**"), antMatcher(HttpMethod.GET, "/images/**"), antMatcher(HttpMethod.GET, "/webjars/**"), regexMatcher(HttpMethod.GET, "\\.ico$")).permitAll().requestMatchers(antMatcher(HttpMethod.GET, "/")).permitAll().anyRequest().authenticated());
        http.formLogin(formLogin -> formLogin.loginPage("/login").permitAll());
        http.exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint((request, response, exception) -> {
            if (request.getRequestURI().startsWith("/api")) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            } else {
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }));
        return http.build();
    }

    /**
     * Password encoder b crypt password encoder.
     *
     * @return the b crypt password encoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
