package utez.edu.mx.unidad3.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class MainSecurity {
    @Bean
    public SecurityFilterChain doFilterInternal(HttpSecurity http) throws Exception{
        http.csrf(c-> c.disable()).cors(c-> c.configurationSource(corsRegistry())).authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/client/**").hasRole("ADMIN")
                .requestMatchers("/api/cede/**").hasRole("EMPLOYEE")
                .requestMatchers(
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/v3/api-docs.yaml",
                        "/swagger-resources/**",
                        "/webjars/**"
                ).permitAll()
                .anyRequest().authenticated()
        ).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    private CorsConfigurationSource corsRegistry(){
        //Se genera toda la configuracion del CORS
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("GET","POST","DELETE","OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(false);//cookies

        //Se especifica a que rutas queremos aplicar dicha configuracion
        UrlBasedCorsConfigurationSource sourse = new UrlBasedCorsConfigurationSource();
        sourse.registerCorsConfiguration("/**",configuration);
        return sourse;
    }

    @Bean
    public UserDetailsService generateAdmin(){
        UserDetails admin = User.builder()
                .username("root")
                .password(passwordEncoder().encode("root"))
                .roles("ADMIN")
                .build();
        UserDetails employee = User.builder()
                .username("cliente")
                .password(passwordEncoder().encode("root"))
                .roles("EMPLOYEE")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }





    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
