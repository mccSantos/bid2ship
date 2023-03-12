package com.hackathon.leilaotransportes.controller

import com.hackathon.leilaotransportes.data.AddBidDTO
import com.hackathon.leilaotransportes.data.BidDTO
import com.hackathon.leilaotransportes.data.Carrier
import com.hackathon.leilaotransportes.service.BidService
import io.swagger.v3.oas.annotations.parameters.RequestBody
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/api/bids")
class BidController(val service: BidService) {
    @PostMapping("")
    fun addBid(@RequestBody bid : AddBidDTO): BidDTO {
        return service.addBid(bid).toDTO()
    }
    @DeleteMapping("/{id}")
    fun deleteBid(@PathVariable id : Long){
        return service.deleteBid(id);
    }
}