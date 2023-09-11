package parking.application.port.`in`

interface SignInUseCase {
    fun signInUseCase(userId: String, password: String) : Boolean
}