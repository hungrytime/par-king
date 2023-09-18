package parking.application.service

import org.springframework.stereotype.Service
import parking.application.port.`in`.ModifyMemberUseCase
import parking.application.port.`in`.SignUpUseCase
import parking.application.port.out.FindMemberPort
import parking.application.port.out.SaveMemberPort
import parking.application.vo.MemberInfoVO
import parking.domain.entity.Member
import parking.domain.exception.MemberException
import parking.domain.exception.enum.ResultCode

@Service
class MemberCommandService(
    private val findMemberPort: FindMemberPort,
    private val saveMemberPort: SaveMemberPort
) : SignUpUseCase, ModifyMemberUseCase {
    override fun signUp(userId: String, password: String, userName: String): Boolean {
        val member = saveMemberPort.saveMember(Member.toMember(userId, password, userName))
        return member.selfValidCheck(userId, userName)
    }

    override fun modifyMember(userId: String, userName: String): Boolean {
        val member = findMemberPort.findMemberByUserId(userId) ?: throw MemberException(
            ResultCode.MEMBER_NOT_FOUND,
            message = "멤버 정보를 찾을 수 없습니다."
        )

        member.modifyBasicMemberInfo(userId, userName)

        val afterSaveMember = saveMemberPort.modifyMember(member)
        return afterSaveMember.selfValidCheck(userId, userName)
    }
}