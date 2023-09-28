package parking.application.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import parking.application.port.out.FindMemberPort
import parking.application.port.out.SaveMemberPort
import parking.domain.entity.Member
import parking.domain.exception.MemberException
import parking.domain.exception.enum.ResultCode

class MemberCommandServiceTest: BehaviorSpec() {
    override fun isolationMode(): IsolationMode = IsolationMode.SingleInstance

    init {
        val findMemberPort = mockk<FindMemberPort>()
        val saveMemberPort = mockk<SaveMemberPort>()

        val memberCommandService = MemberCommandService(findMemberPort, saveMemberPort)
        val member = Member.toMember("userId", "password", "userName")

        Given("회원 정보 저장 시") {

            When("회원 가입할 때") {
                every { saveMemberPort.saveMember(any()) } returns member

                Then("member valid 를 통과해야 한다") {
                    memberCommandService.signUp(member.userId, member.password, member.userName) shouldBe true
                }
            }
        }

        Given("회원 정보 수정 시") {

            When("수정할 멤버 정보가 없을 때") {
                every { findMemberPort.findMemberByUserId(any()) } throws MemberException(
                    ResultCode.MEMBER_NOT_FOUND,
                    message = "멤버 정보를 찾을 수 없습니다.")

                Then("MemberException 발생") {
                    shouldThrow<MemberException> { memberCommandService.modifyMember(member.userId, member.userName) }
                }
            }

            When("정상적으로 멤버가 수정 되었을 때") {
                val modifiedMember = Member.toMember("modifiedMember", "password", "modifiedUserName")

                every { findMemberPort.findMemberByUserId(any()) } returns member
                every { saveMemberPort.modifyMember(member) } returns modifiedMember

                Then("수정된 정보로 저장돼야 한다") {
                    memberCommandService.modifyMember(modifiedMember.userId, modifiedMember.userName) shouldBe true
                }
            }
        }
    }
}