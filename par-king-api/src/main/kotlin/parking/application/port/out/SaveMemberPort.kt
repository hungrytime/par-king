package parking.application.port.out

import parking.domain.Member

interface SaveMemberPort {
    fun saveMember(member: Member): Member
}