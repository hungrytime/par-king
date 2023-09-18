package parking.application.port.out

import parking.domain.entity.Member

interface SaveMemberPort {
    fun saveMember(member: Member): Member
    fun modifyMember(member: Member): Member
}