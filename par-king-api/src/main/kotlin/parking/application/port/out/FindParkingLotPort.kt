package parking.application.port.out

import parking.domain.entity.ParkingLot

interface FindParkingLotPort {
    fun findParkingLot(parkingLotId: Long): ParkingLot
}