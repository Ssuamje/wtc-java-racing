package racingcar.game;

import racingcar.Display;
import racingcar.Validatable;
import racingcar.Validator;

import java.util.List;
import java.util.OptionalInt;

/**
 * 게임 객체
 */
public class RacingGame implements Validatable {
	private static final int DEFAULT_POSITION = 0;

	private final RacingGameConfig config;
	private final RacingGameStrategy strategy;

	public RacingGame(RacingGameConfig config, RacingGameStrategy strategy) {
		this.config = config;
		this.strategy = strategy;
		Validator.throwIfNotValid(this, ExceptionStatus.INVALID_RACING_GAME);
	}

	@Override
	public boolean isValid() {
		return this.config.isValid();
	}

	@Override
	public String toString() {
		return "RacingGame{" +
				"carNames=" + this.config.getCarNames() +
				", tryCount=" + this.config.getTryCount() +
				'}';
	}

	public void runGame() {
		List<String> carNames = this.config.getCarNames();
		List<Car> cars = createCarsByNames(carNames, DEFAULT_POSITION);

		int tryCount = this.config.getTryCount();
		for (int i = 0; i < tryCount; i++) {
			cars.forEach(this::moveByStrategy);
			printMoveResult(cars);
		}
		printWinner(cars);
	}

	private void moveByStrategy(Car car) {
		if (strategy.isMoveable()) {
			car.move();
		}
	}

	private List<Car> createCarsByNames(List<String> carNames, int defaultPosition) {
		return carNames.stream()
				.map(name -> new Car(name, defaultPosition))
				.toList();
	}

	private void printMoveResult(List<Car> cars) {
		cars.forEach(car ->
				Display.putMessage(car.getName() + " : " + "-".repeat(car.getMoveCount()))
		);
		Display.putNewLine();
	}

	private void printWinner(List<Car> cars) {
		int maxMoveCount = getMaxMoveCount(cars).orElse(0);
		List<String> winners = getWinnersByMaxMoveCount(cars, maxMoveCount);
		Display.putMessage("최종 우승자 : " + String.join(", ", winners));
	}

	private OptionalInt getMaxMoveCount(List<Car> cars) {
		return cars.stream()
				.mapToInt(Car::getMoveCount)
				.max();
	}

	private List<String> getWinnersByMaxMoveCount(List<Car> cars, int maxMoveCount) {
		return cars.stream()
				.filter(car -> car.getMoveCount() == maxMoveCount)
				.map(Car::getName)
				.toList();
	}
}
