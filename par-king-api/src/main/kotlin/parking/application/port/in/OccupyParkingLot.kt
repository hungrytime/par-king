package parking.application.port.`in`

interface OccupyParkingLot {
    fun occupyParkingLot(userId: String, parkingLotId: Long)
}