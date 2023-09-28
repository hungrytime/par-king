package parking.domain.entity

data class ParkingLot(
    val parkingLotId: Long? = null,
    val name: String,
    val city: String,
    val totalSpace: Long,
    val availableSpace: Long
) {
    fun selfValidCheck(name: String, city: String, totalSpace: Long): Boolean {
        if (this.name != name || this.city != city
            || this.totalSpace != totalSpace) return false

        return true
    }

    companion object {
        fun toParkingLot(name: String, city: String, totalSpace: Long, availableSpace: Long?) = ParkingLot(
            name = name, city = city, totalSpace = totalSpace, availableSpace = availableSpace ?: 0L
        )
    }
}