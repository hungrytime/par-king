package parking.adapter.dto

import parking.application.vo.SignUpMemberVO

data class SignUpMemberDTO(
    val userId: String,
    val password: String,
    val userName: String
) {
    fun toVO() = SignUpMemberVO(
        userId = this.userId,
        password = this.password,
        userName = this.userName
    )

    companion object {
        fun makeSignUpMemberDTO(userId: String, password: String, userName: String) = SignUpMemberDTO(
            userId = userId,
            password = password,
            userName = userName
        )
    }
}
