package parking.jpa.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import parking.jpa.entity.MemberJpaEntity

@Repository
interface MemberJpaRepository : JpaRepository<MemberJpaEntity, Long>, MemberJpaRepositoryCustom

interface MemberJpaRepositoryCustom{

}