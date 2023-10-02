package parking.adapter.`in`

import org.springframework.web.bind.annotation.*
import parking.adapter.dto.MemberInfoDTO
import parking.adapter.dto.SignUpMemberDTO
import parking.application.port.`in`.*
import parking.common.dto.ResponseDTO

@RestController
@RequestMapping("/member")
class MemberController(
    private val findMemberUseCase: FindMemberUseCase,
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val modifyMemberUseCase: ModifyMemberUseCase,
    private val occupyParkingLot: OccupyParkingLot,
    private val releaseParkingLot: ReleaseParkingLot
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

    @GetMapping("/info")
    fun getMemberInfo(
        @RequestParam userId: String
    ): ResponseDTO<MemberInfoDTO> {
        return ResponseDTO.success(MemberInfoDTO.from(findMemberUseCase.memberInfoByUserId(userId)))
    }

    @PostMapping("/sign-up")
    fun signUp(
        @RequestBody member: SignUpMemberDTO
    ): ResponseDTO<Boolean> {
        return ResponseDTO.success(signUpUseCase.signUp(member.userId, member.password, member.userName))
    }

    @PostMapping("/modify")
    fun modify(
        @RequestBody member: MemberInfoDTO
    ): ResponseDTO<Boolean> {
        return ResponseDTO.success(modifyMemberUseCase.modifyMember(member.userId, member.userName))
    }

    @PostMapping("/parkingLot/occupy")
    fun occupyParkingLot(
        @RequestParam userId: String,
        @RequestParam parkingLotId: Long
    ): ResponseDTO<Unit> {
        return ResponseDTO.success(occupyParkingLot.occupyParkingLot(userId, parkingLotId))
    }

    @PostMapping("/parkingLot/release")
    fun releaseParkingLot(
        @RequestParam userId: String
    ): ResponseDTO<Unit> {
        return ResponseDTO.success(releaseParkingLot.releaseParkingLot(userId))
    }
}