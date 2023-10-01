package parking.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import parking.application.port.`in`.MakeOccupiedParkingLot
import parking.application.port.`in`.ModifyMemberUseCase
import parking.application.port.`in`.SignUpUseCase
import parking.application.port.out.FindMemberPort
import parking.application.port.out.FindParkingLotPort
import parking.application.port.out.SaveMemberPort
import parking.application.port.out.SaveParkingLotPort
import parking.application.vo.MemberInfoVO
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
) : SignUpUseCase, ModifyMemberUseCase, MakeOccupiedParkingLot {
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

    @Transactional
    override fun makeOccupied(userId: String, parkingLotId: Long) {
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

        parkingLot.makeOccupied()
        member.makeOccupiedParkingLot(parkingLotId)

        saveMemberPort.saveMember(member)
        saveParkingLotPort.saveParkingLot(parkingLot)

    }
}