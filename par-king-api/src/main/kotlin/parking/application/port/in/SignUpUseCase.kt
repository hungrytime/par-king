package parking.application.port.`in`

import parking.application.VO.SignUpMemberVO

interface SignUpUseCase {
    fun signUp(userId: String, password: String, userName: String): Boolean
}