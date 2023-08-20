package parking.adapter.out

import org.springframework.stereotype.Component
import parking.application.port.out.FindMemberPort
import parking.jpa.repositories.MemberJpaRepository

@Component
class MemberInquiryAdapter(
   private val memberJapRepository: MemberJpaRepository
) : FindMemberPort {

    @Override
    override fun findMemberById(memberId: Long): String {
        return memberJapRepository.findAll().first().userId
    }

}