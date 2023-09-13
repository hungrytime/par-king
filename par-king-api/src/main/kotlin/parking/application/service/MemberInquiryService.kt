package parking.application.service

import org.springframework.stereotype.Service
import parking.application.port.`in`.FindMemberUseCase
import parking.application.port.`in`.SignInUseCase
import parking.application.port.out.FindMemberPort
import parking.application.vo.MemberInfoVO

@Service
class MemberInquiryService(
    private val findMemberPort: FindMemberPort
) : FindMemberUseCase, SignInUseCase {
    override fun userIdByMemberId(memberId: Long): String {
        return findMemberPort.findMemberById(memberId).userId
    }

    override fun userInfoByUserId(userId: String): MemberInfoVO {
        return MemberInfoVO.from(findMemberPort.findMemberByUserId(userId))
    }

    override fun signInUseCase(userId: String, password: String): Boolean {
        val member = findMemberPort.findMemberByUserId(userId)
        return member.checkPassword(password)
    }
}