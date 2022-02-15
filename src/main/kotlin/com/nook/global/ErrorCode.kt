package com.nook.global

enum class ErrorCode(val message: String) {
    E0001("이름이 누락되었습니다."),
    E0002("이름은 최대 20자까지 가능합니다."),
    E0003("반구 정보가 누락되었습니다."),
    E0004("북반구, 남반구 중 하나를 입력해주세요.")
}