package parking.adapter.`in`

import org.springframework.web.bind.annotation.*
import parking.adapter.DTO.SignUpMemberDTO
import parking.application.port.`in`.FindMemberUseCase
import parking.application.port.`in`.SignInUseCase
import parking.application.port.`in`.SignUpUseCase
import parking.common.DTO.ResponseDTO

@RestController
@RequestMapping("/member")
class MemberController(
    private val findMemberUseCase: FindMemberUseCase,
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase
) {

    @GetMapping
    fun test(): String {
        return findMemberUseCase.userIdByMemberId(1L)
    }

    @GetMapping("/sign-in")
    fun signIn(
        @RequestParam userId: String,
        @RequestParam password: String
    ): ResponseDTO<Boolean> {
        return ResponseDTO.success(signInUseCase.signInUseCase(userId, password))
    }

    @PostMapping("/sign-up")
    fun signUp(
        @RequestBody member: SignUpMemberDTO
    ): ResponseDTO<Boolean> {
        return ResponseDTO.success(signUpUseCase.signUp(member.userId, member.password, member.userName))
    }
}