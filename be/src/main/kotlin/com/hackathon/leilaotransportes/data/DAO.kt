package com.hackathon.leilaotransportes.data
import java.time.LocalDate
import java.util.Date
import javax.persistence.*

@Entity
data class Package(
    @Id @GeneratedValue val id: Long,
    val origin: String,
    val destiny: String,
    val weight: Float,
    val height: Float,
    val width: Float,
    val length: Float,
    val maxDate: Date,
    val nPackages: Int,
    var status: StateEnum,
    @OneToOne val carrier: Carrier?,
    @ManyToOne(fetch = FetchType.EAGER) var owner: Client,
    @OneToMany(fetch = FetchType.EAGER) val bids: Set<Bid>,

    ) {

    fun toDTO() = PackageDTO(
        this.id, this.destiny, this.origin, this.maxDate,this.weight, this.width,this.height,this.length,this.nPackages
    )
}


@Entity
data class Client(
    @Id @GeneratedValue val id: Long,
    val name: String,
    val email: String,
    val password: String,
    val isParticular: Boolean,
    @OneToMany(fetch = FetchType.EAGER) var packs: MutableList<Package>,
) {
    fun toDTO() = CreateUserDTO(
        this.email,this.password,this.name,this.isParticular
    )
}

@Entity
data class Bid(
    @Id @GeneratedValue val id: Long,
    @OneToOne val pack: Package,
    @OneToOne val bidder: Carrier,
    val date: LocalDate,
    val price: Float,
    val eta: Date,
) {
    fun toDTO() = BidDTO(
        this.id, this.price,this.eta,this.bidder.id
    )
}

@Entity
data class Carrier(
    @Id @GeneratedValue val id: Long,
    val email: String,
    val password: String,
    val name: String,
    @Column(columnDefinition = "LONGTEXT") val logo: String?,
    @OneToMany val packages: MutableList<Package>,
) {
    fun toDTO()= CarrierDTO(
        this.id, this.email,this.password, this.name, this.logo
    )
}

enum class StateEnum(private val type: String) {
    Accepted("accepted"),
    Open("open"),
    Close("close");
}

@Entity
data class Admin(
    @Id @GeneratedValue val id: Long,
    val email: String,
    val password: String,
)