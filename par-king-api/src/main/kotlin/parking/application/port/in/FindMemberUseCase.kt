package parking.application.port.`in`

interface FindMemberUseCase {
    fun userIdByMemberId(memberId: Long) : String
}