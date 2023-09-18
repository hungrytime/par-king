package parking.application.port.`in`

interface ModifyMemberUseCase {
    fun modifyMember(userId: String, userName: String): Boolean
}