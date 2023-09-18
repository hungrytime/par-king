package parking.domain.entity

data class Member(
    val memberId: Long? = null,
    var userId: String,
    var password: String,
    var userName: String,
    //기본 ACTIVATED로 하고 탈퇴시 REVOKED로 변경
    var memberStatus: MemberStatus = MemberStatus.ACTIVATED,
    val createdDate: String? = null,
    val occupiedParkingLot: Long? = null
) {
    fun selfValidCheck(userId: String, userName: String): Boolean {
        if (this.userId != userId || this.userName != userName) return false

        return true
    }

    fun modifyBasicMemberInfo(userId: String, userName: String) {
        this.userId = userId
        this.userName = userName
    }

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
