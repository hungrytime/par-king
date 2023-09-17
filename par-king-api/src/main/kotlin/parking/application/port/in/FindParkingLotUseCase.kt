package parking.application.port.`in`

import parking.application.vo.ParkingLotInfoVO

interface FindParkingLotUseCase {
    fun findParkingLotById(parkingLotId: Long): ParkingLotInfoVO
}