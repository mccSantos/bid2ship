package com.hackathon.leilaotransportes.service


import com.hackathon.leilaotransportes.data.*
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*


@Service
class MyUserDetailsService(val clientRepository: ClientRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = clientRepository.findByEmail(username) ?: throw UsernameNotFoundException(username)
        return MyUserPrincipal(user)
    }
    fun addUser(user : CreateUserDTO): Optional<MyUserPrincipal> {
        val client : Client = clientRepository.save(Client(0, user.name, user.email, user.password, user.particular, mutableListOf()))
        return Optional.of(MyUserPrincipal(client))
    }
}

@Service
class MyUserDetailsServiceCarrier(val carrierRepository: CarrierRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val carrier = carrierRepository.findByEmail(username) ?: throw UsernameNotFoundException(username)
        return MyCarrierPrincipal(carrier)
    }
    fun addCarrier(user : CreateCarrierDTO): Optional<MyCarrierPrincipal> {
        val carrier : Carrier = carrierRepository.save(Carrier(0, user.email,user.password,user.name,user.logo,
            mutableListOf()
        ))
        return Optional.of(MyCarrierPrincipal(carrier))
    }
}