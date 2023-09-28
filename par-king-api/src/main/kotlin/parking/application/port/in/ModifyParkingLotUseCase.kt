package parking.application.port.`in`

interface ModifyParkingLotUseCase {
    fun modifyParkingLot(name: String, city: String, totalSpace: Long, availableSpace: Long): Boolean
}