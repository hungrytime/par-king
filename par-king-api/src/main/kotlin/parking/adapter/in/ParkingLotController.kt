package parking.adapter.`in`

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import parking.adapter.dto.ParkingLotDTO
import parking.application.port.`in`.FindParkingLotUseCase
import parking.common.dto.ResponseDTO

@RestController("/parking-lot")
class ParkingLotController(
    private val findParkingLotUseCase: FindParkingLotUseCase
) {
    @GetMapping("/info")
    fun parkingLotInfo(
        @RequestParam parkingLotId: Long
    ): ResponseDTO<ParkingLotDTO> {
        return ResponseDTO.success(ParkingLotDTO.from(findParkingLotUseCase.findParkingLotById(parkingLotId)))
    }


}