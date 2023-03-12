package com.hackathon.leilaotransportes.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.hackathon.leilaotransportes.data.*
import com.hackathon.leilaotransportes.service.MyUserDetailsService
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.web.filter.GenericFilterBean
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

object JWTSecret {
    private const val passphrase = "p54xoyMev6MH3jJuf85HjuXMB401tUqV9PoF048u"
    val KEY: String = Base64.getEncoder().encodeToString(passphrase.toByteArray())
    const val SUBJECT = "JWT Tiago Time Attendance"
    val VALIDITY = minutesToMillis(20); // 20 minutes in milliseconds
}

fun minutesToMillis(minutes: Long): Long {
    return 1000 * 60 * minutes;
}

private fun addResponseTokenClient(authentication: Authentication, response: HttpServletResponse, user: Client) {

    val claims = HashMap<String, Any?>()
    claims["email"] = authentication.name
    claims["name"] = user.name
    claims["id"] = user.id
    claims["type"] = "client"
    val token = Jwts
        .builder()
        .setClaims(claims)
        .setSubject(JWTSecret.SUBJECT)
        .setIssuedAt(Date(System.currentTimeMillis()))
        .setExpiration(Date(System.currentTimeMillis() + JWTSecret.VALIDITY))
        .signWith(SignatureAlgorithm.HS256, JWTSecret.KEY)
        .compact()

    response.addHeader("Authorization", "Bearer $token")
}

private fun addResponseTokenCarrier(authentication: Authentication, response: HttpServletResponse, user: Carrier) {

    val claims = HashMap<String, Any?>()
    claims["email"] = authentication.name
    claims["name"] = user.name
    claims["id"] = user.id
    claims["type"] = "carrier"
    val token = Jwts
        .builder()
        .setClaims(claims)
        .setSubject(JWTSecret.SUBJECT)
        .setIssuedAt(Date(System.currentTimeMillis()))
        .setExpiration(Date(System.currentTimeMillis() + JWTSecret.VALIDITY))
        .signWith(SignatureAlgorithm.HS256, JWTSecret.KEY)
        .compact()

    response.addHeader("Authorization", "Bearer $token")
}

class ClientPasswordAuthenticationFilterToJWT(
    defaultFilterProcessesUrl: String?,
    private val anAuthenticationManager: AuthenticationManager,
    private val userRepository: ClientRepository,
) : AbstractAuthenticationProcessingFilter(defaultFilterProcessesUrl) {

    override fun attemptAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?
    ): Authentication? {

        //getting user from request body
        val user = ObjectMapper().readValue(request!!.inputStream, LoginDTO::class.java)

        // perform the "normal" authentication
        val auth = anAuthenticationManager.authenticate(UsernamePasswordAuthenticationToken(user.email, user.password))

        return if (auth.isAuthenticated) {
            // Proceed with an authenticated user
            SecurityContextHolder.getContext().authentication = auth
            auth
        } else
            null
    }

    fun successfulAuthenticationClient(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain?,
        auth: Authentication
    ) {

        // When returning from the Filter loop, add the token to the response
        val user = userRepository.findByEmail(auth.name)
        user ?: return
        response.setContentType("application/json")
        response.writer.println(ObjectMapper().writeValueAsString(LoginDTO(user.email, user.password)))
        addResponseTokenClient(auth, response, user)


    }


}

class CarrierPasswordAuthenticationFilterToJWT(
    defaultFilterProcessesUrl: String?,
    private val anAuthenticationManager: AuthenticationManager,
    private val userRepository: CarrierRepository
) : AbstractAuthenticationProcessingFilter(defaultFilterProcessesUrl) {

    override fun attemptAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?
    ): Authentication? {

        //getting user from request body
        val user = ObjectMapper().readValue(request!!.inputStream, Client::class.java)

        // perform the "normal" authentication
        val auth = anAuthenticationManager.authenticate(UsernamePasswordAuthenticationToken(user.email, user.password))

        return if (auth.isAuthenticated) {
            // Proceed with an authenticated user
            SecurityContextHolder.getContext().authentication = auth
            auth
        } else
            null
    }

    fun successfulAuthenticationClient(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain?,
        auth: Authentication
    ) {

        // When returning from the Filter loop, add the token to the response
        val user = userRepository.findByEmail(auth.name)
        user ?: return
        response.setContentType("application/json")
        response.writer.println(ObjectMapper().writeValueAsString(LoginDTO(user.email, user.password)))
        addResponseTokenCarrier(auth, response, user)


    }

}


