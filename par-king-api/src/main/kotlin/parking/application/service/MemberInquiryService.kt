package parking.application.service

import org.springframework.stereotype.Service
import parking.application.port.`in`.FindMemberUseCase
import parking.application.port.out.FindMemberPort

@Service
class MemberInquiryService(
    private val findMemberPort: FindMemberPort
) : FindMemberUseCase {

    @Override
    override fun userIdByMemberId(memberId: Long): String {
        return findMemberPort.findMemberById(memberId)
    }

}