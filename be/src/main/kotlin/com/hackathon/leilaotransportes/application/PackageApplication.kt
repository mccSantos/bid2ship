package com.hackathon.leilaotransportes.application


import com.hackathon.leilaotransportes.data.PackageCreateDTO
import com.hackathon.leilaotransportes.data.PackageDTO
import com.hackathon.leilaotransportes.data.PackageService
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ControllerAdvice
import java.security.Principal

@ControllerAdvice
@Service
class PackageApplication(private val packageService: PackageService) {

    fun addPackage(pack: PackageCreateDTO): PackageDTO {
        return packageService.createPackage(pack).toDTO()
    }

    fun getPackage(id:Long): PackageDTO{
        return packageService.getPackage(id).toDTO();
    }

}