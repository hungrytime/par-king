package parking.domain.exception

import parking.domain.exception.enum.ResultCode

class BusinessException(
    open val resultCode: ResultCode,
    override val message: String?,
    override val cause: Throwable? = null,
    open val content: Any? = null
): RuntimeException(message, cause)