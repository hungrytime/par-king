package parking.application.service

import org.springframework.stereotype.Service
import parking.application.port.`in`.SignUpUseCase
import parking.application.port.out.SaveMemberPort
import parking.domain.Member

@Service
class MemberCommandService(
    private val saveMemberPort: SaveMemberPort
) : SignUpUseCase {
    override fun signUp(userId: String, password: String, userName: String): Boolean {
        val member = saveMemberPort.saveMember(Member.toMember(userId, password, userName))
        return saveCheck(member, userId, password, userName)
    }

    private fun saveCheck(member: Member, userId: String, password: String, userName: String): Boolean {
        if(member.userId != userId || member.password != password || member.userName != userName) return false
        return true
    }
}