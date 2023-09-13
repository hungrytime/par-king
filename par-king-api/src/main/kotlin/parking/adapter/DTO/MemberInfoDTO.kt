package parking.adapter.DTO

import parking.application.vo.MemberInfoVO

data class MemberInfoDTO(
    val userId: String,
    val userName: String,
    val memberStatus: String,
    val createdAt: String
) {
    companion object {
        fun from(member: MemberInfoVO) = MemberInfoDTO(
            userId = member.userId,
            userName = member.userName,
            memberStatus = member.memberStatus.toString(),
            createdAt = member.createdAt
        )
    }
}