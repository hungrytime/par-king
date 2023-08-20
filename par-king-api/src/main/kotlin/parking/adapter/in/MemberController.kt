package parking.adapter.`in`

import org.springframework.web.bind.annotation.*
import parking.application.port.`in`.FindMemberUseCase
import parking.common.DTO.ResponseDTO

@RestController
@RequestMapping("/member")
class MemberController(
    private val findMemberUseCase: FindMemberUseCase
) {

    @GetMapping
    fun test(): String {
        return findMemberUseCase.userIdByMemberId(1L)
    }

    @PostMapping("/sign-in")
    fun signIn(
        @RequestParam userId: String,
        @RequestParam password: String
    ): ResponseDTO<Boolean> {
        return ResponseDTO.success(true)
    }
}