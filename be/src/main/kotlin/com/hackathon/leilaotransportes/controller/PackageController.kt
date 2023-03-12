package com.hackathon.leilaotransportes.controller

import com.hackathon.leilaotransportes.api.PackageAPI
import com.hackathon.leilaotransportes.application.PackageApplication
import com.hackathon.leilaotransportes.data.PackageCreateDTO
import com.hackathon.leilaotransportes.data.PackageDTO
import com.hackathon.leilaotransportes.data.PackageService
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class PackageController(val app: PackageApplication , val service: PackageService) : PackageAPI{
    override fun createPackage(pack: PackageCreateDTO): PackageDTO {
        return app.addPackage(pack)
    }

    override fun getPackage(packId: Long): PackageDTO {
        return app.getPackage(packId)
    }

    override fun getAllPackages(email: String): Collection<PackageDTO> {
        return service.getAllPackages().map { it.toDTO() }
    }

    override fun deletePackage(packId: Long) {
        service.deletePackage(packId)
    }

}