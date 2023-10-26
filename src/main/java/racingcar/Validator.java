package racingcar;

import racingcar.game.ExceptionStatus;

public class Validator {

	public static void throwIfNotValid(Validatable validatable, ExceptionStatus exceptionStatus) {
		if (!validatable.isValid())
			throw new IllegalArgumentException(
					exceptionStatus.getMessage() + " : " + validatable.toString()
					+ "\n"
					+ exceptionStatus.getUsage()
			);
	}
}
