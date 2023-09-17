package parking.jpa.repositories

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import parking.jpa.entity.ParkingLotJpaEntity
import parking.jpa.entity.QParkingLotJpaEntity.parkingLotJpaEntity

class ParkingLotRepositoryImpl: QuerydslRepositorySupport(ParkingLotJpaEntity::class.java), ParkingLotJpaRepositoryCustom {
    override fun findByCity(city: String, pageable: Pageable): Page<ParkingLotJpaEntity> {
        return from(parkingLotJpaEntity)
            .where(parkingLotJpaEntity.city.eq(city))
            .fetchResults()
            .let {
                PageImpl(it.results, pageable, it.total)
            }
    }
}