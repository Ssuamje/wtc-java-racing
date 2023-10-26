package racingcar.game;

public enum ExceptionStatus {
	INVALID_CAR("유효하지 않은 자동차입니다.",
			"자동차 이름은 쉼표(,)를 기준으로 구분하며 이름은 5자 이하만 가능합니다."),
	INVALID_RACING_GAME("유효하지 않은 레이싱 게임입니다.",
			"유효한 자동차 이름과 자연수인 시도 횟수를 입력해주세요."),
	;

	private final String message;
	private final String usage;

	ExceptionStatus(String message, String usage) {
		this.message = message;
		this.usage = usage;
	}

	public String getMessage() {
		return message;
	}
	public String getUsage() {
		return usage;
	}
}
