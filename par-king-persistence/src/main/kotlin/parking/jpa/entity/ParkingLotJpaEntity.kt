package parking.jpa.entity

import parking.domain.entity.ParkingLot
import javax.persistence.*

@Entity
@Table(name = "parking_lot")
class ParkingLotJpaEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val parkingLotId: Long? = null,
    val name: String,
    val city: String,
    val totalSpace: Long,
    val availableSpace: Long

) : BaseEntity() {
    fun to() = ParkingLot(
        parkingLotId = this.parkingLotId,
        totalSpace = this.totalSpace,
        availableSpace = this.availableSpace,
        name = this.name,
        city = this.city
    )

    companion object {
        fun from(parkingLot: ParkingLot) = ParkingLotJpaEntity(
            name = parkingLot.name,
            city = parkingLot.city,
            totalSpace = parkingLot.totalSpace,
            availableSpace = parkingLot.availableSpace
        )
    }
}