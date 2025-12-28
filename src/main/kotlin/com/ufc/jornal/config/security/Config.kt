package com.ufc.jornal.config.security

import com.ufc.jornal.constants.ENPOINTS_DEV
import com.ufc.jornal.constants.ENPOINTS_PROD
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
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
            .authorizeHttpRequests { auth ->
                ENPOINTS_PROD.PUBLIC.forEach {
                    auth.requestMatchers(it.method, it.path).permitAll()
                }

                ENPOINTS_PROD.ADMIN.forEach {
                    auth.requestMatchers(it.method, it.path).hasRole("ADMIN")
                }

                ENPOINTS_PROD.AUTHENTICATED.forEach {
                    auth.requestMatchers(it.method, it.path).authenticated()
                }

                auth.anyRequest().authenticated()
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
            .authorizeHttpRequests { auth ->

                ENPOINTS_DEV.TOOL_KIT.forEach {
                    auth.requestMatchers(it).permitAll()
                }

                ENPOINTS_DEV.PUBLIC.forEach {
                    auth.requestMatchers(it.method, it.path).permitAll()
                }

                ENPOINTS_DEV.ADMIN.forEach {
                    auth.requestMatchers(it.method, it.path).hasRole("ADMIN")
                }

                ENPOINTS_DEV.AUTHENTICATED.forEach {
                    auth.requestMatchers(it.method, it.path).authenticated()
                }

                auth.anyRequest().authenticated()
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
