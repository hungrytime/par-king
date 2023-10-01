package parking.application.service

import org.springframework.stereotype.Service
import parking.application.port.`in`.FindMemberUseCase
import parking.application.port.`in`.SignInUseCase
import parking.application.port.out.FindMemberPort
import parking.application.vo.MemberInfoVO
import parking.domain.exception.MemberException
import parking.domain.exception.enum.ResultCode

@Service
class MemberInquiryService(
    private val findMemberPort: FindMemberPort
) : FindMemberUseCase, SignInUseCase {
    override fun userIdByMemberId(memberId: Long): String {
        val member = findMemberPort.findMemberById(memberId) ?: throw MemberException(ResultCode.MEMBER_NOT_FOUND, "member not found")
        return member.userId
    }

    override fun memberInfoByUserId(userId: String): MemberInfoVO {
        val member = findMemberPort.findMemberByUserId(userId) ?: throw MemberException(ResultCode.MEMBER_NOT_FOUND, "member not found")
        return MemberInfoVO.from(member)
    }

    override fun signInUseCase(userId: String, password: String): Boolean {
        val member = findMemberPort.findMemberByUserId(userId) ?: throw MemberException(ResultCode.MEMBER_NOT_FOUND, "member not found")
        return member.checkPassword(password)
    }
}