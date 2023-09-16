package parking.adapter.out

import org.springframework.stereotype.Component
import parking.application.port.out.FindMemberPort
import parking.domain.entity.Member
import parking.jpa.repositories.MemberJpaRepository

@Component
class MemberInquiryAdapter(
   private val memberJapRepository: MemberJpaRepository
) : FindMemberPort {

    override fun findMemberById(memberId: Long): Member {
        return memberJapRepository.findAll().first().to()
    }

    override fun findMemberByUserId(userId: String): Member {
        return memberJapRepository.findMemberByUserId(userId).to()
    }

}