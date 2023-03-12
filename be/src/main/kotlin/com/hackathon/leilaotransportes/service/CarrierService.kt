package com.hackathon.leilaotransportes.service

import com.hackathon.leilaotransportes.data.*
import java.util.*

class CarrierService(val carriers: CarrierRepository,  val packages : PackageRepository) {
    fun addCarrier(carrier: CreateCarrierDTO): CarrierDTO {
        if (carrier.email.isEmpty() || carrier.password.isEmpty() || carrier.name.isEmpty())
            throw Exception("missing information.")

        val c = Carrier(0, carrier.email, carrier.password, carrier.name, carrier.logo, mutableListOf())
        carriers.save(c)
        return c.toDTO()
    }

    fun deleteCarrier(id: Long){
        carriers.deleteById(id);
    }

    fun getAllPackages(): MutableIterable<Package>{
        return packages.findAll()
    }

    fun getAllCarriers(): MutableIterable<Carrier>{
        return getAllCarriers()
    }
}