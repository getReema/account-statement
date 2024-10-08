package reema.com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class AccessConfig {

	@Value("${admin.password}")
	private String adminPassword;
	
	@Value("${user.password}")
	private String userPassword;
	
	
	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/statement/").hasAnyRole("USER", "ADMIN") 
                .requestMatchers("/api/statement/{id}").hasAnyRole("USER", "ADMIN") 
                .anyRequest().hasRole("ADMIN"))
            .formLogin(form -> form
                .defaultSuccessUrl("/api/statement/", true))
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll())
            .sessionManagement(session -> session
            		.invalidSessionUrl("/login?invalid-session=true")
                    .maximumSessions(1)// Allow only one session per user
                    .maxSessionsPreventsLogin(true)
                    //and().invalidSessionUrl("/login?invalid-session=true")
                   // .invalidSessionUrl())
                   .expiredUrl("/login?invalid-session=true")
                   )
            .httpBasic();

        return http.build();
    }

   @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
            .password("{noop}"+userPassword)
            .roles("USER")
            .build());
        manager.createUser(User.withUsername("admin")
            .password("{noop}"+adminPassword)
            .roles("ADMIN")
            .build());
        return manager;
    }
    
}
