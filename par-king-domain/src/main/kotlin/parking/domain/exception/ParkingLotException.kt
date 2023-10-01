package parking.domain.exception

import parking.domain.exception.enum.ResultCode

class ParkingLotException(
    override val resultCode: ResultCode,
    override val message: String?,
    override val cause: Throwable? = null,
    override val content: Any? = null
) : BusinessException(resultCode, message, cause, content)