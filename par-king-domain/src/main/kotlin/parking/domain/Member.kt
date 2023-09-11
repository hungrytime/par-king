package parking.domain

data class Member(
    val memberId: Long? = null,
    val userId: String,
    val password: String,
    val userName: String,
    //기본 ACTIVATED로 하고 탈퇴시 REVOKED로 변경
    var memberStatus: MemberStatus = MemberStatus.ACTIVATED,
    val createdDate: String? = null,
    val occupiedParkingLot: Long? = null
) {
    fun changeRevoked() {
        this.memberStatus = MemberStatus.REVOKED
    }

    fun checkPassword(password: String) : Boolean {
        return password == this.password
    }

    companion object {
        fun toMember(userId: String, password: String, userName: String) =
            Member(userId = userId, password = password, userName = userName)
    }
}

enum class MemberStatus {
    ACTIVATED,
    REVOKED
}
