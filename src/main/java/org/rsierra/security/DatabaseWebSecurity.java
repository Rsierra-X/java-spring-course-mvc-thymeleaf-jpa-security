package org.rsierra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity {
    @Bean
    public UserDetailsManager usersCustom(DataSource dataSource) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.setUsersByUsernameQuery("select username, password, status from user where username=?");
        users.setAuthoritiesByUsernameQuery("select u.username, p.profile from userprofile up " +
                "inner join user u on u.id = up.idUser "	+
                "inner join profile p on p.id = up.idProfile  " + "where u.username = ?");
        return users;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authorize -> authorize

                // Los recursos estáticos no requieren autenticación
                .requestMatchers("/bootstrap/**", "/images/**", "/tinymce/**", "/logos/**").permitAll()

                // Las vistas públicas no requieren autenticación
                .requestMatchers("/","/login", "/signup", "/search","/bcrypt/**", "/vacancy/view/**").permitAll()

                // Asignar permisos a URLs por ROLES
                .requestMatchers("/request/create/**", "/request/save/**").hasAuthority("USUARIO")
                .requestMatchers("/vacancy/**").hasAnyAuthority("SUPERVISOR", "ADMINISTRADOR")
                .requestMatchers("/request/**").hasAnyAuthority("SUPERVISOR", "ADMINISTRADOR")
                .requestMatchers("/categories/**").hasAnyAuthority("SUPERVISOR", "ADMINISTRADOR")
                .requestMatchers("/users/**").hasAnyAuthority("ADMINISTRADOR")


                // Todas las demás URLs de la Aplicación requieren autenticación
                .anyRequest().authenticated());

        // El formulario de Login no requiere autenticacion
        http.formLogin(form -> form.loginPage("/login").permitAll());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
