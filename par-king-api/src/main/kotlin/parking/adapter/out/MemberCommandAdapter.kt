package parking.adapter.out

import org.springframework.stereotype.Component
import parking.application.port.out.SaveMemberPort
import parking.domain.Member
import parking.jpa.repositories.MemberJpaRepository

@Component
class MemberCommandAdapter(
    private val memberJapRepository: MemberJpaRepository
) : SaveMemberPort {
    override fun saveMember(member: Member): Member {
        TODO("Not yet implemented")
    }
}