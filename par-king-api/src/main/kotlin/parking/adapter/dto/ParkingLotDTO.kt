package parking.adapter.dto

import parking.application.vo.ParkingLotInfoVO

data class ParkingLotDTO(
    val name: String,
    val city: String,
    val totalSpace: Long,
    val availableSpace: Long
) {
    companion object {
        fun from(vo: ParkingLotInfoVO) = ParkingLotDTO(
            name = vo.name,
            city = vo.city,
            totalSpace = vo.totalSpace,
            availableSpace = vo.availableSpace
        )
    }
}
