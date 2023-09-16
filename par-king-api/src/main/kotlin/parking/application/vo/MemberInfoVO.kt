package parking.application.vo

import parking.domain.entity.Member
import parking.domain.entity.MemberStatus

data class MemberInfoVO(
    val userId: String,
    val userName: String,
    val memberStatus: MemberStatus,
    val createdAt: String
) {
    companion object {
        fun from(member: Member) = MemberInfoVO(
            userId = member.userId,
            userName = member.userName,
            memberStatus = member.memberStatus,
            createdAt = member.createdDate!!
        )
    }
}
