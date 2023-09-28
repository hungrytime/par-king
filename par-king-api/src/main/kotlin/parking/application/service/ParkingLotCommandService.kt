package parking.application.service

import org.springframework.stereotype.Service
import parking.application.port.`in`.ModifyParkingLotUseCase
import parking.application.port.`in`.SaveParkingLotUseCase
import parking.application.port.out.SaveParkingLotPort
import parking.domain.entity.ParkingLot

@Service
class ParkingLotCommandService(
    private val saveParkingLotPort: SaveParkingLotPort
): SaveParkingLotUseCase, ModifyParkingLotUseCase {
    override fun saveParkingLot(name: String, city: String, totalSpace: Long): Boolean {
        val parkingLot = saveParkingLotPort.saveParkingLot(ParkingLot.toParkingLot(name, city, totalSpace, null))
        return parkingLot.selfValidCheck(name, city, totalSpace)
    }

    override fun modifyParkingLot(name: String, city: String, totalSpace: Long, availableSpace: Long): Boolean {
        val parkingLot = saveParkingLotPort.modifyParkingLot(ParkingLot.toParkingLot(name, city,totalSpace, availableSpace))
        return parkingLot.selfValidCheck(name, city, totalSpace)
    }
}