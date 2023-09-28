package parking.application.service

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import parking.application.port.out.FindMemberPort

class MemberInquiryServiceTest: BehaviorSpec() {
    override fun isolationMode(): IsolationMode = IsolationMode.SingleInstance

    init {
        val findMemberPort = mockk<FindMemberPort>()

        val memberInquiryService = MemberInquiryService(findMemberPort)
    }
}