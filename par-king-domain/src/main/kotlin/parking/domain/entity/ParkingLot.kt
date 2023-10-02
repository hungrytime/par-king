package parking.domain.entity

import parking.domain.exception.ParkingLotException
import parking.domain.exception.enum.ResultCode

data class ParkingLot(
    val parkingLotId: Long? = null,
    val name: String,
    val city: String,
    val totalSpace: Long,
    var availableSpace: Long = 0L
) {
    fun selfValidCheck(name: String, city: String, totalSpace: Long): Boolean {
        if (this.name != name || this.city != city
            || this.totalSpace != totalSpace) return false

        return true
    }

    fun occupy() {
        if (this.availableSpace < 1) throw ParkingLotException(
            ResultCode.PARKING_LOT_ALREADY_FULL,
            message = "이용할 수 있는 주차 공간이 없습니다."
        )

        this.availableSpace--
    }

    fun release() {
        if (this.availableSpace >= this.totalSpace) throw ParkingLotException(
            ResultCode.PARKING_LOT_CAN_NOT_RELEASE,
            message = "이미 주차 공간이 모두 이용가능하여, [${this.name}]의 찜 해제할 수 없습니다."
        )

        this.availableSpace++
    }

    companion object {
        fun toParkingLot(name: String, city: String, totalSpace: Long, availableSpace: Long?) = ParkingLot(
            name = name, city = city, totalSpace = totalSpace, availableSpace = availableSpace ?: 0L
        )
    }
}