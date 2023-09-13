package parking.application.port.`in`

import parking.application.vo.MemberInfoVO

interface FindMemberUseCase {
    fun userIdByMemberId(memberId: Long): String

    fun userInfoByUserId(userId: String): MemberInfoVO
}