package parking.jpa.entity

import javax.persistence.*

@Entity
@Table(name = "member")
data class MemberJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val memberId: Long? = null,

    val userId: String,
    val password: String

) : BaseEntity() {


}