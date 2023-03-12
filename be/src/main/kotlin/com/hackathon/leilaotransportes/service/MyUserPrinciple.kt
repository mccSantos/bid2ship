package com.hackathon.leilaotransportes.service

import com.hackathon.leilaotransportes.data.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class MyUserPrincipal(private val user: Client) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val list = mutableListOf<GrantedAuthority>()
        return list
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}

class MyCarrierPrincipal(private val user: Carrier) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val list = mutableListOf<GrantedAuthority>()
        return list
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}