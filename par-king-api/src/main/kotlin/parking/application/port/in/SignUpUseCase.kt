package parking.application.port.`in`

interface SignUpUseCase {
    fun signUp(userId: String, password: String, userName: String): Boolean
}