package parking.jpa.entity

import parking.domain.entity.ParkingLot
import javax.persistence.*

@Entity
@Table(name = "parking_lot")
class ParkingLotJpaEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val parkingLotId: Long? = null,

    val totalSpace: Long,
    val availableSpace: Long,
    val name: String,
    val city: String
) : BaseEntity() {
    fun to() = ParkingLot(
        parkingLotId = this.parkingLotId,
        totalSpace = this.totalSpace,
        availableSpace = this.availableSpace,
        name = this.name,
        city = this.city
    )
}