package calculator.enums;

public enum ErrorCode {
    IS_NOT_NUMBER("숫자를 입력해주세요"),
    IS_NOT_POSITIVE_NUMBER("양수가 아닙니다."),
    IS_NOT_CUSTOM_DELIMITER_INPUT("커스텀 구분자 입력을 진행하지 않았습니다. 기본 구분자를 사용해주세요."),
    DIFFERENT_CUSTOM_DELIMITER_INPUT("입력했던 커스텀 구분자와 다른 구분자를 사용했습니다."),
    ENTERED_DELIMITER_CONTINUOUS("구분자가 연속으로 등장하는 것은 허용되지 않습니다."),
    INVALID_POSITION_OF_DELIMITER("구분자의 위치가 잘못되었습니다. 구분자의 위치는 숫자 사이여야 합니다."),
    INVALID_RANGE_INTEGER("정수 범위가 아닙니다.");


    private final String errorName;

    ErrorCode(String errorName) {
        this.errorName = errorName;
    }

    public String getErrorName() {
        return errorName;
    }
}
