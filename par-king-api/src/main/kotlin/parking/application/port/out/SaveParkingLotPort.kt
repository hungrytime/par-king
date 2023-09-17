package parking.application.port.out

import parking.domain.entity.ParkingLot

interface SaveParkingLotPort {
    fun saveParkingLot(parkingLot: ParkingLot): ParkingLot
}