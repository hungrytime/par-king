package parking.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import parking.application.port.`in`.OccupyParkingLot
import parking.application.port.`in`.ReleaseParkingLot
import parking.application.port.`in`.ModifyMemberUseCase
import parking.application.port.`in`.SignUpUseCase
import parking.application.port.out.FindMemberPort
import parking.application.port.out.FindParkingLotPort
import parking.application.port.out.SaveMemberPort
import parking.application.port.out.SaveParkingLotPort
import parking.domain.entity.Member
import parking.domain.exception.MemberException
import parking.domain.exception.ParkingLotException
import parking.domain.exception.enum.ResultCode

@Service
class MemberCommandService(
    private val findMemberPort: FindMemberPort,
    private val saveMemberPort: SaveMemberPort,
    private val findParkingLotPort: FindParkingLotPort,
    private val saveParkingLotPort: SaveParkingLotPort
) : SignUpUseCase, ModifyMemberUseCase, OccupyParkingLot, ReleaseParkingLot {
    //회원가입
    override fun signUp(userId: String, password: String, userName: String): Boolean {
        val member = saveMemberPort.saveMember(Member.toMember(userId, password, userName))
        return member.selfValidCheck(userId, userName)
    }

    // 회원정보 수정
    override fun modifyMember(userId: String, userName: String): Boolean {
        val member = findMemberPort.findMemberByUserId(userId) ?: throw MemberException(
            ResultCode.MEMBER_NOT_FOUND,
            message = "멤버 정보를 찾을 수 없습니다."
        )

        member.modifyBasicMemberInfo(userId, userName)

        val afterSaveMember = saveMemberPort.modifyMember(member)
        return afterSaveMember.selfValidCheck(userId, userName)
    }

    //주차장 찜 기능
    @Transactional
    override fun occupyParkingLot(userId: String, parkingLotId: Long) {
        val member = findMemberPort.findMemberByUserId(userId) ?: throw MemberException(
            ResultCode.MEMBER_NOT_FOUND,
            message = "멤버($userId) 정보를 찾을 수 없습니다."
        )

        if (member.checkAlreadyOccupied()) throw MemberException(
            ResultCode.MEMBER_ALREADY_OCCUPIED,
            message = "이미 찜한 주자창이 있습니다."
        )

        val parkingLot = findParkingLotPort.findParkingLot(parkingLotId) ?: throw ParkingLotException(
            ResultCode.PARKING_LOT_NOT_FOUND,
            message = "주차장 정보($parkingLotId)를 찾을 수 없습니다."
        )

        parkingLot.occupy()
        member.occupyParkingLot(parkingLotId)

        saveMemberPort.saveMember(member)
        saveParkingLotPort.saveParkingLot(parkingLot)

    }

    override fun releaseParkingLot(userId: String) {
        val member = findMemberPort.findMemberByUserId(userId) ?: throw MemberException(
            ResultCode.MEMBER_NOT_FOUND,
            message = "멤버($userId) 정보를 찾을 수 없습니다."
        )

        if (member.occupiedParkingLot == null) throw MemberException(
            ResultCode.MEMBER_NOT_FOUND_PARKING_LOT,
            message = "찜한 주차장 정보를 찾을 수 없습니다."
        )

        val parkingLot = findParkingLotPort.findParkingLot(member.occupiedParkingLot!!) ?: throw ParkingLotException(
            ResultCode.PARKING_LOT_NOT_FOUND,
            message = "주차장 정보(${member.occupiedParkingLot})를 찾을 수 없습니다."
        )

        member.releaseParkingLot()
        parkingLot.release()

        saveMemberPort.saveMember(member)
        saveParkingLotPort.saveParkingLot(parkingLot)
    }
}