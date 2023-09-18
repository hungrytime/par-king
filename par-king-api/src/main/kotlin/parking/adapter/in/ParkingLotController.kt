package parking.adapter.`in`

import org.springframework.web.bind.annotation.*
import parking.adapter.dto.ParkingLotDTO
import parking.application.port.`in`.FindParkingLotUseCase
import parking.application.port.`in`.SaveParkingLotUseCase
import parking.common.dto.ResponseDTO

@RestController("/parking-lot")
class ParkingLotController(
    private val findParkingLotUseCase: FindParkingLotUseCase,
    private val saveParkingLotUseCase: SaveParkingLotUseCase
) {
    @GetMapping("/info")
    fun parkingLotInfo(
        @RequestParam parkingLotId: Long
    ): ResponseDTO<ParkingLotDTO> {
        return ResponseDTO.success(ParkingLotDTO.from(findParkingLotUseCase.findParkingLotById(parkingLotId)))
    }

    @PostMapping("/save")
    fun save(
        @RequestBody parkingLotDTO: ParkingLotDTO
    ): ResponseDTO<Boolean> {

        return ResponseDTO.success(
            saveParkingLotUseCase.saveParkingLot(
                parkingLotDTO.name,
                parkingLotDTO.city,
                parkingLotDTO.totalSpace
            )
        )
    }
}