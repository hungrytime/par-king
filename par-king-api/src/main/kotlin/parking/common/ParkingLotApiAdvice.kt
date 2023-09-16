package parking.common

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import parking.common.dto.ResponseDTO
import parking.domain.exception.BusinessException
import parking.domain.exception.enum.ResultCode

@RestControllerAdvice
class ParkingLotApiAdvice {

    @ExceptionHandler(BusinessException::class)
    @ResponseStatus(HttpStatus.OK)
    fun exceptionHandler(e: BusinessException): ResponseDTO<String> {
        return ResponseDTO.fail(e.resultCode, content = "error", message = e.message)
    }

    @ExceptionHandler(Exception::class)
    fun exceptionHandler(e: Exception): ResponseDTO<String> {
        return ResponseDTO.fail(ResultCode.FAIL, content = "error", message = e.message)
    }
}