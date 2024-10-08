package reema.com.config;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootTest
public class AccessConfigTest {

    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    public void testUserDetailsService() {
        assertThat(userDetailsService).isInstanceOf(InMemoryUserDetailsManager.class);
        assertThat(userDetailsService.loadUserByUsername("user")).isNotNull();
        assertThat(userDetailsService.loadUserByUsername("admin")).isNotNull();
    }
}