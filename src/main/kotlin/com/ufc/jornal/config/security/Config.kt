package com.ufc.jornal.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@Profile("prod")
class Config {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests {
                it
                    .requestMatchers(
                        HttpMethod.POST.name(), "/admins/**",
                        HttpMethod.POST.name(), "/teachers/**",
                        HttpMethod.PUT.name(), "/teachers/**",
                        HttpMethod.DELETE.name(), "/teachers/**"
                    ).hasRole("ADMIN")
                    .requestMatchers(
                        "/posts/{postId}/comments",
                        "/posts/like"
                    ).authenticated()
                    .requestMatchers(
                        "/auth/**",
                        HttpMethod.GET.name(), "/posts/**",
                        HttpMethod.POST.name(), "/students"
                    ).permitAll()
                    .anyRequest().authenticated()
            }
            .oauth2ResourceServer {
                it.jwt { jwt ->
                    jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())
                }
            }


        return http.build()
    }

    @Bean
    fun jwtAuthenticationConverter(): JwtAuthenticationConverter {
        val jwtConverter = JwtAuthenticationConverter()
        jwtConverter.setJwtGrantedAuthoritiesConverter { jwt ->
            val roles = jwt.getClaimAsStringList("roles")?: emptyList()
            roles.map { role ->
                SimpleGrantedAuthority("ROLE_$role")
            }
        }
        return jwtConverter
    }

}

@Configuration
@EnableWebSecurity
@Profile("dev", "test")
class SecurityConfigDev {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .headers { it.frameOptions { frame -> frame.disable() } }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests {
                it
                    .requestMatchers(
                        HttpMethod.POST.name(), "/admins/**",
                        HttpMethod.POST.name(), "/teachers/**",
                        HttpMethod.PUT.name(), "/teachers/**",
                        HttpMethod.DELETE.name(), "/teachers/**"
                    ).hasRole("ADMIN")
                    .requestMatchers(
                        "/posts/{postId}/comments",
                        "/posts/like"
                    ).authenticated()
                    .requestMatchers(
                        "/auth/**",
                        HttpMethod.GET.name(), "/posts/**",
                        HttpMethod.POST.name(), "/students"
                    ).permitAll()
                    .requestMatchers(
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/h2-console/**"
                    ).permitAll()
                    .anyRequest().authenticated()
            }
            .oauth2ResourceServer {
                it.jwt { jwt ->
                    jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())
                }
            }

        return http.build()
    }

    @Bean
    fun jwtAuthenticationConverter(): JwtAuthenticationConverter {
        val jwtConverter = JwtAuthenticationConverter()
        jwtConverter.setJwtGrantedAuthoritiesConverter { jwt ->
            val role = jwt.getClaimAsString("role")
            listOf(SimpleGrantedAuthority("ROLE_$role"))
        }
        return jwtConverter
    }
}
