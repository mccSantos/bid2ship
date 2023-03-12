package com.hackathon.leilaotransportes.api

import com.hackathon.leilaotransportes.data.PackageCreateDTO
import com.hackathon.leilaotransportes.data.PackageDTO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RequestMapping("/api/packages")
interface PackageAPI {
    @Operation
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "added package", content=[Content()]),
        ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
    ])
    @PostMapping("")
    fun createPackage(@RequestBody pack: PackageCreateDTO) : PackageDTO

    @Operation(summary = "Get one package with given id")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Found the package", content = [
            (Content(mediaType = "application/json", schema = Schema(implementation = PackageDTO::class)))]),
        ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
        ApiResponse(responseCode = "404", description = "Did not find the sought package", content = [Content()])]
    )
    @GetMapping("{packId}")
    fun getPackage(@PathVariable packId:Long): PackageDTO

    @Operation(summary = "Get all packages")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Found all packages", content = [
            (Content(mediaType = "application/json", array = (ArraySchema(schema = Schema(implementation = PackageDTO::class)))))]),
        ApiResponse(responseCode = "400", description = "Bad request", content = [Content()])]
    )
    @GetMapping("client/{email}")
    fun getAllPackages(@PathVariable email:String) : Collection<PackageDTO>

    @Operation(summary = "Delete the package with given id")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Found and deleted the package", content = [Content()]),
        ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
        ApiResponse(responseCode = "404", description = "Did not find the sought package", content = [Content()])]
    )
    @DeleteMapping("{packId}")
    fun deletePackage(@PathVariable packId: Long)

}