package parking.jpa.repositories

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import parking.jpa.entity.MemberJpaEntity
import parking.jpa.entity.QMemberJpaEntity.memberJpaEntity


class MemberJpaRepositoryImpl : QuerydslRepositorySupport(MemberJpaEntity::class.java), MemberJpaRepositoryCustom {
    override fun findMemberByUserId(userId: String): MemberJpaEntity {
        return from(memberJpaEntity)
            .where(memberJpaEntity.userId.eq(userId))
            .fetchOne()
    }
}