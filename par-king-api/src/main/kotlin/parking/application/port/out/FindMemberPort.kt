package parking.application.port.out

import parking.domain.entity.Member

interface FindMemberPort {
    fun findMemberById(memberId: Long): Member?
    fun findMemberByUserId(userId: String): Member?
}