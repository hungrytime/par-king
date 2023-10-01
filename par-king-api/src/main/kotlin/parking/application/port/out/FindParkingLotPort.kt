package parking.application.port.out

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import parking.domain.entity.ParkingLot

interface FindParkingLotPort {
    fun findParkingLot(parkingLotId: Long): ParkingLot?
    fun findParkingLotByCity(city: String, pageable: Pageable): Page<ParkingLot>
}