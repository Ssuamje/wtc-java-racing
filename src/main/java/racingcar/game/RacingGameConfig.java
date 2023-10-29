package racingcar.game;

import racingcar.Validatable;
import racingcar.Validator;

import java.util.List;

public class RacingGameConfig implements Validatable {

	private final List<String> carNames;
	private final int tryCount;

	@Override
	public String toString() {
		return "RacingGameConfig{" +
			"carNames=" + this.carNames +
			", tryCount=" + this.tryCount +
			'}';
	}

	public RacingGameConfig(List<String> carNames, int tryCount) {
		this.carNames = carNames;
		this.tryCount = tryCount;
		Validator.throwIfNotValid(this, ExceptionStatus.INVALID_RACING_GAME_CONFIG);
	}

	public List<String> getCarNames() {
		return carNames;
	}

	public int getTryCount() {
		return tryCount;
	}

	@Override public boolean isValid() {
		return !this.carNames.isEmpty()
				&& this.tryCount > 0;
	}
}


