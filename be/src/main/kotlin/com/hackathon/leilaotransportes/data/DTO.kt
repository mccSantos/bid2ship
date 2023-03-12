package com.hackathon.leilaotransportes.data


import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.persistence.*

data class PackageCreateDTO(
    val ownerId: Long,
    val destiny: String,
    val origin: String,
    val maxDate: Date,
    val weight: Float,
    val width: Float,
    val height: Float,
    val length: Float,
    val nPackages: Int
)

data class PackageDTO(
    val id: Long,
    val destiny: String,
    val origin: String,
    val maxDate: Date,
    val weight: Float,
    val width: Float,
    val height: Float,
    val length: Float,
    val nPackages: Int
)

data class PackageUpdateDTO(
    val packId: Long,
   val status: StateEnum
)

data class CreateUserDTO(
     val email:String,
     val password: String,
     val name: String,
     val particular: Boolean
){
    constructor() : this("a","a","a",true)
}

data class CreateCarrierDTO(
    val email: String,
    val password: String,
    val name: String,
    val logo: String?,
)

data class AddBidDTO(
    val packId:Long,
    val price: Float,
    val estimatedDate: Date,
    val carrierId: Long,
)

data class LoginDTO(
    val email: String,
    val password: String
){
    constructor() : this("a","a")
}

data class BidDTO(
    val packId:Long,
    val price: Float,
    val estimatedDate: Date,
    val carrierId: Long,
)

data class CarrierDTO(
    val id : Long,
    val email: String,
    val password: String,
    val name: String,
    val logo: String?,
)