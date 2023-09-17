package parking.application.service

import org.springframework.stereotype.Service
import parking.application.port.`in`.FindParkingLotUseCase
import parking.application.port.out.FindParkingLotPort
import parking.application.vo.ParkingLotInfoVO

@Service
class ParkingLotInquiryService(
    private val findParkingLotPort: FindParkingLotPort
): FindParkingLotUseCase {
    override fun findParkingLotById(parkingLotId: Long): ParkingLotInfoVO {
        return ParkingLotInfoVO.from(findParkingLotPort.findParkingLot(parkingLotId))
    }
}