package parking.adapter.out

import org.springframework.stereotype.Component
import parking.application.port.out.SaveMemberPort
import parking.domain.entity.Member
import parking.jpa.entity.MemberJpaEntity
import parking.jpa.repositories.MemberJpaRepository

@Component
class MemberCommandAdapter(
    private val memberJapRepository: MemberJpaRepository
) : SaveMemberPort {
    override fun saveMember(member: Member): Member {
        return memberJapRepository.save(MemberJpaEntity.from(member)).to()
    }
}