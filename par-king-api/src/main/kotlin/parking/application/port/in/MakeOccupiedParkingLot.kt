package parking.application.port.`in`

interface MakeOccupiedParkingLot {
    fun makeOccupied(userId: String, parkingLotId: Long)
}