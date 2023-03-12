package com.hackathon.leilaotransportes.data
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*
@Repository
interface PackageRepository : CrudRepository<Package, Long> {
    override fun findById(id: Long): Optional<Package>

    override fun findAll(): MutableIterable<Package>

    override fun deleteById(id:Long)
    @Query(value = "select pack from Package pack where pack.owner.id = :userId")
    fun getPackageByClient(userId: Long): MutableIterable<Package>

    @Query(value = "select pack from Package pack where pack.carrier.id = :carrierId")
    fun getBidPackages(carrierId:Long)

    @Query(value = "select pack from Package pack where pack.carrier.id = :carrierId and pack.status = 'accepted'")
    fun getAcceptedPackages(carrierId: Long)
}
@Repository
interface ClientRepository : CrudRepository<Client, Long> {

    override fun findById(id:Long): Optional<Client>
    fun findByEmail(email: String): Client?
}
@Repository
interface CarrierRepository : CrudRepository<Carrier,Long>{
    override fun findById(id:Long): Optional<Carrier>

    fun findByEmail(email:String): Carrier?
}
@Repository
interface AdminRepository : CrudRepository<Admin,Long>{
    override fun findById(id:Long) : Optional<Admin>
}

interface BidRepository : CrudRepository<Bid, Long>{
    override fun findById(id:Long) : Optional<Bid>
}