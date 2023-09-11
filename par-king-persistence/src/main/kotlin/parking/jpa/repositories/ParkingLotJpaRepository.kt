package parking.jpa.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import parking.jpa.entity.ParkingLotJpaEntity

@Repository
interface ParkingLotJpaRepository : JpaRepository<ParkingLotJpaEntity, Long>, ParkingLotJpaRepositoryCustom

interface ParkingLotJpaRepositoryCustom {

}