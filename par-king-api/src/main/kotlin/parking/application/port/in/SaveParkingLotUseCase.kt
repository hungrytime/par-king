package parking.application.port.`in`

interface SaveParkingLotUseCase {
    fun saveParkingLot(name: String, city: String, totalSpace: Long): Boolean
}