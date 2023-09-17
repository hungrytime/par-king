package parking.application.vo

import parking.domain.entity.ParkingLot

data class ParkingLotInfoVO(
    val name: String,
    val city: String,
    val totalSpace: Long,
    val availableSpace: Long
) {
    companion object {
        fun from(parkingLot: ParkingLot) = ParkingLotInfoVO(
            name = parkingLot.name,
            city = parkingLot.city,
            totalSpace = parkingLot.totalSpace,
            availableSpace = parkingLot.availableSpace
        )
    }
}
