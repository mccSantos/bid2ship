package com.hackathon.leilaotransportes.data


import com.hackathon.leilaotransportes.data.Package
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.LocalDate
import java.util.*
import javax.transaction.Transactional

@Component
@Profile("!test")
class DataSeeder(
    val clients: ClientRepository,
    val carriers: CarrierRepository,
    val bids: BidRepository,
    val admins: AdminRepository,
    val packages: PackageRepository,
) : CommandLineRunner {

    @Transactional
    override fun run(vararg args: String?) {
        addClients()
        //addCarriers()
        //addAdmin()
        //addBids()
        //addPackages()

    }

    // add clients
    fun addClients() {
        clients.deleteAll()
        clients.saveAll(
            listOf(
                Client(0, "Melo", "fm.melo@campus.fct.unl.pt",
                    "password", true, mutableListOf()),
                Client(0, "Tozé", "tonecas@cam.pt",
                    "password", true, mutableListOf()),
                Client(0, "VIP", "vip@vip.pt",
                    "password", false, mutableListOf())
            )
        )
    }

    // add workers (managers, drivers and hubWorkers)
    fun addCarriers() {

        carriers.deleteAll()

        carriers.saveAll(
            listOf(
                Carrier(0, "dhl@dhl.com", "password",
                    "DHL", "data:image/png;base64,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",
                    mutableListOf()
                ),
                Carrier(0, "mrw@mrw.com", "password",
                    "MRW", "https://707.pt/wp-content/uploads/2018/01/mrw.png", mutableListOf()
                )
            )
        )

    }

    // add trucks
    fun addAdmin() {
        admins.deleteAll()

        admins.save(Admin(0,"admin@admin.pt", "password"))
    }

    // add hubs
    fun addBids() {
        bids.deleteAll()
        val c1 = Carrier(0, "dhl@dhl.com", "password",
            "DHL", "data:image/png;base64,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",
            mutableListOf()
        )
        val c2 = Carrier(0, "mrw@mrw.com", "password",
            "MRW", "https://707.pt/wp-content/uploads/2018/01/mrw.png", mutableListOf()
        )

        val u1 = Client(0,"yo","yoyo","password", true, mutableListOf())
        val u2 = Client(0,"yo2","yoyo2","password", false, mutableListOf())

        val p1 = com.hackathon.leilaotransportes.data.Package(
            0,
            "lx",
            "porto",
            2.0f,
            10.0f,
            10.0f,
            10.0f,
            Date.from(Instant.now()),
            1,StateEnum.Open, c1,u1, emptySet())

        bids.saveAll(
            listOf(
                Bid(0,p1,c1, LocalDate.now(), 10.0f,Date.from(Instant.now())),
                Bid(0,p1,c2, LocalDate.now(), 12.0f,Date.from(Instant.now()))
            )
        )
    }
}