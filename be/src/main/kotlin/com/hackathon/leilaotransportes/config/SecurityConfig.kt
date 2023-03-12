package com.hackathon.leilaotransportes.config

import com.hackathon.leilaotransportes.data.CarrierRepository
import com.hackathon.leilaotransportes.data.ClientRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableWebSecurity(debug=true)
class SecurityConfig(private val carrierRepository: CarrierRepository) {



    @Bean
    fun filterChain(http: HttpSecurity,
                    authenticationManager: AuthenticationManager,
                    userRepository: ClientRepository
    ): SecurityFilterChain? {
        http.cors().and().csrf().disable().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
            .antMatchers("/admin/**").permitAll()
            .antMatchers("/client/login").permitAll()
            .antMatchers("/carrier/login").permitAll()
            .antMatchers("/signup").permitAll()
            .anyRequest().authenticated()
            .and()
            .httpBasic().and()
            .addFilterBefore(
                CarrierPasswordAuthenticationFilterToJWT("/carrier/login", authenticationManager,carrierRepository),
                BasicAuthenticationFilter::class.java)
            .addFilterBefore(
                ClientPasswordAuthenticationFilterToJWT("/client/login", authenticationManager, userRepository),
                BasicAuthenticationFilter::class.java)
            .addFilterBefore(
                UserPasswordSignUpFilterToJWT("/signup", userRepository),
                BasicAuthenticationFilter::class.java
            )
            .addFilterBefore(
                JWTAuthenticationFilter(carrierRepository, userRepository),
                BasicAuthenticationFilter::class.java)
        return http.build()
    }
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOriginPatterns = listOf("*")
        configuration.allowedMethods = listOf("GET", "POST")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    @Bean
    fun encoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationManager(authConfiguration: AuthenticationConfiguration): AuthenticationManager? {
        return authConfiguration.authenticationManager
    }





}