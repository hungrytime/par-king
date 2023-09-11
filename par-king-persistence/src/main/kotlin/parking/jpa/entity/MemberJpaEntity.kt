package parking.jpa.entity

import parking.domain.Member
import parking.domain.MemberStatus
import javax.persistence.*

@Entity
@Table(name = "member")
data class MemberJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val memberId: Long? = null,

    val userId: String,
    val password: String,
    val userName: String,

    @Enumerated(EnumType.STRING)
    val memberStatus: MemberStatus = MemberStatus.ACTIVATED,

    val occupiedParkingLot: Long? = null

) : BaseEntity() {

    fun to() = Member(
        memberId = this.memberId,
        memberStatus = this.memberStatus,
        userId = this.userId,
        password = this.password,
        userName = this.userName,
        createdDate = this.createdAt.toString(),
        occupiedParkingLot = this.occupiedParkingLot
    )

    companion object {
        fun from(member: Member) = MemberJpaEntity(
           userId = member.userId, password = member.password, userName = member.userName
        )
    }
}