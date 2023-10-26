package racingcar;

import racingcar.game.ExceptionStatus;

/**
 * Validatable한 타입들을 검증하는 정적 클래스
 */
public class Validator {

	/**
	 * 현재 어플리케이션의 경우, 정확한 용법에 대한 설명이 필요하므로,
	 * 필요한 문구들을 에러 메시지에 같이 붙여서 출력하도록 만들었다.
	 * @param validatable
	 * @param exceptionStatus
	 */
	public static void throwIfNotValid(Validatable validatable, ExceptionStatus exceptionStatus) {
		if (!validatable.isValid())
			throw new IllegalArgumentException(
					"\n"
					+ exceptionStatus.getMessage()
					+ "\n"
					+ validatable.toString()
					+ "\n"
					+ exceptionStatus.getUsage()
			);
	}
}
