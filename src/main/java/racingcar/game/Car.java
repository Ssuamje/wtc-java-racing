package racingcar.game;

import racingcar.Validatable;
import racingcar.Validator;

/**
 * 자동차 객체
 */
public class Car implements Validatable {
	private final String name;
	private int moveCount;
	public Car(String name, int moveCount) {
		this.name = name;
		this.moveCount = moveCount;
		Validator.throwIfNotValid(this, ExceptionStatus.INVALID_CAR);
	}

	public void move() {
		this.moveCount++;
	}

	public String getName() {
		return name;
	}

	public int getMoveCount() {
		return moveCount;
	}

	@Override
	public boolean isValid() {
		return !name.isBlank() && name.length() <= 5
			&& moveCount >= 0;
	}
	@Override
	public String toString() {
		return "Car{ name='" + name + '\'' +
			", position=" + moveCount +
			'}';
	}
}
