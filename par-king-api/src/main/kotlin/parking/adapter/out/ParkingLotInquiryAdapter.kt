package parking.adapter.out

import org.springframework.stereotype.Component
import parking.application.port.out.FindParkingLotPort
import parking.domain.entity.ParkingLot
import parking.jpa.repositories.ParkingLotJpaRepository

@Component
class ParkingLotInquiryAdapter(
    private val parkingLotJpaRepository: ParkingLotJpaRepository
) : FindParkingLotPort {

    override fun findParkingLot(parkingLotId: Long): ParkingLot {
        return parkingLotJpaRepository.findById(parkingLotId).get().to()
    }
}