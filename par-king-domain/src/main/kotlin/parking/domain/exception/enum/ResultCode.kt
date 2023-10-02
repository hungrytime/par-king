package parking.domain.exception.enum

enum class ResultCode {
    SUCCESS,
    FAIL,

    //멤버 제외처리
    MEMBER_NOT_FOUND,
    MEMBER_AUTHENTICATION_FAIL,
    MEMBER_INVALID,
    MEMBER_ALREADY_OCCUPIED,
    MEMBER_NOT_FOUND_PARKING_LOT,

    //주차장 제외처리
    PARKING_LOT_NOT_FOUND,
    PARKING_LOT_ALREADY_FULL,
    PARKING_LOT_CAN_NOT_RELEASE
}