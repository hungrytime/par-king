package parking.application.service

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import parking.application.port.out.FindMemberPort
import parking.domain.entity.Member

class MemberInquiryServiceTest: BehaviorSpec() {
    override fun isolationMode(): IsolationMode = IsolationMode.SingleInstance

    init {
        val findMemberPort = mockk<FindMemberPort>()

        val memberInquiryService = MemberInquiryService(findMemberPort)

        val member = Member.toMember(
            userId = "userId", password = "password", userName = "userName"
        )

        Given("memberId 로 조회") {

            When("userId 를 조회할 때") {
                every { findMemberPort.findMemberByUserId(any()) } returns member

                Then("userId를 리턴한다") {
                    memberInquiryService.userIdByMemberId(1L) shouldBe member.userId
                }
            }
        }

        Given("userId 로 조회") {

            When("member 정보 조회 할 때") {
                every { findMemberPort.findMemberByUserId(any()) } returns member

                Then("member 정보 리턴") {
                    val result = memberInquiryService.memberInfoByUserId(member.userId)

                    result.userId shouldBe member.userId
                    result.userName shouldBe member.userName
                }
            }
        }

        Given("signIn") {

            When("signIn 성공 할 때") {
                every { findMemberPort.findMemberByUserId(any()) } returns member

                Then("성공 리턴") {
                    memberInquiryService.signInUseCase(member.userId, member.password) shouldBe true
                }
            }
        }
    }
}