package com.hackathon.leilaotransportes.service


import com.hackathon.leilaotransportes.data.*
import com.hackathon.leilaotransportes.data.PackageRepository
import java.time.LocalDate

class BidService(public val packages: PackageRepository,
                 val clients: ClientRepository,
                 val carriers: CarrierRepository,
                 val bids: BidRepository
)
{
    fun addBid(bid: AddBidDTO):Bid{
        val pack = packages.findById(bid.packId).get()
        if(pack.status.equals(StateEnum.Close))
            throw Exception("Já fechou!")
        val carrier = carriers.findById(bid.carrierId).get()

        val b = Bid(0,pack, carrier , LocalDate.now(),bid.price,bid.estimatedDate )
        bids.save(b)
        return b
    }
    fun deleteBid(id:Long){
        val bid = bids.findById(id).get()
        if(!bid.pack.status.equals(StateEnum.Open))
            throw Exception("package Closed or Accepted")
    }
}