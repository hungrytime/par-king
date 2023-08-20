package parking.application.port.out

interface FindMemberPort {
    fun findMemberById(memberId: Long): String
}