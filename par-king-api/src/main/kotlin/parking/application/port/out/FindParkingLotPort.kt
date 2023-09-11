package parking.application.port.out

import parking.domain.ParkingLot

interface FindParkingLotPort {
    fun findParkingLot(parkingLotId: Long): ParkingLot
}