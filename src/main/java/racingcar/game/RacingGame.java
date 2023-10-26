package racingcar.game;

import camp.nextstep.edu.missionutils.Randoms;
import racingcar.Validatable;
import racingcar.Validator;

import java.util.List;

/**
 * 게임 객체
 */
public class RacingGame implements Validatable {

	private final List<String> carNames;
	private final int tryCount;
	private final int minNum;
	private final int thresholdToMove;
	private final int maxNum;

	private static final int DEFAULT_POSITION = 0;

	public RacingGame(List<String> carNames, int tryCount, int minNum, int thresholdToMove, int maxNum) {
		this.carNames = carNames;
		this.tryCount = tryCount;
		this.minNum = minNum;
		this.thresholdToMove = thresholdToMove;
		this.maxNum = maxNum;
	}

	/**
	 * 외부에서 주어지는 상수 값은 유효성 검사를 하지 않았다.
	 * 해당 부분은 게임을 하는 사용자에게 주어지는 통제 변수가 아니므로 제외했다.
	 * 만약 해당 부분으로 인해 에러가 난다면 그 부분은 프로그램 자체에 책임이 있는 것이다.
	 */
	@Override
	public boolean isValid() {
		return !this.carNames.isEmpty()
			&&  this.tryCount > 0;
	}
	@Override
	public String toString() {
		return "RacingGame{" +
			"carNames=" + carNames +
			", tryCount=" + tryCount +
			", minNum=" + minNum +
			", thresholdToMove=" + thresholdToMove +
			", maxNum=" + maxNum +
			'}';
	}

	public void runGame() {
		List<Car> cars = this.carNames.stream()
				.map(name -> new Car(name, DEFAULT_POSITION))
				.toList();
		cars.forEach(car -> {
			Validator.throwIfNotValid(car, ExceptionStatus.INVALID_CAR);
		});

		for (int i = 0; i < tryCount; i++) {
			cars.forEach(car -> {
				int randomNum = Randoms.pickNumberInRange(minNum, maxNum);
				if (isGreaterThanThresholdToMove(randomNum)) {
					car.move();
				}
			});
			printMoveResult(cars);
		}
		printWinner(cars);
	}

	private boolean isGreaterThanThresholdToMove(int num) {
		return num >= thresholdToMove;
	}

	private void printMoveResult(List<Car> cars) {
		for (Car car : cars) {
			System.out.println(car.getName() + " : " + "-".repeat(car.getMoveCount()));
		}
	}

	private void printWinner(List<Car> cars) {
		int maxMoveCount = cars.stream()
				.mapToInt(Car::getMoveCount)
				.max()
				.orElse(0);
		List<String> winners = cars.stream()
				.filter(car -> car.getMoveCount() == maxMoveCount)
				.map(Car::getName)
				.toList();
		System.out.println("최종 우승자 : " + String.join(", ", winners));
	}
}
