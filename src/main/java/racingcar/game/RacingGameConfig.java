package racingcar.game;

import racingcar.Validatable;

import java.util.List;

public class RacingGameConfig implements Validatable {

	private final List<String> carNames;
	private final int tryCount;

	public RacingGameConfig(List<String> carNames, int tryCount) {
		this.carNames = carNames;
		this.tryCount = tryCount;
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


