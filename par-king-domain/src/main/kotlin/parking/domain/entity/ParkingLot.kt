package parking.domain.entity

data class ParkingLot(
    val parkingLotId: Long? = null,
    val totalSpace: Long,
    val availableSpace: Long,
    val name: String,
    val city: String
)