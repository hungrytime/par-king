package parking.adapter.out

import org.springframework.stereotype.Component
import parking.application.port.out.FindMemberPort
import parking.domain.entity.Member
import parking.jpa.repositories.MemberJpaRepository

@Component
class MemberInquiryAdapter(
   private val memberJapRepository: MemberJpaRepository
) : FindMemberPort {
    override fun findMemberById(memberId: Long): Member? {
        val optionalResult = memberJapRepository.findById(memberId)

        if (optionalResult.isPresent) return optionalResult.get().to()

        return null
    }

    override fun findMemberByUserId(userId: String): Member? {
        return memberJapRepository.findMemberByUserId(userId)?.to()
    }

}