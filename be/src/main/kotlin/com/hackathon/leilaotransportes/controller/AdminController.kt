package com.hackathon.leilaotransportes.controller

import com.hackathon.leilaotransportes.data.Carrier
import com.hackathon.leilaotransportes.data.CarrierDTO
import com.hackathon.leilaotransportes.data.CreateCarrierDTO
import com.hackathon.leilaotransportes.service.CarrierService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/admin")
class AdminController (val serv: CarrierService){
    @PostMapping("")
    fun createCarrier(carrier : CreateCarrierDTO): CarrierDTO {

        return serv.addCarrier(carrier)
    }
    @DeleteMapping("/{id}")
    fun deleteCarrier(@PathVariable id : Long){
        serv.deleteCarrier(id)
    }
}