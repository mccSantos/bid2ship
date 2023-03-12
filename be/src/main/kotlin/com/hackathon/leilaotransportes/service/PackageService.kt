package com.hackathon.leilaotransportes.data
import com.hackathon.leilaotransportes.data.*
import org.springframework.stereotype.Service
import java.nio.file.attribute.GroupPrincipal
import java.security.InvalidParameterException
import java.security.Principal

@Service
class PackageService(
    public val packages: PackageRepository,
    val clients: ClientRepository, val carriers: CarrierRepository
) {
    fun createPackage(pack: PackageCreateDTO): Package{
        if (pack.destiny.isEmpty())
            throw InvalidParameterException("Destination address is empty")
        if (pack.origin.isEmpty())
            throw InvalidParameterException("Origin address is empty")
        val client = clients.findById(pack.ownerId).get()
        val p = Package(0,pack.origin,pack.destiny,pack.weight,pack.height,pack.width,pack.length,pack.maxDate,pack.nPackages,StateEnum.Open,null,client,emptySet())
        packages.save(p)
        return p
    }

    fun updateShipment(packId: Long, update: PackageUpdateDTO){
        if (!packages.existsById(packId))
            throw Exception(String.format("No such package with packageId: %d found", packId))
        val pack: Package = packages.findById(packId).get()
        if (pack.status.equals(StateEnum.Close))
            throw Exception("Can't update closed package!")

        pack.status = update.status

        packages.save(pack)
    }
    fun deletePackage(packId: Long) {
        if (!packages.existsById(packId))
            throw Exception(String.format("No such package with packId: %d found", packId))
        packages.deleteById(packId)
    }
    fun getPackage(packId: Long): Package {
        if (!packages.existsById(packId))
            throw Exception(String.format("No such package with packId: %d found", packId))
        return packages.findById(packId).get()
    }

    fun getAllUserPackages(id: Long): MutableIterable<Package> = packages.getPackageByClient(id)

    fun getAllPackages(): MutableIterable<Package> {
        return packages.findAll()
    }
}