class UserAuthToken(
    private val login: String,
    private val authorities: List<GrantedAuthority>,
) : Authentication {

    override fun getAuthorities() = authorities

    override fun setAuthenticated(isAuthenticated: Boolean) {}

    override fun getName() = login

    override fun getCredentials() = null

    override fun getPrincipal() = UsernamePasswordAuthenticationToken("a","a")

    override fun isAuthenticated() = true

    override fun getDetails() = UsernamePasswordAuthenticationToken("a","a")
}

class JWTAuthenticationFilter(val carrierRepo: CarrierRepository, val userRepository: ClientRepository) :
    GenericFilterBean() {


    override fun doFilter(
        request: ServletRequest?,
        response: ServletResponse?,
        chain: FilterChain?
    ) {

        val authHeader = (request as HttpServletRequest).getHeader("Authorization")

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            val token = authHeader.substring(7)
            try {
                val claims = Jwts.parser().setSigningKey(JWTSecret.KEY).parseClaimsJws(token).body
                val exp = (claims["exp"] as Int).toLong()
                if (exp < System.currentTimeMillis() / 1000)
                    (response as HttpServletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED)
                else {
                    val type = (claims["type"] as String)
                    if (type.equals("client")) {
                        val user = userRepository.findByEmail(claims["username"] as String) ?: throw TODO()
                        val authentication = UserAuthToken(
                            claims["username"] as String,
                            emptyList()
                        )

                        SecurityContextHolder.getContext().authentication = authentication
                        val userDAO = userRepository.findByEmail(user.email) ?: throw TODO()
                        // Renew token with extended time here. (before doFilter)
                        // TODO validar quando damos renew, porque no kiosk nao devemos querer, mas no bo queremos
                        addResponseTokenClient(authentication, response as HttpServletResponse, userDAO)

                        chain!!.doFilter(request, response)
                    } else {
                        val user = carrierRepo.findByEmail(claims["username"] as String) ?: throw TODO()
                        val authentication = UserAuthToken(
                            claims["username"] as String,
                            emptyList()
                        )

                        SecurityContextHolder.getContext().authentication = authentication
                        val userDAO = carrierRepo.findByEmail(user.email) ?: throw TODO()
                        // Renew token with extended time here. (before doFilter)
                        // TODO validar quando damos renew, porque no kiosk nao devemos querer, mas no bo queremos
                        addResponseTokenCarrier(authentication, response as HttpServletResponse, userDAO)

                        chain!!.doFilter(request, response)
                    }

                }
            } catch (expired: ExpiredJwtException) {
                (response as HttpServletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED)
            }

        } else {
            chain!!.doFilter(request, response)
        }
    }
}

class UserPasswordSignUpFilterToJWT(
    defaultFilterProcessesUrl: String?,
    private val users: ClientRepository
) : AbstractAuthenticationProcessingFilter(defaultFilterProcessesUrl) {

    override fun attemptAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?
    ): Authentication? {
        //getting user from request body
        val user = ObjectMapper().readValue(request!!.inputStream, CreateUserDTO::class.java)
        print("here")
        return users.save(Client(0, user.name, user.email, user.password, user.particular, mutableListOf()))
            .let {
                val auth = UserAuthToken(user.email, emptyList())
                print("here1")
                SecurityContextHolder.getContext().authentication = auth
                    auth
            }
    }
    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain?,
        auth: Authentication
    ) {

        addResponseTokenClient(auth, response, users.findAll().first())
    }
}