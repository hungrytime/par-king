package parking.adapter.out

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import parking.application.port.out.FindParkingLotPort
import parking.domain.entity.ParkingLot
import parking.jpa.repositories.ParkingLotJpaRepository

@Component
class ParkingLotInquiryAdapter(
    private val parkingLotJpaRepository: ParkingLotJpaRepository
) : FindParkingLotPort {
    override fun findParkingLot(parkingLotId: Long): ParkingLot? {
        val parkingLot = parkingLotJpaRepository.findById(parkingLotId)

        if(!parkingLot.isPresent) return null

        return parkingLot.get().to()
    }

    override fun findParkingLotByCity(city: String, pageable: Pageable): Page<ParkingLot> {
        return parkingLotJpaRepository.findByCity(city, pageable).map { it.to() }
    }
}