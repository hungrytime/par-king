package parking.adapter.out

import org.springframework.stereotype.Component
import parking.application.port.out.SaveParkingLotPort
import parking.domain.entity.ParkingLot
import parking.jpa.entity.ParkingLotJpaEntity
import parking.jpa.repositories.ParkingLotJpaRepository

@Component
class ParkingLotCommandAdapter(
    private val parkingLotJpaRepository: ParkingLotJpaRepository
): SaveParkingLotPort {
    override fun saveParkingLot(parkingLot: ParkingLot): ParkingLot {
        return parkingLotJpaRepository.save(ParkingLotJpaEntity.from(parkingLot)).to()
    }

    override fun modifyParkingLot(parkingLot: ParkingLot): ParkingLot {
        return parkingLotJpaRepository.save(ParkingLotJpaEntity.from(parkingLot)).to()
    }
}